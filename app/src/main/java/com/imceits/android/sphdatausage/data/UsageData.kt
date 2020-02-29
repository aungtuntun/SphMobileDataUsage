package com.imceits.android.sphdatausage.data

import androidx.room.Embedded
import androidx.room.Relation

data class UsageData(
    @Embedded
    val data: MobileData,
    @Relation(parentColumn = "id", entityColumn = "parent_id", entity = QuarterData::class)
    val dataList:List<QuarterData>
)
