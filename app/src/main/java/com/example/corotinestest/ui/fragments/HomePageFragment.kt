package com.example.corotinestest.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.corotinestest.R
import com.example.corotinestest.core.model.NewsCategoryTitleModel
import com.example.corotinestest.core.model.trnews.Article
import com.example.corotinestest.ui.adapter.BaseAdapter
import com.example.corotinestest.ui.adapter.ISetItemView
import com.example.corotinestest.ui.viewmodel.HomePageViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home_page.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomePageFragment : Fragment(R.layout.fragment_home_page) {

    private var adapter: BaseAdapter<NewsCategoryTitleModel>? = null
    private var adapterNews: BaseAdapter<Article>? = null
    private var newsList: ArrayList<Article> = ArrayList()
    val categoryList = mutableListOf<NewsCategoryTitleModel>()
    private var navController: NavController? = null
    private val homeViewModel by viewModel<HomePageViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        //setAdapter()
        newsAdapter()
        setList()
        //  observeNews()
//        lifecycleScope.launch {
//            homeViewModel.getHomeNews()
//        }
        // newsAdapter()

    }

    private fun setList() {
        categoryList.add(NewsCategoryTitleModel("Sozcu", 0))
        categoryList.add(NewsCategoryTitleModel("Cumhuriyet", 1))
        categoryList.add(NewsCategoryTitleModel("Hurriyet", 2))
        categoryList.add(NewsCategoryTitleModel("Milliyet", 3))
        categoryList.add(NewsCategoryTitleModel("HaberTurk", 4))
    }

    /*private fun setAdapter() {

        adapter = BaseAdapter(
            requireContext(),
            R.layout.row_item_category,
            categoryList as ArrayList<NewsCategoryTitleModel>
        )
        adapter!!.setItemView(object : ISetItemView<NewsCategoryTitleModel> {
            override fun setItemView(v: View?, item: NewsCategoryTitleModel, position: Int) {
                val categorytitle = v!!.findViewById<TextView>(R.id.categoryTitle)
                categorytitle.text = item.categoryTitle
            }

        })
        adapter!!.setiOnRecyclerViewItemClickListener(object :
            IOnRecyclerViewItemClickListener<NewsCategoryTitleModel> {
            override fun onRecyclerItemListener(
                v: View?,
                item: NewsCategoryTitleModel,
                position: Int
            ) {
                when (position) {
                    0 -> navController?.navigate(R.id.action_homePageFragment_to_sozcuFragment)
                    1 -> navController?.navigate(R.id.action_homePageFragment_to_cumhuriyetFragment)
                    2 -> navController?.navigate(R.id.action_homePageFragment_to_hurriyetFragment)
                    3 -> navController?.navigate(R.id.action_homePageFragment_to_milliyetFragment)
                    4 -> navController?.navigate(R.id.action_homePageFragment_to_haberTurkFragment)
                }
            }
        })
        recyclerCategory.adapter = adapter
        adapter!!.setList(categoryList)
    }*/

    fun ClickControl() {

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
    }

}