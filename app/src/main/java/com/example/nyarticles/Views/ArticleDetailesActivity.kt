package com.example.nyarticles.Views


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.nyarticles.GlobalResources.GlobalStrings
import com.example.nyarticles.R
import com.example.nyarticles.databinding.ActivityArticlesDetailesBinding
import com.example.nyarticles.viewModels.PopluarArticlesDetailesViewModel
import com.squareup.picasso.Picasso

class ArticleDetailesActivity : AppCompatActivity() {

    lateinit var dataBinding:ActivityArticlesDetailesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val imageUrl = intent.extras?.getString(GlobalStrings.ARTICLE_IMAGE_KEY)
        val imageTitle = intent.extras!!.getString(GlobalStrings.ARTICLE_TITLE_KEY)
        val imageAbstract = intent.extras!!.getString(GlobalStrings.ARTICLE_ABSTRACT_KEY)

        val viewModel = PopluarArticlesDetailesViewModel(imageUrl,imageTitle!!,imageAbstract!!)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_articles_detailes)
        dataBinding.vm = viewModel

        Picasso.get()
                .load(imageUrl)
                .into(dataBinding.articleItemImageView)

    }
}