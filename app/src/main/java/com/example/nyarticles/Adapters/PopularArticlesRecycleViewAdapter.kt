package com.example.nyarticles.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nyarticles.GlobalResources.GlobalStrings
import com.example.nyarticles.R
import com.example.nyarticles.Reponse.PopularArticlesResponse
import com.example.nyarticles.Views.ArticleDetailesActivity
import com.example.nyarticles.databinding.ArticlesRecycleviewLayoutBinding
import com.example.nyarticles.viewModels.PopularArticlesItemViewModel
import com.example.nyarticles.viewModels.PopularArticlesViewModel
import com.squareup.picasso.Picasso

class PopularArticlesRecycleViewAdapter(popularArticlesResponse: PopularArticlesResponse)
    : RecyclerView.Adapter<PopularArticlesRecycleViewAdapter.Companion.PopularArticlesViwHolder>() {
    var articles:PopularArticlesResponse = popularArticlesResponse


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularArticlesViwHolder {
        var itemBinding:ArticlesRecycleviewLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.articles_recycleview_layout,parent,false)

        var myHolder = PopularArticlesViwHolder(itemBinding.root,itemBinding)

        val height = parent.measuredHeight / 3
        myHolder.itemView.layoutParams.height = height
        return myHolder
    }

    override fun onBindViewHolder(holder: PopularArticlesViwHolder, position: Int) {
        val viewModel = PopularArticlesItemViewModel(articles.results[position].title,
                "By "+articles.results[position].source,articles.results[position].published_date)

        holder.articlesRecycleviewLayoutBinding.vm = viewModel

        holder.articlesRecycleviewLayoutBinding.mainActivityLayout.setOnClickListener {

            var intent =Intent(it.context,ArticleDetailesActivity::class.java)
            if(articles.results[position].media.isNotEmpty())
                intent.putExtra(GlobalStrings.ARTICLE_IMAGE_KEY,articles.results[position]?.media[0].mediaMetadata[2].url)
            intent.putExtra(GlobalStrings.ARTICLE_TITLE_KEY,articles.results[position].title)
            intent.putExtra(GlobalStrings.ARTICLE_ABSTRACT_KEY,articles.results[position].abstract)
            it.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {

       return articles.results.count()
    }






    companion object{
        class PopularArticlesViwHolder(itemview:View, articlesRecycleviewLayoutBinding: ArticlesRecycleviewLayoutBinding )
            :RecyclerView.ViewHolder(itemview){
                var articlesRecycleviewLayoutBinding = articlesRecycleviewLayoutBinding
            }
    }


}