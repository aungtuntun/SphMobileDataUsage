package com.imceits.android.sphdatausage.data

import androidx.lifecycle.LiveData
import com.imceits.android.sphdatausage.network.AppExecutors
import com.imceits.android.sphdatausage.network.NetworkBoundResource
import com.imceits.android.sphdatausage.network.Resource
import com.imceits.android.sphdatausage.network.ServiceGenerator
import retrofit2.Call
import java.util.concurrent.Executors

class DataRepository(private val dataDao: DataDao) {
    val appExecutors: AppExecutors = AppExecutors(
       Executors.newSingleThreadExecutor(),
       Executors.newFixedThreadPool(4),
       AppExecutors.MainThreadExecutor()
   )
    val apiService: APIService = ServiceGenerator.createService(APIService::class.java)

    fun loadMobileDataUsages(): LiveData<Resource<List<UsageData>>> {
        return object : NetworkBoundResource<List<UsageData>, MobileResponse>(appExecutors = appExecutors) {

            override fun saveCallResult(item: MobileResponse) {
                val dataList = item.getDataList(item.result)
                dataDao.insertData(dataList)
            }

            override fun shouldFetch(data: List<UsageData>?): Boolean {
                return data.isNullOrEmpty()
            }

            override fun loadFromDb(): LiveData<List<UsageData>> {
                return dataDao.getData()
            }

            override fun createCall(): Call<MobileResponse> {
                return apiService.getData()
            }
        }.asLiveData()
    }
}