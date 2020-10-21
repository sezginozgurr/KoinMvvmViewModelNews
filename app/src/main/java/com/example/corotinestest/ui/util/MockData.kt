package com.example.corotinestest.ui.util

import com.example.corotinestest.core.model.NewsCategoryTitleModel

object MockData {
    fun getCategory(): List<NewsCategoryTitleModel> {
        val categoryList: ArrayList<NewsCategoryTitleModel> = ArrayList()
        val category = NewsCategoryTitleModel("Sozcu")
        val category1 = NewsCategoryTitleModel("Hurriyet")
        val category2 = NewsCategoryTitleModel("A Haber")
        val category3 = NewsCategoryTitleModel("Anadolu Ajans")
        val category4 = NewsCategoryTitleModel("Sabah")
        val category5 = NewsCategoryTitleModel("Son Dakika")

        categoryList.add(category)
        categoryList.add(category1)
        categoryList.add(category2)
        categoryList.add(category3)
        categoryList.add(category4)
        categoryList.add(category5)

        return categoryList
    }
}