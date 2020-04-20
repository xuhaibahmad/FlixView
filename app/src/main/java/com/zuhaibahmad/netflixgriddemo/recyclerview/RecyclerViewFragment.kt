package com.zuhaibahmad.netflixgriddemo.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.zuhaibahmad.netflixgriddemo.BannerScreen
import com.zuhaibahmad.netflixgriddemo.R
import com.zuhaibahmad.netflixgriddemo.leanback.utils.FakeDataFactory
import kotlinx.android.synthetic.main.main_fragment.*

class RecyclerViewFragment : Fragment() {

    private val parentScreen by lazy { requireActivity() as BannerScreen }

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
        rowAdapter.setOnChildSelectedListener { _, item ->
            parentScreen.updateBanner(url = item.featuredImageUrl)
        }
        rowAdapter.setOnChildClickedListener { _, item ->
            Toast.makeText(requireContext(), "${item.label} clicked!", Toast.LENGTH_SHORT).show()
        }
    }
}