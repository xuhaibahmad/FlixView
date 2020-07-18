package com.zuhaibahmad.flixview.leanback.utils

import androidx.leanback.widget.ArrayObjectAdapter
import com.zuhaibahmad.flixview.leanback.views.ContentPresenterSelector

class CircularArrayObjectAdapter(
    presenterSelector: ContentPresenterSelector,
    val items: List<Any>
) : ArrayObjectAdapter(presenterSelector) {

    override fun size(): Int = items.size * 100

    override fun get(index: Int): Any? = items[index % items.size]
}