package com.example.nyarticles.Views

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.PressBackAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.nyarticles.Adapters.PopularArticlesRecycleViewAdapter
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.nyarticles.R
import com.example.nyarticles.esspressoTest.EsspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{
    @get:Rule val mainActivityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun registerIdlingResource(){
        IdlingRegistry.getInstance().register(EsspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unRegisterIdlingResource(){
        IdlingRegistry.getInstance().unregister(EsspressoIdlingResource.countingIdlingResource)
    }

   // test recycle view visibility
    @Test
    fun test_RecycleView_Visibilty(){
        onView(withId(R.id.articlesRecycleView)).check(matches(isDisplayed()))
    }



    // test first 5 elements detailes activity is visible
    @Test
    fun test_selectListItem_isDetailesActivityDisplayed(){
        for(i in 0..4){
            onView(withId(R.id.articlesRecycleView))
                .perform(actionOnItemAtPosition<PopularArticlesRecycleViewAdapter.Companion.PopularArticlesViwHolder>(i,click()))


            onView(withId(R.id.articleItemImageView)).check(matches(
                withEffectiveVisibility(Visibility.VISIBLE)))

            onView(withId(R.id.articleDetailesTitleTextView)).check(matches(
                withEffectiveVisibility(Visibility.VISIBLE)))

            onView(withId(R.id.articleDetailesAbstractTextView)).check(matches(
                withEffectiveVisibility(Visibility.VISIBLE)))
            pressBack()
        }



    }
}