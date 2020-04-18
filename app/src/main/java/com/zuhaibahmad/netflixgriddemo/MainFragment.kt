package com.zuhaibahmad.netflixgriddemo

import android.os.Bundle
import android.os.Handler
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.*
import com.zuhaibahmad.netflixgriddemo.data.BrowseItem
import com.zuhaibahmad.netflixgriddemo.utils.CircularArrayObjectAdapter
import com.zuhaibahmad.netflixgriddemo.utils.FakeDataFactory
import com.zuhaibahmad.netflixgriddemo.views.ContentPresenterSelector


class MainFragment : RowsSupportFragment() {

    private val rowAdapter = ArrayObjectAdapter(ListRowPresenter())

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = rowAdapter
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