package com.imceits.android.sphdatausage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.imceits.android.sphdatausage.data.DataRepository
import com.imceits.android.sphdatausage.data.MobileData
import com.imceits.android.sphdatausage.data.MobileDatabase
import com.imceits.android.sphdatausage.data.UsageData
import com.imceits.android.sphdatausage.network.Resource

class MainViewModel(application: Application) : AndroidViewModel(application) {
private val repository: DataRepository
    val dataList: LiveData<Resource<List<UsageData>>>

    init {
        val dataDao = MobileDatabase.getInstance(application).dataDao()
        repository = DataRepository(dataDao)
        dataList = repository.loadMobileDataUsages()
    }
}