package com.example.corotinestest.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.corotinestest.R
import com.example.corotinestest.core.model.NewsCategoryTitleModel
import com.example.corotinestest.core.model.trnews.Article
import com.example.corotinestest.ui.adapter.BaseAdapter
import com.example.corotinestest.ui.viewmodel.HomePageViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class CumhuriyetFragment : Fragment(R.layout.fragment_cumhuriyet) {


    private var adapter: BaseAdapter<NewsCategoryTitleModel>? = null
    private var adapterNews: BaseAdapter<Article>? = null
    private var newsList: ArrayList<Article> = ArrayList()
    val categoryList = mutableListOf<NewsCategoryTitleModel>()
    private val homeViewModel by viewModel<HomePageViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setAdapter()
        setList()
        observeNews()
        lifecycleScope.launch {
            homeViewModel.getHomeNews()
        }
        //newsAdapter()

        //navController = Navigation.findNavController(view)
    }

    private fun setList() {
        categoryList.add(NewsCategoryTitleModel("Sozcu", 0))
        categoryList.add(NewsCategoryTitleModel("Cumhuriyet", 1))
        categoryList.add(NewsCategoryTitleModel("Hurriyet", 2))
        categoryList.add(NewsCategoryTitleModel("Milliyet", 3))
        categoryList.add(NewsCategoryTitleModel("HaberTurk", 4))
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