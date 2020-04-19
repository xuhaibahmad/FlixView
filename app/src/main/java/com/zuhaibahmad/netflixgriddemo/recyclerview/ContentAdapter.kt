package com.zuhaibahmad.netflixgriddemo.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zuhaibahmad.netflixgriddemo.R
import com.zuhaibahmad.netflixgriddemo.leanback.data.Thumbnail
import com.zuhaibahmad.netflixgriddemo.recyclerview.ContentAdapter.ContentViewHolder
import kotlinx.android.synthetic.main.list_item_section.view.*

class ContentAdapter(
    private val items: MutableList<Thumbnail> = mutableListOf()
) : RecyclerView.Adapter<ContentViewHolder>() {

    private var selectedItem = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_section, parent, false)
        return ContentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(items[position])
        Log.e("ContentAdapter", "Selected: $selectedItem == Current: $position")
        holder.itemView.vSelection.isVisible = selectedItem == position
    }

    fun setSelecteditem(selecteditem: Int) {
        selectedItem = selecteditem
        notifyDataSetChanged()
    }

    fun update(list: List<Thumbnail>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Thumbnail) {
            itemView.tvTitle.text = item.label
            Glide.with(itemView.context)
                .asBitmap()
                .load(item.imageUrl)
                .into(itemView.ivThumbnail);
        }
    }
}