package com.zuhaibahmad.flixview.views

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import androidx.leanback.widget.VerticalGridView
import com.zuhaibahmad.flixview.adapters.SectionRowAdapter

class CustomVerticalGridView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : VerticalGridView(context, attributeSet, defStyle) {

    private val sectionAdapter by lazy { (adapter as SectionRowAdapter) }

    override fun onFinishInflate() {
        super.onFinishInflate()
        this.smoothScrollToPosition(1)
        this.setOnChildSelectedListener { _, _, position, _ ->
            SectionRowAdapter.currentRow = position
            sectionAdapter.notifyViewHolderUpdated(position)
        }
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean = when (event.keyCode) {
        KeyEvent.KEYCODE_DPAD_UP -> scrollUp()
        KeyEvent.KEYCODE_DPAD_DOWN -> scrollDown()
        else -> super.dispatchKeyEvent(event)
    }

    private fun scrollUp(): Boolean = SectionRowAdapter.currentRow <= 1

    private fun scrollDown(): Boolean = SectionRowAdapter.currentRow >= adapter!!.itemCount - 2
}