package com.example.corotinestest.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.corotinestest.R
import com.example.corotinestest.core.model.NewsCategoryTitleModel
import com.example.corotinestest.core.model.trnews.Article
import com.example.corotinestest.ui.adapter.BaseAdapter
import com.example.corotinestest.ui.viewmodel.HomePageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CumhuriyetFragment : Fragment(R.layout.fragment_sabah) {

    private var adapter: BaseAdapter<NewsCategoryTitleModel>? = null
    private var adapterNews: BaseAdapter<Article>? = null
    private var newsList: ArrayList<Article> = ArrayList()
    val categoryList = mutableListOf<NewsCategoryTitleModel>()
    private var navController: NavController? = null
    private val homeViewModel by viewModel<HomePageViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

}