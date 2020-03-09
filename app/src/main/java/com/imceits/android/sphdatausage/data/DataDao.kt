package com.imceits.android.sphdatausage.data

import androidx.lifecycle.LiveData
import androidx.room.*
import org.jetbrains.annotations.NotNull

@Dao
abstract class DataDao {

 public fun insertData(@NotNull dataList: List<MobileData>) {
  for (i in dataList) {
   val parentId = insertMobileData(i)
   val quarterList = i.dataList
   quarterList.forEach {
    it.parentId = parentId.toInt()
    insertQuarter(it)
   }
  }
 }

 @Transaction
 @Query("SELECT * FROM Mobile")
 abstract fun getData(): LiveData<List<UsageData>>

 @Insert(onConflict = OnConflictStrategy.REPLACE)
 abstract fun insertMobileData(data: MobileData): Long

 @Insert(onConflict = OnConflictStrategy.REPLACE)
 abstract fun insertQuarter(data: QuarterData)

}