package com.example.corotinestest.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.corotinestest.R
import com.example.corotinestest.core.model.PhotosModelItem
import com.example.corotinestest.core.model.UserResponseModelItem
import com.example.corotinestest.ui.adapter.BaseAdapter
import com.example.corotinestest.ui.adapter.ISetItemView
import com.example.corotinestest.ui.viewmodel.DetailsViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailsActivity : AppCompatActivity() {

    private val detailsViewModel by viewModel<DetailsViewModel>()
    private var adapter: BaseAdapter<PhotosModelItem>? = null
    private var detailsList: ArrayList<PhotosModelItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        observeDetails()
        GlobalScope.launch {
            detailsViewModel.getDetailsArguments()
        }

        setAdapterDetails()

    }

    private fun setAdapterDetails() {
        val list: UserResponseModelItem? = intent.getParcelableExtra("key")
        adapter = BaseAdapter(this, R.layout.row_detail_item, detailsList)
        adapter!!.setItemView(object : ISetItemView<PhotosModelItem> {
            override fun setItemView(v: View?, item: PhotosModelItem, position: Int) {
                val cardDetails = v!!.findViewById(R.id.cardDetail) as CardView
                val detailsPhoto = v.findViewById(R.id.detailImage) as AppCompatImageView
                val detailsTitle = v.findViewById(R.id.detailTitle) as TextView
                val detailsEmail = v.findViewById(R.id.detailEmail) as TextView

                val picasso = Picasso.get()
                picasso.load(item.thumbnailUrl)
                    .into(detailsPhoto)
                detailsTitle.text = item.title
                detailsEmail.text = list?.email

                cardDetails.setOnClickListener {
                    val intent = Intent(this@DetailsActivity, LastScreen::class.java)
                    intent.putExtra("detail", item)
                    startActivity(intent)
                }

            }

        })
        recyclerDetail.adapter = adapter
    }

    private fun observeDetails() {
        detailsViewModel.loading.observe(this, {
            if (it)
                Toast.makeText(this, "Veriler yukleniyor", Toast.LENGTH_LONG).show()
            else
                Toast.makeText(this, "Veriler yuklendi", Toast.LENGTH_LONG).show()
        })
        detailsViewModel.resultResponse.observe(this, {
            for (i in it) {
                val url = GlideUrl(
                    "${i.thumbnailUrl}", LazyHeaders.Builder()
                        .addHeader("User-Agent", "your-user-agent")
                        .build()
                )
                i.thumbnailUrl = url.toStringUrl() + ".jpg"
            }
            adapter?.setList(it)
        })
        detailsViewModel.responseErrorModel.observe(this, {
            Toast.makeText(this, "Veriler Cekilemedi", Toast.LENGTH_SHORT).show()
        })
    }
}