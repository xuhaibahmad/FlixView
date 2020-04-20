package com.zuhaibahmad.netflixgriddemo.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zuhaibahmad.netflixgriddemo.R
import com.zuhaibahmad.netflixgriddemo.leanback.data.BrowseItem
import kotlinx.android.synthetic.main.list_item_content.view.tvTitle
import kotlinx.android.synthetic.main.list_item_section.view.*

class SectionRowAdapter(
    private val items: MutableList<BrowseItem.Section> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val EMPTY_STRING = ""
        const val PADDING_VIEW = 0
        const val NORMAL_VIEW = 1
        var currentRow = 0
        var currentCol = 0
    }

    init {
        items.add(0, BrowseItem.Section("first", EMPTY_STRING, emptyList()))
        items.add(items.size, BrowseItem.Section("last", EMPTY_STRING, emptyList()))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == PADDING_VIEW) {
            val view = inflater.inflate(R.layout.list_item_section_padding, parent, false)
            PaddingViewHolder(view)
        } else {
            val view = inflater.inflate(R.layout.list_item_section, parent, false)
            SectionViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SectionViewHolder) {
            val adapter = ContentAdapter(getItem(position).items.toMutableList())
            holder.bind(getItem(position), adapter)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val id = getItem(position).id
        return if (id == "first" || id == "last") PADDING_VIEW else NORMAL_VIEW
    }

    override fun getItemCount(): Int = items.size

    private fun getItem(index: Int): BrowseItem.Section = items[index]

    class PaddingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class SectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(
            item: BrowseItem.Section,
            adapter: ContentAdapter
        ) {
            itemView.tvTitle.text = item.category
            itemView.rvContent.layoutManager = GridLayoutManager(
                itemView.context,
                1,
                GridLayoutManager.HORIZONTAL,
                false
            )
            itemView.rvContent.adapter = adapter
        }
    }
}