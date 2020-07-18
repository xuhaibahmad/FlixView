package com.zuhaibahmad.flixview.leanback

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.*
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.request.transition.ViewPropertyTransition
import com.zuhaibahmad.flixview.leanback.data.BrowseItem
import com.zuhaibahmad.flixview.leanback.data.Icon
import com.zuhaibahmad.flixview.leanback.data.Thumbnail
import com.zuhaibahmad.flixview.leanback.utils.CircularArrayObjectAdapter
import com.zuhaibahmad.flixview.leanback.utils.FakeDataFactory
import com.zuhaibahmad.flixview.leanback.views.ContentPresenterSelector


interface BannerScreen {
    fun updateBanner(url: String? = null)
}

class LeanbackFragment : RowsSupportFragment() {

    private val rowAdapter = ArrayObjectAdapter(ListRowPresenter(FocusHighlight.ZOOM_FACTOR_NONE, false))
    private val parentScreen by lazy { requireActivity() as BannerScreen }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = rowAdapter
        setOnItemViewClickedListener { _, item, _, _ ->
            val message = when (item) {
                is Icon -> "${item.label} clicked!"
                is Thumbnail -> "${item.label} clicked!"
                else -> "Unknown item $item clicked!"
            }
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
        setOnItemViewSelectedListener { _, item, _, _ ->
            if (item is Thumbnail) parentScreen.updateBanner(url = item.featuredImageUrl)
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
}