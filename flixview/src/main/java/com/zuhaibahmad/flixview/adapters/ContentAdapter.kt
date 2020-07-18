package com.zuhaibahmad.flixview.adapters

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
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
    private val items: MutableList<Content> = mutableListOf(),
    private val itemWidth: Int = 0,
    private val itemHeight: Int = 0,
    private val titleBackgroundColor: Int = 0,
    private val titleTextColor: Int = 0,
    private val titleTextSize: Float = 0f
) : RecyclerView.Adapter<ContentViewHolder>() {

    private var recyclerView: CustomHorizontalGridView? = null

    init {
        Log.e("ContentAdapter", "Title(bg: $titleBackgroundColor, textColor: $titleTextColor, textSize: $titleTextSize)")
    }

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
        if (itemWidth != 0) {
            view.layoutParams.width = itemWidth
        }
        if (itemHeight != 0) {
            view.layoutParams.height = itemHeight
        }
        return ContentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = 100

    fun getItem(index: Int) = items[index % items.size]

    inner class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Content) {
            itemView.tvTitle.apply {
                text = item.label
                if (titleBackgroundColor != 0) {
                    background = ColorDrawable(titleBackgroundColor);
                }
                if (titleTextColor != 0) {
                    setTextColor(titleTextColor)
                }
                if (titleTextSize != 0f) {
                    textSize = titleTextSize
                }
            }
            Glide.with(itemView.context)
                .asBitmap()
                .load(item.imageUrl)
                .into(itemView.ivThumbnail)
        }
    }
}