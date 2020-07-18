package com.zuhaibahmad.flixview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zuhaibahmad.flixview.R
import com.zuhaibahmad.flixview.data.Content
import com.zuhaibahmad.flixview.adapters.ContentAdapter.ContentViewHolder
import com.zuhaibahmad.flixview.views.CustomHorizontalGridView
import kotlinx.android.synthetic.main.list_item_content.view.*

class ContentAdapter(
    private val items: MutableList<Content> = mutableListOf()
) : RecyclerView.Adapter<ContentViewHolder>() {

    private var recyclerView: CustomHorizontalGridView? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView as CustomHorizontalGridView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_content, parent, false)
        return ContentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = 100

    fun getItem(index: Int) = items[index % items.size]

    inner class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Content) {
            itemView.tvTitle.text = item.label
            Glide.with(itemView.context)
                .asBitmap()
                .load(item.imageUrl)
                .into(itemView.ivThumbnail)
        }
    }
}