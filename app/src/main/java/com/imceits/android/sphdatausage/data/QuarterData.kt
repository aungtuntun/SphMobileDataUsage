package com.imceits.android.sphdatausage.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Quarter")
data class QuarterData(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "mobile_data")
    val mobileData: Double,
    @ColumnInfo(name = "quarter")
    val quarter: String,
    @ColumnInfo(name = "parent_id")
    var parentId: Int,
    @ColumnInfo(name = "decrease")
    val decrease: Boolean)