package com.zuhaibahmad.netflixgriddemo.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zuhaibahmad.netflixgriddemo.R
import com.zuhaibahmad.netflixgriddemo.leanback.data.Thumbnail
import com.zuhaibahmad.netflixgriddemo.recyclerview.ContentAdapter.ContentViewHolder
import kotlinx.android.synthetic.main.list_item_content.view.*

class ContentAdapter(
    private val items: MutableList<Thumbnail> = mutableListOf()
) : RecyclerView.Adapter<ContentViewHolder>() {

    private var isRowFocused = false
    private var selectedItemPos = 0
    private var recyclerView: RecyclerView? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_content, parent, false)
        return ContentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(getItem(position))
        val selected = isRowFocused && position == selectedItemPos
        holder.itemView.vSelection.isVisible = selected
        Log.e("ContentAdapter", "Selected: $selected")
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = null
    }

    fun updateFocus(rowFocused: Boolean) {
        val linearLayoutManager = recyclerView?.layoutManager as LinearLayoutManager
        isRowFocused = rowFocused
        selectedItemPos = linearLayoutManager.findFirstVisibleItemPosition()
        Log.e("ContentAdapter", "Selected: ${getItem(selectedItemPos).label}")
        notifyItemRangeChanged(selectedItemPos - 1, 3)
    }

    override fun getItemCount(): Int = 100

    private fun getItem(index: Int) = items[index % items.size]

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