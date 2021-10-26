package com.example.waetherapp.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.waetherapp.R
import com.example.waetherapp.data.db.AppDataBase.Companion.DATABASE_NAME
import com.example.waetherapp.ui.kurt.MainActivity
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FirstAppLaunchTest {
    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    companion object {
        @JvmStatic
        @BeforeClass
        fun clearDatabaseBeforeFirstLaunch() {
            InstrumentationRegistry.getInstrumentation().targetContext.deleteDatabase(DATABASE_NAME)
        }
    }

    @Test
    fun firstAppLaunchOpensSearchScreen() {
        Espresso.onView(withId(R.id.enterTextHint))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}