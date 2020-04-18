package com.zuhaibahmad.netflixgriddemo

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.*
import com.zuhaibahmad.netflixgriddemo.data.BrowseItem
import com.zuhaibahmad.netflixgriddemo.data.Icon
import com.zuhaibahmad.netflixgriddemo.data.Thumbnail
import com.zuhaibahmad.netflixgriddemo.utils.CircularArrayObjectAdapter
import com.zuhaibahmad.netflixgriddemo.utils.FakeDataFactory
import com.zuhaibahmad.netflixgriddemo.views.ContentPresenterSelector


class MainFragment : RowsSupportFragment() {

    private val rowAdapter = ArrayObjectAdapter(ListRowPresenter())

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = rowAdapter
        setOnItemViewClickedListener { itemViewHolder, item, rowViewHolder, row ->
            if (item is BrowseItem.Banner) return@setOnItemViewClickedListener
            val message = when (item) {
                is Icon -> "${item.label} clicked!"
                is Thumbnail -> "${item.label} clicked!"
                else -> "Unknown item $item clicked!"
            }
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
        Handler().postDelayed({
            createRows()
        }, 500)
    }

    private fun createRows() {
        for (browseItem in FakeDataFactory.getFakeData()) {
            rowAdapter.add(createCardRow(browseItem))
        }
    }

    private fun createCardRow(item: BrowseItem): Row {
        val presenterSelector = ContentPresenterSelector()
        return when (item) {
            is BrowseItem.Banner -> createBannerRow(presenterSelector, item)
            is BrowseItem.Section -> createSectionRow(presenterSelector, item)
            is BrowseItem.Actions -> createActionsRow(presenterSelector, item)
            else -> throw IllegalArgumentException("Unknown item ${item.javaClass.simpleName}")
        }
    }

    private fun createActionsRow(
        presenterSelector: ContentPresenterSelector,
        item: BrowseItem.Actions
    ): ListRow {
        val actionsAdapter = ArrayObjectAdapter(presenterSelector)
        for (card in item.items) {
            actionsAdapter.add(card)
        }
        return ListRow(HeaderItem(item.header), actionsAdapter)
    }

    private fun createSectionRow(
        presenterSelector: ContentPresenterSelector,
        item: BrowseItem.Section
    ): ListRow {
        val sectionAdapter = CircularArrayObjectAdapter(presenterSelector, item.items)
        for (card in item.items) {
            sectionAdapter.add(card)
        }
        return ListRow(HeaderItem(item.category), sectionAdapter)
    }

    private fun createBannerRow(
        presenterSelector: ContentPresenterSelector,
        item: BrowseItem
    ): ListRow {
        val bannerAdapter = ArrayObjectAdapter(presenterSelector)
        bannerAdapter.add(item)
        return ListRow(bannerAdapter)
    }
}