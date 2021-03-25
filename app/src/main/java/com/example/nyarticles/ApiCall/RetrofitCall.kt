package com.example.nyarticles.ApiCall

import com.example.nyarticles.GlobalResources.GlobalStrings
import com.example.nyarticles.Request.PopularArticlesRequest
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitCall {
    companion object{
         private fun getRetrofitInstance():Retrofit{
            var ret:Retrofit= Retrofit.Builder()
                    .baseUrl(GlobalStrings.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

            return ret
        }
        fun getPopularArticlesRequest():PopularArticlesRequest{
            return getRetrofitInstance().create(PopularArticlesRequest::class.java)
        }
    }

}