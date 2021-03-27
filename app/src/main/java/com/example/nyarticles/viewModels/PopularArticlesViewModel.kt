package com.example.nyarticles.viewModels

import android.util.Log
import com.example.nyarticles.ApiCall.RetrofitCall
import com.example.nyarticles.GlobalResources.GlobalStrings
import com.example.nyarticles.Navigators.PopularArticlesNavigataor
import com.example.nyarticles.Request.PopularArticlesRequest
import com.example.nyarticles.esspressoTest.EsspressoIdlingResource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PopularArticlesViewModel(val navigator: PopularArticlesNavigataor) {

    fun loadPopularArticlesByperiod(period:String=GlobalStrings.DEFAULT_ARTICLES_PERIOD){
        EsspressoIdlingResource.increment()
        var popularArticlesRequest: PopularArticlesRequest = RetrofitCall.getPopularArticlesRequest()
        var popularArticlesResponse = popularArticlesRequest.getPopularArticlesByPeriod(period)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    Log.d("popularArticlesResponse",response.toString())
                    navigator.onCallPopularArticlesApi(response)
                    EsspressoIdlingResource.decrement()

                },{
                    err -> Log.d("popularArticlesError",err.toString())
                })
    }
}