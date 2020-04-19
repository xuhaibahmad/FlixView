package com.zuhaibahmad.netflixgriddemo.recyclerview

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zuhaibahmad.netflixgriddemo.R
import com.zuhaibahmad.netflixgriddemo.leanback.utils.FakeDataFactory
import kotlinx.android.synthetic.main.main_fragment.*

class RecyclerViewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = FakeDataFactory.getCategorizedContent().toMutableList()
        val rowAdapter = SectionRowAdapter(data)
        rvSection.adapter = rowAdapter
        rvSection.isNestedScrollingEnabled = true
        rvSection.setOnChildSelectedListener { _, _, position, _ ->
            Handler().postDelayed({
                rowAdapter.setSelectedItemPosition(position)
            }, 500)
        }
    }
}