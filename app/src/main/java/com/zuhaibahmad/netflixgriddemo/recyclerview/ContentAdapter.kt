package com.zuhaibahmad.netflixgriddemo.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zuhaibahmad.netflixgriddemo.R
import com.zuhaibahmad.netflixgriddemo.leanback.data.Thumbnail
import com.zuhaibahmad.netflixgriddemo.recyclerview.ContentAdapter.ContentViewHolder
import kotlinx.android.synthetic.main.list_item_content.view.*

class ContentAdapter(
    private val items: MutableList<Thumbnail> = mutableListOf()
) : RecyclerView.Adapter<ContentViewHolder>() {

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

        fun bind(item: Thumbnail) {
            itemView.tvTitle.text = item.label
            Glide.with(itemView.context)
                .asBitmap()
                .load(item.imageUrl)
                .into(itemView.ivThumbnail)
        }
    }
}