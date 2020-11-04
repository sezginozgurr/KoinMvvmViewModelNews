package com.example.corotinestest.ui.util

import com.example.corotinestest.core.model.FragmentModel
import com.example.corotinestest.ui.fragments.MilliyetFragment


object MockData {
    fun getNewsCategoryFragment(): List<FragmentModel> {
        val fragmentList = ArrayList<FragmentModel>()

        repeat(getTitleList().size) { position ->

            val milliyetFragment = MilliyetFragment()


            val fragmentModel4 = FragmentModel(5, getTitleList()[position], milliyetFragment)


            fragmentList.add(fragmentModel4)
        }

        return fragmentList
    }

    private fun getTitleList(): List<String> {
        val titleList = ArrayList<String>()
        titleList.add("ANASAYFA")
        titleList.add("SABAH")
        titleList.add("MILLIYET")
        titleList.add("HURRIYET")
        titleList.add("SOZCU")
        titleList.add("HABERTURK")
        titleList.add("CUMHURIYET")
        return titleList
    }
}