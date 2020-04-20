package com.zuhaibahmad.netflixgriddemo.recyclerview.views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import androidx.leanback.widget.VerticalGridView
import com.zuhaibahmad.netflixgriddemo.recyclerview.SectionRowAdapter

@Suppress("DEPRECATION")
class CustomVerticalGridView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : VerticalGridView(context, attributeSet, defStyle) {

    override fun onFinishInflate() {
        super.onFinishInflate()
        this.smoothScrollToPosition(1)
        this.setOnChildSelectedListener { _, _, position, _ ->
            SectionRowAdapter.currentRow = position
            Log.e("CVGV", "Updating current row")
        }
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        return when (event.keyCode) {
            KeyEvent.KEYCODE_DPAD_UP -> scrollUp()
            KeyEvent.KEYCODE_DPAD_DOWN -> scrollDown()
            else -> super.dispatchKeyEvent(event)
        }
    }

    private fun scrollUp(): Boolean {
        Log.e("CVGV", "V Scroll Up")
        return SectionRowAdapter.currentRow <= 1
    }

    private fun scrollDown(): Boolean {
        Log.e("CVGV", "V Scroll Down")
        return SectionRowAdapter.currentRow >= adapter!!.itemCount - 2
    }
}