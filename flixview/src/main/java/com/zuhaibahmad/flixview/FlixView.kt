package com.zuhaibahmad.flixview

import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.request.transition.ViewPropertyTransition
import com.zuhaibahmad.flixview.adapters.OnChildClickedListener
import com.zuhaibahmad.flixview.adapters.OnChildSelectedListener
import com.zuhaibahmad.flixview.adapters.SectionRowAdapter
import com.zuhaibahmad.flixview.data.Category
import com.zuhaibahmad.flixview.data.Content
import kotlinx.android.synthetic.main.view_flixview.view.*

typealias OnItemViewSelectedListener = (Int, Content) -> Unit
typealias OnItemViewClickedListener = (Int, Content) -> Unit

class FlixView : FrameLayout {

    private var childSelectedListener: OnChildSelectedListener? = null
    private var childClickedListener: OnChildClickedListener? = null

    private val rowAdapter = SectionRowAdapter()

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
        rvSection.adapter = rowAdapter
        rowAdapter.setOnChildSelectedListener { index, item ->
            updateBanner(url = item.featuredImageUrl)
            childSelectedListener?.invoke(index, item)
        }
        rowAdapter.setOnChildClickedListener { index, item ->
            childClickedListener?.invoke(index, item)
        }
    }

    fun setItems(items: List<Category>) {
        rowAdapter.setItems(items)
    }

    private fun updateBanner(url: String?) {
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