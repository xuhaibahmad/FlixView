package com.zuhaibahmad.netflixgriddemo.views

import androidx.leanback.widget.Presenter
import androidx.leanback.widget.PresenterSelector
import com.zuhaibahmad.netflixgriddemo.data.BrowseItem
import com.zuhaibahmad.netflixgriddemo.data.Icon
import com.zuhaibahmad.netflixgriddemo.data.Thumbnail

class ContentPresenterSelector: PresenterSelector() {

    private val presenters: HashMap<BrowseItem, Presenter> = HashMap()

    override fun getPresenter(item: Any): Presenter {
        val browseItem = item as BrowseItem
        var presenter = presenters[browseItem]
        if (presenter == null) {
            presenter = when (browseItem) {
                is BrowseItem.Banner -> BannerPresenter()
                is Thumbnail -> SectionPresenter()
                is Icon -> ActionsPresenter()
                else -> throw IllegalArgumentException("Unknown item ${item.javaClass.simpleName}")
            }
        }
        presenters[browseItem] = presenter
        return presenter
    }
}
