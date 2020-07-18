package com.zuhaibahmad.flixview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zuhaibahmad.flixview.R
import com.zuhaibahmad.flixview.data.Category
import com.zuhaibahmad.flixview.data.Content
import kotlinx.android.synthetic.main.list_item_content.view.tvTitle
import kotlinx.android.synthetic.main.list_item_section.view.*

typealias OnChildSelectedListener = (Int, Content) -> Unit
typealias OnChildClickedListener = (Int, Content) -> Unit

class SectionRowAdapter(
    private val items: MutableList<Category> = mutableListOf(),
    private val itemWidth: Int = 0,
    private val itemHeight: Int = 0,
    private val titleBackgroundColor: Int = 0,
    private val titleTextColor: Int = 0,
    private val titleTextSize: Float = 0f,
    private val categoryTextColor: Int = 0
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
        items.add(0, Category(FIRST, EMPTY_STRING, emptyList()))
        items.add(items.size, Category(LAST, EMPTY_STRING, emptyList()))
    }

    fun setItems(items: List<Category>) = this.items.apply {
        clear()
        add(Category(FIRST, EMPTY_STRING, emptyList()))
        addAll(items)
        add(Category(LAST, EMPTY_STRING, emptyList()))
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
            val adapter = ContentAdapter(
                getItem(position).items.toMutableList(),
                itemWidth = itemWidth,
                itemHeight = itemHeight,
                titleBackgroundColor = titleBackgroundColor,
                titleTextColor = titleTextColor,
                titleTextSize = titleTextSize
            )
            viewHolders[position] = holder
            holder.bind(getItem(position), adapter)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val id = getItem(position).id
        return if (id == FIRST || id == LAST) PADDING_VIEW else NORMAL_VIEW
    }

    override fun getItemCount(): Int = items.size

    private fun getItem(index: Int): Category = items[index]

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

    inner class SectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Category, adapter: ContentAdapter) {
            itemView.tvTitle.apply {
                text = item.name
                if (categoryTextColor != 0) {
                    setTextColor(categoryTextColor)
                }
            }
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