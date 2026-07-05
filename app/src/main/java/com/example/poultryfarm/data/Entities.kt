package com.example.poultryfarm.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "halls")
data class Hall(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val initialCount: Int,
    val sortOrder: Int = 0
)

@Entity(tableName = "daily_entries")
data class DailyEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val hallId: Int,
    val date: String,
    val deaths: Int,
    val note: String = ""
)

@Entity(tableName = "vaccine_records")
data class VaccineRecord(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val vaccineType: String,
    val method: String,
    val brand: String,
    val date: String,
    val dose: String,
    val halls: String,
    val chickAge: String
)

@Entity(tableName = "lighting_records")
data class LightingRecord(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val ageDay: String,
    val darkHours: String,
    val timeRange: String,
    val date: String
)
