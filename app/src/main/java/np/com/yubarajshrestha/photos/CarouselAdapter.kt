package np.com.yubarajshrestha.photos

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.TransitionDrawable
import android.support.constraint.ConstraintLayout
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import android.support.v7.graphics.Palette
import android.widget.ImageView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import android.support.v4.content.ContextCompat


class CarouselAdapter(private val photos: ArrayList<Photos>, private val context: Context): PagerAdapter() {

    lateinit var layoutInflater: LayoutInflater

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object` as ConstraintLayout

    override fun getCount(): Int = photos.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.carousel_photo_item, container, false)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        Glide.with(container.context)
                .asBitmap()
                .load(photos[position].profileImage)
                .listener(object: RequestListener<Bitmap> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean = false
                    override fun onResourceReady(resource: Bitmap?, model: Any?, target: Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        Palette.from(resource!!).generate { palette -> setUpInfoBackgroundColor(view.findViewById<ConstraintLayout>(R.id.parent_container), palette) }
                        return false;
                    }

                })
                .into(imageView)
        container.addView(view)
        return view
    }

    @SuppressLint("NewApi")
    private fun setUpInfoBackgroundColor(ll: ConstraintLayout, palette: Palette) {
        val swatch = getMostPopulousSwatch(palette)
        if (swatch != null) {
            val startColor = ContextCompat.getColor(ll.context, R.color.colorPrimary)
            val endColor = swatch.rgb

            val color: Array<ColorDrawable> = arrayOf(ColorDrawable(startColor), ColorDrawable(endColor))
            val transitionDrawable = TransitionDrawable(color)
            ll.background = transitionDrawable
            transitionDrawable.startTransition(1000)
            //AnimationUtility.animateBackgroundColorChange(ll, startColor, endColor)
        }
    }

    private fun getMostPopulousSwatch(palette: Palette?): Palette.Swatch? {
        var mostPopulous: Palette.Swatch? = null
        if (palette != null) {
            for (swatch in palette.swatches) {
                if (mostPopulous == null || swatch.population > mostPopulous.population) {
                    mostPopulous = swatch
                }
            }
        }
        return mostPopulous
    }

}