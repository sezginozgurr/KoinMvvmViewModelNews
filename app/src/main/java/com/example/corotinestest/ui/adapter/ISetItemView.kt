package com.example.corotinestest.ui.adapter

import android.view.View

interface ISetItemView<T> {
    fun setItemView(v: View?, item: T, position: Int)
}