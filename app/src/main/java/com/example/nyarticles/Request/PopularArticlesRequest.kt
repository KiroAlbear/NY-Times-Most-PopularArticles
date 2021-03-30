package com.example.nyarticles.Request

import android.database.Observable
import com.example.nyarticles.Reponse.PopularArticlesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PopularArticlesRequest {
    @GET("svc/mostpopular/v2/viewed/{period}.json?")
    fun getPopularArticlesByPeriod(
            @Path("period")
            period:String,
            @Query("api-key")
            apiKey:String ,

    ):io.reactivex.Observable<PopularArticlesResponse>



}