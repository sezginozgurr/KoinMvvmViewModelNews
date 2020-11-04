package com.example.corotinestest.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class CategoryNewsAdapter(
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(
    fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    private val newsFragmentList = ArrayList<Fragment>()
    private val newsFragmentTitle = ArrayList<String>()

    override fun getCount(): Int = newsFragmentList.size

    override fun getItem(position: Int): Fragment = newsFragmentList[position]

    override fun getPageTitle(position: Int): CharSequence? = newsFragmentTitle[position]

    fun addFragment(fragment: Fragment, title: String) {
        newsFragmentList.add(fragment)
        newsFragmentTitle.add(title)
    }

}