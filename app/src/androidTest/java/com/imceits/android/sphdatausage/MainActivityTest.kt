package com.imceits.android.sphdatausage

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.imceits.android.sphdatausage.ui.MainActivity
import org.junit.After
import org.junit.Before

import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainActivityTest {
    @Rule
        val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)
    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }
}