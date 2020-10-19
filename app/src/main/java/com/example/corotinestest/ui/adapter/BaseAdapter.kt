package com.example.corotinestest.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BaseAdapter<T>(
    private val context: Context,
    private val resourceId: Int,
    private var items: ArrayList<T>?
) : RecyclerView.Adapter<BaseAdapter<T>.ViewHolder>() {

    private var iSetItemView: ISetItemView<T>? = null
    private var iOnRecyclerViewItemClickListener: IOnRecyclerViewItemClickListener<T>? = null


    fun setItemView(iSetItemView: ISetItemView<T>?) {
        this.iSetItemView = iSetItemView
    }

    fun setiOnRecyclerViewItemClickListener(iOnRecyclerViewItemClickListener: IOnRecyclerViewItemClickListener<T>?) {
        this.iOnRecyclerViewItemClickListener = iOnRecyclerViewItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(resourceId, parent, false)
        return ViewHolder(view)
    }


    fun updateItem(position: Int, newItem: T) {
        notifyItemChanged(position, newItem)
    }

    fun setList(newlist: ArrayList<T>) {
        items = newlist
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (items != null) {
            items!!.size
        } else 0
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(
        view
    )

    fun removeAt(position: Int) {
        items?.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, items!!.size)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items!![position]
        iSetItemView!!.setItemView(holder.view, item, position)
        if (iOnRecyclerViewItemClickListener != null) holder.view.setOnClickListener {
            iOnRecyclerViewItemClickListener!!.onRecyclerItemListener(
                holder.view,
                item,
                position
            )
        }
    }


}