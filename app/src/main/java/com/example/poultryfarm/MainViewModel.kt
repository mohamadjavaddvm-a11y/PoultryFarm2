package com.example.poultryfarm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.poultryfarm.data.AppDatabase
import com.example.poultryfarm.data.Hall
import com.example.poultryfarm.data.DailyEntry
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class HallReport(
    val hall: Hall,
    val deathsToday: Int,
    val cumulativeDeaths: Int,
    val stockStartOfDay: Int,
    val remaining: Int,
    val cumulativePercent: Double
)

class MainViewModel(app: Application) : AndroidViewModel(app) {
    private val db = AppDatabase.getInstance(app)
    private val hallDao = db.hallDao()
    private val entryDao = db.dailyEntryDao()

    val vaccines = db.vaccineDao().getAll()
    val lighting = db.lightingDao().getAll()

    val halls: StateFlow<List<Hall>> = hallDao.getAll()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val dates: StateFlow<List<String>> = entryDao.getAllDates()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addHall(name: String, initialCount: Int) = viewModelScope.launch {
        hallDao.insert(Hall(name = name, initialCount = initialCount, sortOrder = halls.value.size))
    }

    fun deleteHall(hall: Hall) = viewModelScope.launch { hallDao.delete(hall) }

    fun saveDailyEntries(date: String, deathsByHall: Map<Int, Int>) = viewModelScope.launch {
        val entries = deathsByHall.map { (hallId, deaths) -> DailyEntry(hallId = hallId, date = date, deaths = deaths) }
        entryDao.insertAll(entries)
    }

    suspend fun getReportForDate(date: String): List<HallReport> {
        return halls.value.map { hall ->
            val upToToday = entryDao.getUpTo(hall.id, date)
            val cumulative = upToToday.sumOf { it.deaths }
            val todayDeaths = upToToday.filter { it.date == date }.sumOf { it.deaths }
            val cumulativeBefore = cumulative - todayDeaths
            val stockStart = hall.initialCount - cumulativeBefore
            val remaining = hall.initialCount - cumulative
            val percent = if (hall.initialCount > 0) cumulative * 100.0 / hall.initialCount else 0.0
            HallReport(hall, todayDeaths, cumulative, stockStart, remaining, percent)
        }
    }
}
