package com.example.nyarticles.Navigators

import com.example.nyarticles.Reponse.PopularArticlesResponse

interface PopularArticlesNavigataor {
    fun onCallPopularArticlesApi(body:PopularArticlesResponse)
}