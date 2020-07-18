package com.zuhaibahmad.flixview

import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.request.transition.ViewPropertyTransition
import com.zuhaibahmad.flixview.adapters.OnChildClickedListener
import com.zuhaibahmad.flixview.adapters.OnChildSelectedListener
import com.zuhaibahmad.flixview.adapters.SectionRowAdapter
import com.zuhaibahmad.flixview.data.Category
import com.zuhaibahmad.flixview.data.Content
import kotlinx.android.synthetic.main.view_flixview.view.*

const val DEFAULT_BANNER_ASPECT_RATIO = "36:10"

typealias OnItemViewSelectedListener = (Int, Content) -> Unit
typealias OnItemViewClickedListener = (Int, Content) -> Unit

class FlixView : FrameLayout {

    private var childSelectedListener: OnChildSelectedListener? = null
    private var childClickedListener: OnChildClickedListener? = null

    private lateinit var rowAdapter: SectionRowAdapter

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

        var categoryTextColor = 0
        var titleBackgroundColor = 0
        var titleTextColor = 0
        var titleTextSize = 0f

        if (typedArray.hasValue(R.styleable.FlixView_banner_aspect_ratio)) {
            val aspectRatio = typedArray.getString(R.styleable.FlixView_banner_aspect_ratio)
                ?: DEFAULT_BANNER_ASPECT_RATIO
            applyBannerAspectRatio(aspectRatio)
        }

        if (typedArray.hasValue(R.styleable.FlixView_selection_foreground)) {
            val drawable = typedArray.getDrawable(R.styleable.FlixView_selection_foreground)
                ?: ContextCompat.getDrawable(context, R.drawable.bg_selection)
            vSelection.background = drawable
        }

        if (typedArray.hasValue(R.styleable.FlixView_content_width)) {
            val defWidth = context.resources.getDimension(R.dimen.card_width)
            val width = typedArray.getDimension(R.styleable.FlixView_content_width, defWidth)
            vSelection.layoutParams.width = width.toInt()
        }

        if (typedArray.hasValue(R.styleable.FlixView_content_height)) {
            val defHeight = context.resources.getDimension(R.dimen.card_height)
            val height = typedArray.getDimension(R.styleable.FlixView_content_height, defHeight)
            vSelection.layoutParams.height = height.toInt()
        }

        if (typedArray.hasValue(R.styleable.FlixView_content_title_background_color)) {
            val defColor = ContextCompat.getColor(context, R.color.colorPrimary)
            val color = typedArray.getColor(R.styleable.FlixView_content_title_background_color, defColor)
            titleBackgroundColor = color
        }

        if (typedArray.hasValue(R.styleable.FlixView_content_title_text_color)) {
            val defColor = ContextCompat.getColor(context, R.color.textColorPrimary)
            val color = typedArray.getColor(R.styleable.FlixView_content_title_text_color, defColor)
            titleTextColor = color
        }

        if (typedArray.hasValue(R.styleable.FlixView_content_title_text_size)) {
            val defSize = context.resources.getDimension(R.dimen.title_text_size)
            val size = typedArray.getDimension(R.styleable.FlixView_content_title_text_size, defSize)
            titleTextSize = size
        }

        if (typedArray.hasValue(R.styleable.FlixView_category_title_text_color)) {
            val defColor = ContextCompat.getColor(context, R.color.textColorPrimary)
            val color = typedArray.getColor(R.styleable.FlixView_category_title_text_color, defColor)
            categoryTextColor = color
        }

        vSelection.requestLayout()

        rowAdapter = SectionRowAdapter(
            itemWidth = vSelection.layoutParams.width,
            itemHeight = vSelection.layoutParams.height,
            titleBackgroundColor = titleBackgroundColor,
            titleTextColor = titleTextColor,
            titleTextSize = titleTextSize,
            categoryTextColor = categoryTextColor
        );
        rvSection.adapter = rowAdapter
        rowAdapter.setOnChildSelectedListener { index, item ->
            updateBanner(url = item.featuredImageUrl)
            childSelectedListener?.invoke(index, item)
        }
        rowAdapter.setOnChildClickedListener { index, item ->
            childClickedListener?.invoke(index, item)
        }
    }

    private fun applyBannerAspectRatio(aspectRatio: String) {
        val set = ConstraintSet()
        set.clone(vContainer)
        set.setDimensionRatio(ivBanner.id, aspectRatio)
        set.applyTo(vContainer)
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