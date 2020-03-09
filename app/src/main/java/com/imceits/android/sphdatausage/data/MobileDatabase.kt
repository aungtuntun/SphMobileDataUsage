package com.imceits.android.sphdatausage.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MobileData::class, QuarterData::class], version = 1, exportSchema = false)
abstract class MobileDatabase: RoomDatabase() {

    abstract fun dataDao() : DataDao

/*    companion object {
        @Volatile
        private var INSTANCE: MobileDatabase? = null

        fun getInstance(context: Context): MobileDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    MobileDatabase::class.java, "Data.db")
                    .allowMainThreadQueries()
                    .addCallback(object : Callback() {

                    }).build()
                INSTANCE = instance
                return instance
            }
        }
    }*/
}