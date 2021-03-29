package com.example.nyarticles.Views

import android.app.Activity
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nyarticles.Adapters.PopularArticlesRecycleViewAdapter
import com.example.nyarticles.GlobalResources.GlobalStrings
import com.example.nyarticles.Navigators.PopularArticlesNavigataor
import com.example.nyarticles.R
import com.example.nyarticles.Reponse.PopularArticlesResponse
import com.example.nyarticles.databinding.ActivityMainBinding
import com.example.nyarticles.viewModels.PopularArticlesViewModel
import com.mancj.materialsearchbar.MaterialSearchBar

class MainActivity : AppCompatActivity(),PopularArticlesNavigataor,MaterialSearchBar.OnSearchActionListener {
    lateinit var dataBinding:ActivityMainBinding
    lateinit var viewModel:PopularArticlesViewModel
    lateinit var recycleViewAdapter:PopularArticlesRecycleViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = PopularArticlesViewModel(this)
        dataBinding.vm = viewModel
        viewModel.loadPopularArticlesByperiod(GlobalStrings.DEFAULT_ARTICLES_PERIOD)


        dataBinding.articlesRecycleView.layoutManager = LinearLayoutManager(this)

        dataBinding.articlesRecycleView.setHasFixedSize(true)
        dataBinding.articlesRecycleView.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)


        dataBinding.searchView.setOnSearchActionListener(this)
        dataBinding.swipeRefreshLayout.setOnRefreshListener {
            dataBinding.loadingLinearLayout.visibility = View.VISIBLE
            viewModel.loadPopularArticlesByperiod()
        }
    }

    override fun onCallPopularArticlesApi(body: PopularArticlesResponse) {
        recycleViewAdapter = PopularArticlesRecycleViewAdapter(body)
        dataBinding.articlesRecycleView.adapter = recycleViewAdapter
        dataBinding.swipeRefreshLayout.isRefreshing = false
        recycleViewAdapter.notifyDataSetChanged()

        dataBinding.loadingLinearLayout.visibility = View.GONE

    }

    override fun onErrorCallApi() {
        Toast.makeText(this,resources.getString(R.string.error_message),Toast.LENGTH_SHORT).show()
    }


    override fun onSearchStateChanged(enabled: Boolean) {

    }

    override fun onSearchConfirmed(text: CharSequence?) {
        viewModel.loadPopularArticlesByperiod(text.toString())

        val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = this.currentFocus

        // close keyboard
        imm.hideSoftInputFromWindow(view!!.windowToken, 0)

    }

    override fun onButtonClicked(buttonCode: Int) {

    }
}