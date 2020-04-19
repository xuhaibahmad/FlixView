package com.zuhaibahmad.netflixgriddemo.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.zuhaibahmad.netflixgriddemo.R
import com.zuhaibahmad.netflixgriddemo.leanback.utils.FakeDataFactory
import kotlinx.android.synthetic.main.main_fragment.*


class RecyclerViewFragment : Fragment() {

    val contentAdapter = ContentAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        val items = FakeDataFactory.fakeThumbnails + FakeDataFactory.fakeThumbnails
        contentAdapter.update(items)
        rvContent.adapter = contentAdapter
        contentAdapter.setSelecteditem(0)
    }

    fun setupUi() {
        rvContent.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                synchronized(this) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        //calculatePositionAndScrollDate(recyclerView)
                        //val linearLayoutManager = rvContent.layoutManager as GridLayoutManager
                        //val selection = linearLayoutManager.findFirstCompletelyVisibleItemPosition()
                        //contentAdapter.setSelecteditem(selection)
                    }
                }
            }
        })
    }
}