package com.example.corotinestest.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.corotinestest.R
import com.example.corotinestest.core.model.NewsCategoryTitleModel
import com.example.corotinestest.core.model.trnews.Article
import com.example.corotinestest.ui.adapter.BaseAdapter
import com.example.corotinestest.ui.viewmodel.HomePageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomePageFragment : Fragment(R.layout.fragment_home_page) {

    private var adapter: BaseAdapter<NewsCategoryTitleModel>? = null
    private var adapterNews: BaseAdapter<Article>? = null
    private var newsList: ArrayList<Article> = ArrayList()
    val categoryList = mutableListOf<NewsCategoryTitleModel>()
    private val homeViewModel by viewModel<HomePageViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

/*    private fun newsAdapter() {

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

    }

    private fun observeNews() {
        homeViewModel.loading.observe(viewLifecycleOwner, {
            if (it)
                Toast.makeText(requireContext(), "yukleniyor", Toast.LENGTH_LONG).show()
            else
                Toast.makeText(requireContext(), "yuklendi", Toast.LENGTH_LONG).show()
        })
        homeViewModel.resultResponse.observe(viewLifecycleOwner, {
            newsList = it.articles
            adapterNews?.setList(newsList)
            Log.e("a", "${it.toString()}")
            //adapterNews!!.setList(it.articles)
        })
        homeViewModel.responseErrorModel.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), "Veriler Cekilemedi", Toast.LENGTH_SHORT).show()
        })
    }*/

}