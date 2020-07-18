package com.zuhaibahmad.flixview

import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Toast
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.request.transition.ViewPropertyTransition
import com.zuhaibahmad.flixview.leanback.utils.FakeDataFactory
import com.zuhaibahmad.flixview.recyclerview.OnChildClickedListener
import com.zuhaibahmad.flixview.recyclerview.OnChildSelectedListener
import com.zuhaibahmad.flixview.recyclerview.SectionRowAdapter
import kotlinx.android.synthetic.main.view_flixview.view.*

class FlixView : FrameLayout {

    private var childSelectedListener: OnChildSelectedListener? = null
    private var childClickedListener: OnChildClickedListener? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.FlixView, 0, 0
        )
        bindView(typedArray)
        typedArray.recycle()
    }

    private fun bindView(typedArray: TypedArray) {
        val view = LayoutInflater.from(context).inflate(R.layout.view_flixview, this, true)
        val data = FakeDataFactory.getCategorizedContent().toMutableList()
        val rowAdapter = SectionRowAdapter(data)
        rvSection.adapter = rowAdapter
        rowAdapter.setOnChildSelectedListener { index, item ->
            updateBanner(url = item.featuredImageUrl)
            childSelectedListener?.invoke(index, item)
        }
        rowAdapter.setOnChildClickedListener { index, item ->
            Toast.makeText(context, "${item.label} clicked!", Toast.LENGTH_SHORT).show()
            childClickedListener?.invoke(index, item)
        }
    }

    fun updateBanner(url: String?) {
        val animationObject = ViewPropertyTransition.Animator { view ->
            view.alpha = 0f
            val fadeAnim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
            fadeAnim.duration = 500
            fadeAnim.start()
        }
        Glide.with(this)
            .asBitmap()
            .load(url)
            .centerCrop()
            .transition(GenericTransitionOptions.with(animationObject))
            .into(ivBanner)
    }

    fun setOnChildSelectedListener(listener: OnChildSelectedListener) {
        this.childSelectedListener = listener
    }

    fun setOnChildClickedListener(listener: OnChildClickedListener) {
        this.childClickedListener = listener
    }
}