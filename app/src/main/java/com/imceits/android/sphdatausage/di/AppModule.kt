package com.imceits.android.sphdatausage.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.imceits.android.sphdatausage.data.APIService
import com.imceits.android.sphdatausage.data.DataDao
import com.imceits.android.sphdatausage.data.MobileDatabase
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
  //  private val BASE_URL : String = "https://data.gov.sg/api/action/"
    @Provides @Singleton
    fun provideAPIService(gson: Gson, okHttpClient: OkHttpClient): APIService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://data.gov.sg/api/action/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(APIService::class.java)
    }

    @Provides @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()
        okHttpClient.connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
        return okHttpClient.build()
    }

    @Provides @Singleton
    fun provideMobileDatabase(app: Application): MobileDatabase {
        return Room.databaseBuilder(app.applicationContext, MobileDatabase::class.java, "Data.db")
            .allowMainThreadQueries()
            .build()
    }

    @Provides @Singleton
    fun provideDataDao(db: MobileDatabase): DataDao {
        return db.dataDao()
    }
}