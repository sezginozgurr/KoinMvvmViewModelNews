package com.example.corotinestest.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.corotinestest.R
import com.example.corotinestest.core.model.NewsCategoryTitleModel
import com.example.corotinestest.ui.adapter.BaseAdapter
import com.example.corotinestest.ui.adapter.ISetItemView
import com.example.corotinestest.ui.util.MockData
import kotlinx.android.synthetic.main.fragment_home_page.*


class HomePageFragment : Fragment(R.layout.fragment_home_page) {

    private var adapter: BaseAdapter<NewsCategoryTitleModel>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }

    private fun setAdapter() {
        val categoryList = MockData.getCategory() as ArrayList<NewsCategoryTitleModel>
        adapter = BaseAdapter(requireContext(), R.layout.row_item_category, categoryList)
        adapter!!.setItemView(object : ISetItemView<NewsCategoryTitleModel> {
            override fun setItemView(v: View?, item: NewsCategoryTitleModel, position: Int) {
                val categorytitle = v!!.findViewById<TextView>(R.id.categoryTitle)
                categorytitle.text = item.categoryTitle
            }

        })
        recyclerCategory.adapter = adapter
        adapter!!.setList(categoryList)
    }

}