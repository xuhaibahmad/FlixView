package com.zuhaibahmad.netflixgriddemo.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zuhaibahmad.netflixgriddemo.R
import com.zuhaibahmad.netflixgriddemo.leanback.data.BrowseItem
import kotlinx.android.synthetic.main.list_item_content.view.tvTitle
import kotlinx.android.synthetic.main.list_item_section.view.*
import kotlinx.android.synthetic.main.main_fragment.*

class SectionRowAdapter(
    private val items: MutableList<BrowseItem.Section> = mutableListOf()
) : RecyclerView.Adapter<SectionRowAdapter.SectionViewHolder>() {

    private val adapters = HashMap<Int, ContentAdapter>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_section, parent, false)
        return SectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        val adapter = ContentAdapter(getItem(position).items.toMutableList())
        adapters[position] = adapter
        holder.bind(getItem(position), adapter)
    }

    override fun getItemCount(): Int = items.size

    private fun getItem(index: Int): BrowseItem.Section = items[index]

    fun setSelectedItemPosition(position: Int) {
        Log.e("SectionRowAdapter", "Selected Category: ${getItem(position).category}")
        adapters.values.forEach { it.updateFocus(false) }
        adapters[position]?.updateFocus(true)
    }

    inner class SectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(
            item: BrowseItem.Section,
            adapter: ContentAdapter
        ) {
            itemView.tvTitle.text = item.category
            itemView.rvContent.layoutManager = LinearLayoutManager(
                itemView.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            itemView.rvContent.adapter = adapter
            itemView.rvContent.isNestedScrollingEnabled = false
            itemView.rvContent.setOnChildSelectedListener { parent, view, position, id ->
                Log.e("SectionRowAdapter", "Content Pos: $position")
                adapter.updateFocus(true)
            }
        }
    }
}