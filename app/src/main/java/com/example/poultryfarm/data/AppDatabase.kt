package com.example.poultryfarm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Hall::class, DailyEntry::class, VaccineRecord::class, LightingRecord::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun hallDao(): HallDao
    abstract fun dailyEntryDao(): DailyEntryDao
    abstract fun vaccineDao(): VaccineDao
    abstract fun lightingDao(): LightingDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "poultry_farm.db"
                ).build()
                INSTANCE = instance
                CoroutineScope(Dispatchers.IO).launch { seedIfEmpty(instance) }
                instance
            }
        }

        private suspend fun seedIfEmpty(db: AppDatabase) {
            if (db.vaccineDao().count() == 0) db.vaccineDao().insertAll(SeedData.vaccines)
            if (db.lightingDao().count() == 0) db.lightingDao().insertAll(SeedData.lighting)
        }
    }
}
