package com.zuhaibahmad.netflixgriddemo.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zuhaibahmad.netflixgriddemo.R
import com.zuhaibahmad.netflixgriddemo.leanback.data.BrowseItem
import com.zuhaibahmad.netflixgriddemo.leanback.data.Thumbnail
import kotlinx.android.synthetic.main.list_item_content.view.tvTitle
import kotlinx.android.synthetic.main.list_item_section.view.*

typealias OnChildSelectedListener = (Int, Thumbnail) -> Unit
typealias OnChildClickedListener = (Int, Thumbnail) -> Unit

class SectionRowAdapter(
    private val items: MutableList<BrowseItem.Section> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val EMPTY_STRING = ""
        const val PADDING_VIEW = 0
        const val NORMAL_VIEW = 1
        const val FIRST = "first"
        const val LAST = "last"
        var currentRow = 0
    }

    private val viewHolders = HashMap<Int, SectionViewHolder>()
    private var childSelectedListener: OnChildSelectedListener? = null
    private var childClickedListener: OnChildClickedListener? = null

    init {
        items.add(0, BrowseItem.Section(FIRST, EMPTY_STRING, emptyList()))
        items.add(items.size, BrowseItem.Section(LAST, EMPTY_STRING, emptyList()))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == PADDING_VIEW) {
            val view = inflater.inflate(R.layout.list_item_section_padding, parent, false)
            PaddingViewHolder(view)
        } else {
            val view = inflater.inflate(R.layout.list_item_section, parent, false)
            view.rvContent.setOnItemViewSelectedListener { i, thumbnail ->
                childSelectedListener?.invoke(i, thumbnail)
            }
            view.rvContent.setOnItemViewClickedListener { i, thumbnail ->
                childClickedListener?.invoke(i, thumbnail)
            }
            SectionViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SectionViewHolder) {
            val adapter = ContentAdapter(getItem(position).items.toMutableList())
            viewHolders[position] = holder
            holder.bind(getItem(position), adapter)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val id = getItem(position).id
        return if (id == FIRST || id == LAST) PADDING_VIEW else NORMAL_VIEW
    }

    override fun getItemCount(): Int = items.size

    private fun getItem(index: Int): BrowseItem.Section = items[index]

    fun setOnChildSelectedListener(listener: OnChildSelectedListener) {
        this.childSelectedListener = listener
    }

    fun setOnChildClickedListener(listener: OnChildClickedListener) {
        this.childClickedListener = listener
    }

    fun notifyViewHolderUpdated(position: Int) {
        viewHolders[position]?.itemView?.rvContent?.updateSelectedPosition()
    }

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