package com.zuhaibahmad.flixview.leanback.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.zuhaibahmad.flixview.R
import com.zuhaibahmad.flixview.leanback.data.Thumbnail
import kotlinx.android.synthetic.main.list_item_content.view.*

class SectionPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        val action = item as Thumbnail
        val itemView = viewHolder.view
        itemView.tvTitle.text = action.label
        Glide.with(itemView.context)
            .asBitmap()
            .load(action.imageUrl)
            .into(itemView.ivThumbnail);
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
        // No-op
    }
}
