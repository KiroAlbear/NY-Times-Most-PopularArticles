package com.example.nyarticles.viewModels

import android.util.Log
import com.example.nyarticles.ApiCall.RetrofitCall
import com.example.nyarticles.GlobalResources.GlobalStrings
import com.example.nyarticles.Navigators.PopularArticlesNavigataor
import com.example.nyarticles.Request.PopularArticlesRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PopularArticlesViewModel(val navigator: PopularArticlesNavigataor) {

    fun loadPopularArticlesByperiod(period:String=GlobalStrings.DEFAULT_ARTICLES_PERIOD){
        var popularArticlesRequest: PopularArticlesRequest = RetrofitCall.getPopularArticlesRequest()
        var popularArticlesResponse = popularArticlesRequest.getPopularArticlesByPeriod(period)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    Log.d("popularArticlesResponse",response.toString())
                    navigator.onCallPopularArticlesApi(response)

                },{
                    err -> Log.d("popularArticlesError",err.toString())
                })
    }
}