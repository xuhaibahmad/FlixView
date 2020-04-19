package com.zuhaibahmad.netflixgriddemo

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.request.transition.ViewPropertyTransition
import kotlinx.android.synthetic.main.main_activity.*

interface BannerScreen {
    fun updateBanner(url: String? = null)
}

class MainActivity : AppCompatActivity(), BannerScreen {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    override fun updateBanner(url: String?) {
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
}