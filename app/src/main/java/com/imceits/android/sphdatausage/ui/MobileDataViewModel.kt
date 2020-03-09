package com.imceits.android.sphdatausage.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.imceits.android.sphdatausage.data.AbsentLiveData
import com.imceits.android.sphdatausage.data.DataRepository
import com.imceits.android.sphdatausage.data.UsageData
import com.imceits.android.sphdatausage.network.Resource
import javax.inject.Inject

class MobileDataViewModel @Inject constructor(dataRepository: DataRepository) : ViewModel() {
    val paramData = MutableLiveData<Int>()

    val mobileDataList: LiveData<Resource<List<UsageData>>> = Transformations.switchMap(paramData) {
        if (it == 0) {
            AbsentLiveData.create()
        }else {
            dataRepository.loadMobileDataUsages()
        }
    }

    fun  setParam(id: Int) {
        paramData.value = id
    }
}
