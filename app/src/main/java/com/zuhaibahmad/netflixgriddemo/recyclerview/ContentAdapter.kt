package com.zuhaibahmad.netflixgriddemo.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zuhaibahmad.netflixgriddemo.R
import com.zuhaibahmad.netflixgriddemo.leanback.data.Thumbnail
import com.zuhaibahmad.netflixgriddemo.recyclerview.ContentAdapter.ContentViewHolder
import kotlinx.android.synthetic.main.list_item_section.view.*

class ContentAdapter(
    private val items: List<Thumbnail>
) : RecyclerView.Adapter<ContentViewHolder>() {

    private var selectedItem = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_section, parent, false)
        return ContentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setSelecteditem(selecteditem: Int) {
        selectedItem = selecteditem
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Thumbnail) {
            val action = item as Thumbnail
            itemView.tvTitle.text = action.label
            Glide.with(itemView.context)
                .asBitmap()
                .load(action.imageUrl)
                .into(itemView.ivThumbnail);
        }
    }
}