package com.example.corotinestest.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.corotinestest.R
import com.example.corotinestest.core.model.CommentResponseModelItem
import com.example.corotinestest.core.model.PhotosModelItem
import com.example.corotinestest.ui.adapter.BaseAdapter
import com.example.corotinestest.ui.adapter.ISetItemView
import com.example.corotinestest.ui.viewmodel.LastScreenViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_last_screen.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LastScreen : AppCompatActivity() {

    private val lastViewModel by viewModel<LastScreenViewModel>()
    private var adapter: BaseAdapter<CommentResponseModelItem>? = null
    private var commentsList: ArrayList<CommentResponseModelItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_last_screen)

        val list: PhotosModelItem? = intent.getParcelableExtra("detail")
        colTitle.text = list?.title
        colDate.text = list?.albumId.toString()
        val picasso = Picasso.get()
        picasso.load(list?.thumbnailUrl).into(colImage)
        observeComments()
        GlobalScope.launch {
            lastViewModel.getComments()
        }

        setAdapter()
        recyclerComments.adapter = adapter
    }

    private fun setAdapter() {
        adapter = BaseAdapter(this, R.layout.row_comments, commentsList)
        adapter!!.setItemView(object : ISetItemView<CommentResponseModelItem> {
            override fun setItemView(v: View?, item: CommentResponseModelItem, position: Int) {
                val name = v!!.findViewById(R.id.name) as TextView
                val body = v.findViewById(R.id.body) as TextView
                val email = v.findViewById(R.id.email) as TextView

                name.text = item.name
                body.text = item.body
                email.text = item.email
            }
        })

    }

    private fun observeComments() {
        lastViewModel.loading.postValue(true)
        lastViewModel.responseErrorModel.observe(this, {
            Toast.makeText(this, "Error Dondum", Toast.LENGTH_SHORT).show()
        })
        lastViewModel.resultResponse.observe(this, {
            adapter?.setList(it)
        })
        lastViewModel.loading.observe(this, {
            if (it)
                Toast.makeText(this, "Veriler yukleniyor", Toast.LENGTH_LONG).show()
            else
                Toast.makeText(this, "Veriler yuklendi", Toast.LENGTH_LONG).show()
        })
    }


}