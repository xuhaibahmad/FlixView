package com.zuhaibahmad.netflixgriddemo.recyclerview.views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zuhaibahmad.netflixgriddemo.recyclerview.ContentAdapter
import com.zuhaibahmad.netflixgriddemo.recyclerview.utils.StartSnapHelper

class CustomHorizontalGridView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : RecyclerView(context, attributeSet, defStyle) {

    private val contentAdapter by lazy { (adapter as ContentAdapter) }
    private val snapHelper: StartSnapHelper = StartSnapHelper()

    override fun onFinishInflate() {
        super.onFinishInflate()
        snapHelper.attachToRecyclerView(this)
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        if (event.action != KeyEvent.ACTION_UP) return false
        return when (event.keyCode) {
            KeyEvent.KEYCODE_DPAD_LEFT -> scrollLeft()
            KeyEvent.KEYCODE_DPAD_RIGHT -> scrollRight()
            KeyEvent.KEYCODE_DPAD_CENTER -> scrollCenter()
            else -> super.dispatchKeyEvent(event)
        }
    }

    private fun scrollLeft(): Boolean {
        val linearLayoutManager = layoutManager as LinearLayoutManager
        val pos = linearLayoutManager.findFirstVisibleItemPosition() - 1
        Log.e("CHGV", "H Scroll Left -> Pos: $pos")
        if (pos >= 0) {
            smoothScrollToPosition(pos)
        }
        return true
    }

    private fun scrollRight(): Boolean {
        val linearLayoutManager = layoutManager as LinearLayoutManager
        val pos = linearLayoutManager.findLastVisibleItemPosition() + 1
        Log.e("CHGV", "H Scroll Right -> Pos: $pos")
        if (pos < contentAdapter.itemCount) {
            smoothScrollToPosition(pos)
        }
        return true
    }

    private fun scrollCenter(): Boolean {
        val pos = getSelectedItemPosition()
        Log.e("CVGV", "H Scroll Center: ${contentAdapter.getItem(pos).label}")
        return true
    }

    fun getSelectedItemPosition(): Int {
        val linearLayoutManager = layoutManager as LinearLayoutManager
        val pos = linearLayoutManager.findFirstVisibleItemPosition()
        return pos
    }
}