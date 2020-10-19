package com.example.corotinestest.ui.adapter

import android.view.View

interface IOnRecyclerViewItemClickListener<T> {
    fun onRecyclerItemListener(v: View?, item: T, position: Int)
}