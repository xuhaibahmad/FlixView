package com.zuhaibahmad.netflixgriddemo.leanback.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.zuhaibahmad.netflixgriddemo.R
import com.zuhaibahmad.netflixgriddemo.leanback.data.Icon
import kotlinx.android.synthetic.main.list_item_actions.view.*

class ActionsPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_actions, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        val action = item as Icon
        val itemView = viewHolder.view
        itemView.tvTitle.text = action.label
        Glide.with(itemView.context)
            .asBitmap()
            .load(action.resId)
            .into(itemView.ivIcon);
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
        // No-op
    }
}
