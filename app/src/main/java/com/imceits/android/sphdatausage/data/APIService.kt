package com.imceits.android.sphdatausage.data

import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    @GET("datastore_search?resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f&limit=59")
    fun getData(): Call<MobileResponse>

}