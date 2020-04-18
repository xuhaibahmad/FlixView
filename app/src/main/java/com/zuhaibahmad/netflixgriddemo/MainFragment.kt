package com.zuhaibahmad.netflixgriddemo

import android.os.Bundle
import android.os.Handler
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.*
import com.zuhaibahmad.netflixgriddemo.data.BrowseItem
import com.zuhaibahmad.netflixgriddemo.utils.FakeDataFactory
import com.zuhaibahmad.netflixgriddemo.views.ContentPresenterSelector


class MainFragment : RowsSupportFragment() {

    private val presenterSelector = ContentPresenterSelector()
    private val rowAdapter = ArrayObjectAdapter(ListRowPresenter())

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUi()
        setupRowAdapter()
    }

    private fun setupUi() {
        setExpand(true)
    }

    private fun setupRowAdapter() {
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

    private fun createCardRow(item: BrowseItem): Row = when (item) {
        is BrowseItem.Banner -> {
            val listRowAdapter = ArrayObjectAdapter(presenterSelector)
            listRowAdapter.add(item)
            ListRow(listRowAdapter)
        }
        is BrowseItem.Section -> {
            val listRowAdapter = ArrayObjectAdapter(presenterSelector)
            for (card in item.items) {
                listRowAdapter.add(card)
            }
            ListRow(HeaderItem(item.category), listRowAdapter)
        }
        is BrowseItem.Actions -> {
            val listRowAdapter = ArrayObjectAdapter(presenterSelector)
            for (card in item.items) {
                listRowAdapter.add(card)
            }
            ListRow(HeaderItem(item.header), listRowAdapter)
        }
        else -> throw IllegalArgumentException("Unknown item ${item.javaClass.simpleName}")
    }
}