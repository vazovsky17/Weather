package app.vazovsky.weather.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

fun ImageView.load(
    imageUrl: String?,
    @DrawableRes placeHolderRes: Int? = null,
    @DrawableRes errorRes: Int? = placeHolderRes,
) {
    Glide.with(this).clear(this)
    Glide.with(context)
        .load(imageUrl)
        .apply { placeHolderRes?.let(::placeholder) }
        .apply { errorRes?.let(::error) }
        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
        .into(this)
}