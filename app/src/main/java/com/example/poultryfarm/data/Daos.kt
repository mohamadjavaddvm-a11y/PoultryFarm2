package com.example.poultryfarm.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface HallDao {
    @Query("SELECT * FROM halls ORDER BY sortOrder")
    fun getAll(): Flow<List<Hall>>

    @Insert
    suspend fun insert(hall: Hall): Long

    @Update
    suspend fun update(hall: Hall)

    @Delete
    suspend fun delete(hall: Hall)
}

@Dao
interface DailyEntryDao {
    @Query("SELECT DISTINCT date FROM daily_entries ORDER BY date DESC")
    fun getAllDates(): Flow<List<String>>

    @Query("SELECT * FROM daily_entries WHERE hallId = :hallId AND date <= :date")
    suspend fun getUpTo(hallId: Int, date: String): List<DailyEntry>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entries: List<DailyEntry>)

    @Delete
    suspend fun delete(entry: DailyEntry)
}

@Dao
interface VaccineDao {
    @Query("SELECT * FROM vaccine_records ORDER BY date")
    fun getAll(): Flow<List<VaccineRecord>>

    @Insert
    suspend fun insertAll(records: List<VaccineRecord>)

    @Query("SELECT COUNT(*) FROM vaccine_records")
    suspend fun count(): Int
}

@Dao
interface LightingDao {
    @Query("SELECT * FROM lighting_records ORDER BY id")
    fun getAll(): Flow<List<LightingRecord>>

    @Insert
    suspend fun insertAll(records: List<LightingRecord>)

    @Query("SELECT COUNT(*) FROM lighting_records")
    suspend fun count(): Int
}
