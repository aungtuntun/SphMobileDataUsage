package com.imceits.android.sphdatausage.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "Mobile")
 public data class MobileData (
   @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int= 0,
    @ColumnInfo(name = "mobile_data")
    var mobileData: Double = 0.0,
    @ColumnInfo(name = "quarter")
    var quarter: String = "",
   @ColumnInfo(name = "image")
    var image: Boolean = false,
   @Ignore
   var dataList:List<QuarterData> = emptyList()
 ) {

}