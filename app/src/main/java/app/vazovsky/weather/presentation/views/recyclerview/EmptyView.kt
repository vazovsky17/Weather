package app.vazovsky.vazovskytech.presentation.views

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import app.vazovsky.weather.R
import app.vazovsky.weather.databinding.ViewStateEmptyBinding
import app.vazovsky.weather.extensions.getColorFromAttribute
import timber.log.Timber
import com.google.android.material.R as MaterialR

class EmptyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ViewStateEmptyBinding = ViewStateEmptyBinding.inflate(LayoutInflater.from(context), this)

    init {
        binding.buttonEmpty.isVisible = false
        val padding = context.resources.getDimensionPixelSize(R.dimen.padding_16)
        setPadding(padding, padding, padding, padding)

        if (attrs != null) {
            val a = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.EmptyView,
                0,
                0
            )
            try {
                setupTheme(
                    a.getBoolean(R.styleable.EmptyView_isDarkMode, false)
                )
                setEmptyImage(
                    a.getResourceId(
                        R.styleable.EmptyView_emptyImageSrc,
                        R.raw.default_empty
                    )
                )
                setEmptyAnimation(
                    a.getResourceId(
                        R.styleable.EmptyView_emptyAnimationSrc,
                        R.raw.default_empty
                    )
                )
                setEmptyTitle(
                    a.getResourceId(
                        R.styleable.EmptyView_emptyTitle,
                        R.string.empty_default_title
                    )
                )
                setEmptyButton(
                    a.getResourceId(
                        R.styleable.EmptyView_emptyButton,
                        0
                    )
                )
                setEmptyComment(
                    a.getResourceId(
                        R.styleable.EmptyView_emptyComment,
                        R.string.empty_default_message
                    )
                )
                setIsLoop(
                    a.getBoolean(R.styleable.EmptyView_loopAnimation, true)
                )
            } finally {
                a.recycle()
            }
        }
    }

    private fun setupTheme(isDark: Boolean) = with(binding) {
        if (isDark) {
            val textColor = context.getColorFromAttribute(MaterialR.attr.colorOnPrimary)
            textEmptyTitle.setTextColor(textColor)
            textEmptyComment.setTextColor(textColor)
        }
    }

    fun setIsLoop(isLoop: Boolean) {
        binding.animationViewEmpty.repeatCount = if (isLoop) ValueAnimator.INFINITE else 0
    }

    fun setEmptyComment(@StringRes comment: Int) {
        binding.textEmptyComment.setText(comment)
    }

    fun setEmptyTitle(@StringRes title: Int) {
        binding.textEmptyTitle.setText(title)
    }

    fun setEmptyImage(@DrawableRes image: Int?) = with(binding) {
        if (image == 0 || image == null) {
            animationViewEmpty.isVisible = false
        } else {
            animationViewEmpty.isVisible = true
            animationViewEmpty.setImageResource(image)
        }
    }

    fun setEmptyAnimation(@RawRes image: Int) = with(binding) {
        if (image == 0) {
            animationViewEmpty.isVisible = false
        } else {
            animationViewEmpty.isVisible = true
            animationViewEmpty.setAnimation(image)
        }
    }

    fun setButtonTextAndClickListener(@StringRes text: Int, onClickListener: () -> Unit) = with(binding) {
        buttonEmpty.isVisible = true
        buttonEmpty.setText(text)
        buttonEmpty.setOnClickListener { onClickListener() }
    }

    private fun setEmptyButton(@StringRes text: Int) = with(binding) {
        if (text == 0) {
            buttonEmpty.isVisible = false
        } else {
            buttonEmpty.isVisible = true
            buttonEmpty.setText(text)
        }
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (visibility == View.GONE) {
            stopEmptyAnimation()
        } else if (visibility == View.VISIBLE) {
            startEmptyAnimation()
        }
    }

    private fun startEmptyAnimation() = with(binding) {
        animationViewEmpty.playAnimation()
    }

    private fun stopEmptyAnimation() = with(binding) {
        try {
            animationViewEmpty.cancelAnimation()
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
}