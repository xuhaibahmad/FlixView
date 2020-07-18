package com.zuhaibahmad.flixview.leanback.views

import androidx.leanback.widget.Presenter
import androidx.leanback.widget.PresenterSelector
import com.zuhaibahmad.flixview.leanback.data.BrowseItem
import com.zuhaibahmad.flixview.leanback.data.Icon
import com.zuhaibahmad.flixview.leanback.data.Thumbnail

class ContentPresenterSelector: PresenterSelector() {

    private val presenters: HashMap<BrowseItem, Presenter> = HashMap()

    override fun getPresenter(item: Any): Presenter {
        val browseItem = item as BrowseItem
        var presenter = presenters[browseItem]
        if (presenter == null) {
            presenter = when (browseItem) {
                is Thumbnail -> SectionPresenter()
                is Icon -> ActionsPresenter()
                else -> throw IllegalArgumentException("Unknown item ${item.javaClass.simpleName}")
            }
        }
        presenters[browseItem] = presenter
        return presenter
    }
}
