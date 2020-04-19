package com.zuhaibahmad.netflixgriddemo.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zuhaibahmad.netflixgriddemo.R
import com.zuhaibahmad.netflixgriddemo.leanback.utils.FakeDataFactory
import kotlinx.android.synthetic.main.main_fragment.*


class RecyclerViewFragment : Fragment() {

    private var finalWidthDate: Int = 0
    private var itemWidthDate: Int = 0
    private var paddingDate: Int = 0
    private var firstItemWidthDate: Int = 0
    var allPixelsDate: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUi()
    }

    fun setupUi() {
        rvContent.doOnPreDraw {
            finalWidthDate = rvContent.measuredWidth
            itemWidthDate = resources.getDimension(R.dimen.card_width).toInt()
            paddingDate = (finalWidthDate - itemWidthDate) / 2
            firstItemWidthDate = paddingDate
            allPixelsDate = 0
            val dateLayoutManager = LinearLayoutManager(requireContext())
            dateLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            rvContent.layoutManager = dateLayoutManager
            rvContent.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(
                    recyclerView: RecyclerView,
                    newState: Int
                ) {
                    super.onScrollStateChanged(recyclerView, newState)
                    synchronized(this) {
                        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                            calculatePositionAndScrollDate(recyclerView)
                        }
                    }
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    allPixelsDate += dx
                }
            })
            rvContent.adapter = ContentAdapter(
                FakeDataFactory.fakeThumbnails + FakeDataFactory.fakeThumbnails + FakeDataFactory.fakeThumbnails
            )
        }
    }

/* this if most important, if expectedPositionDate < 0 recyclerView will return to nearest item*/

    /* this if most important, if expectedPositionDate < 0 recyclerView will return to nearest item*/
    fun calculatePositionAndScrollDate(recyclerView: RecyclerView) {
        var expectedPositionDate =
            ((allPixelsDate + paddingDate - firstItemWidthDate) / itemWidthDate)
        if (expectedPositionDate == -1) {
            expectedPositionDate = 0
        } else if (expectedPositionDate >= recyclerView.adapter!!.itemCount - 2) {
            expectedPositionDate--
        }
        scrollListToPositionDate(recyclerView, expectedPositionDate)
    }

    /* this if most important, if expectedPositionDate < 0 recyclerView will return to nearest item*/
    fun scrollListToPositionDate(
        recyclerView: RecyclerView,
        expectedPositionDate: Int
    ) {
        val targetScrollPosDate: Float =
            (expectedPositionDate * itemWidthDate + firstItemWidthDate - paddingDate).toFloat()
        val missingPxDate: Float = targetScrollPosDate - allPixelsDate
        if (missingPxDate != 0f) {
            recyclerView.smoothScrollBy(missingPxDate.toInt(), 0)
        }
    }
}