package com.imceits.android.sphdatausage.data

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MobileResponse(
    @SerializedName("result")
    @Expose
val result: JsonObject
) {
    fun getDataList(data: JsonObject): List<MobileData> {
        var usageList: MutableList<MobileData>?
        val quarterData: List<QuarterData> = emptyList()
        val yearlyMap = HashMap<String, List<MobileData>>()
        val jsonArray: JsonArray = data.getAsJsonArray("records")
        for (i in 0 until jsonArray.size()) {
            val jsonObject = jsonArray.get(i).asJsonObject
            val id = jsonObject.get("_id").asInt
            val quarter = jsonObject.get("quarter").asString
            val volume = jsonObject.get("volume_of_mobile_data").asDouble
            val usage = MobileData(id, volume, quarter, false, quarterData)

            val year = quarter.split("-")[0]
            if (yearlyMap.containsKey(year)) {
                usageList = yearlyMap.get(year) as MutableList<MobileData>?
            }else{
                usageList = mutableListOf()
            }
            usageList?.add(usage)
            usageList?.let {
                yearlyMap.put(year, it)
            }
        }

        var year: Int
        usageList = mutableListOf()
        for (i in 0 until yearlyMap.size) {
            year = i + 2008
            if (year <=2018) {
                val mobileData = calculateYearlyDataUsage(year, yearlyMap.get(year.toString()))
                usageList.add(mobileData)
            }
        }
        return usageList
    }

    private fun calculateYearlyDataUsage(year: Int, usageList: List<MobileData>?) : MobileData {
        var totalVolume = 0.0
        var maxVolume = 0.0
        var data: Double
        var showImage = false
        // for quarter list
        val dataList : MutableList<QuarterData> = mutableListOf()
        for (i in usageList!!.indices) {
            data = usageList[i].mobileData
            totalVolume += data
            if (maxVolume < data) {
                maxVolume = data
                showImage = false
            }else{
                showImage = true
            }
            val quarterData = QuarterData(usageList[i].id,usageList[i].mobileData, usageList[i].quarter, 0, showImage)
            dataList.add(quarterData)
        }
        return MobileData(0, totalVolume, year.toString(), showImage, dataList)
    }

}