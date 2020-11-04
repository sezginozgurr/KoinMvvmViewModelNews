package com.example.corotinestest.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.corotinestest.R
import com.example.corotinestest.core.model.trnews.Article
import com.example.corotinestest.databinding.FragmentHurriyetBinding
import com.example.corotinestest.ui.adapter.BaseAdapter
import com.example.corotinestest.ui.adapter.ISetItemView
import com.example.corotinestest.ui.viewmodel.HomePageViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_hurriyet.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HurriyetFragment : androidx.fragment.app.Fragment(R.layout.fragment_hurriyet) {

    private lateinit var binding: FragmentHurriyetBinding
    private val hurriyetViewModel by viewModel<HomePageViewModel>()
    private var adapterNews: BaseAdapter<Article>? = null
    private var newsList: ArrayList<Article> = ArrayList()
    private var searchList: ArrayList<Article> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeNews()
        lifecycleScope.launch {
            hurriyetViewModel.getHomeNews()
        }
        newsAdapter()
    }

    private fun newsAdapter() {

        adapterNews = BaseAdapter(requireContext(), R.layout.row_item_home_news, newsList)
        adapterNews!!.setItemView(object : ISetItemView<Article> {
            override fun setItemView(v: View?, item: Article, position: Int) {
                val newsImage: ImageView = v!!.findViewById(R.id.newsImage)
                val newsTitle: TextView = v.findViewById(R.id.newsTitle)
                val newsDetail: TextView = v.findViewById(R.id.newsDetail)
                val newsPublish: TextView = v.findViewById(R.id.publishAt)
                val company: TextView = v.findViewById(R.id.company)

                Picasso.get().load(item.urlToImage).into(newsImage)

                newsTitle.text = item.title
                newsDetail.text = item.description
                newsPublish.text = item.publishedAt
                company.text = item.source.name
            }

        })
        recyclerHomeNews?.adapter = adapterNews

    }

    private fun observeNews() {
        hurriyetViewModel.loading.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Yukleniyor", Toast.LENGTH_SHORT).show()
        }
        hurriyetViewModel.resultResponse.observe(viewLifecycleOwner, {
            newsList = it.articles
            adapterNews!!.setList(newsList)
            //adapterNews!!.setList(it.articles)
        })
        hurriyetViewModel.responseErrorModel.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), "Veriler Cekilemedi", Toast.LENGTH_SHORT).show()
        })
    }
}