package com.imceits.android.sphdatausage

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.imceits.android.sphdatausage.data.*
import com.imceits.android.sphdatausage.network.Resource
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsNull.nullValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.AdditionalMatchers.not
import org.mockito.Mockito.mock
import java.util.*
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class MainViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var mainViewModel: MainViewModel
    private lateinit var dataDao: DataDao

    @Before
    fun setupViewModel() {
        mainViewModel = MainViewModel(ApplicationProvider.getApplicationContext())
        dataDao = MobileDatabase.getInstance(ApplicationProvider.getApplicationContext()).dataDao()
       // val repository = mock(DataRepository::class.java)
    }

    @Test
    fun should_insert_Data() {
        val mobileData = MobileData(1, 10.45, "2018", true)
        val id = dataDao.insertMobileData(mobileData)
        val quarterData1 = QuarterData(1, 10.3, "2018-Q1", id.toInt(), false)
        val quarterData2 = QuarterData(1, 10.4, "2018-Q2", id.toInt(), false)
        val quarterData3 = QuarterData(1, 10.5, "2018-Q3", id.toInt(), false)
        val quarterData4 = QuarterData(1, 10.3, "2018-Q4", id.toInt(), true)

        dataDao.insertQuarter(quarterData1)
        dataDao.insertQuarter(quarterData2)
        dataDao.insertQuarter(quarterData3)
        dataDao.insertQuarter(quarterData4)
        val value = mainViewModel.dataList.getOrAwaitValue()
        assertThat(value.data, not(nullValue()))
    }

}