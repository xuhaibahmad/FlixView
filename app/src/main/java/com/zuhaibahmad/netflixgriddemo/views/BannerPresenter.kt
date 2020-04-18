package com.zuhaibahmad.netflixgriddemo.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.zuhaibahmad.netflixgriddemo.R
import com.zuhaibahmad.netflixgriddemo.data.BrowseItem
import kotlinx.android.synthetic.main.list_item_banner.view.*

class BannerPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_banner, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        val action = item as BrowseItem.Banner
        val itemView = viewHolder.view
        Glide.with(itemView.context)
            .asBitmap()
            .load(action.imageUrl)
            .fitCenter()
            .into(itemView.ivBanner);
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
        // No-op
    }
}
