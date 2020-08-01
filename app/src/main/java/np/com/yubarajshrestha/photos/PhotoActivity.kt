package np.com.yubarajshrestha.photos

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_photo.*
import android.support.v4.view.ViewPager
import org.jetbrains.anko.toast


class PhotoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        setUpToolbar()

        photos = intent.getParcelableArrayListExtra("photos");
        page = intent.getIntExtra("position", 0)

        setUpPager()

        buttonDownload.setOnClickListener {
            toast("Image: " + photos!![page].profileImage)
        }

        /*val image = intent.getStringExtra("image")

        Glide.with(this).load(image).into(imageView)*/
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        supportActionBar?.title = ""
    }

    private fun setUpPager() {
        val adapter = CarouselAdapter(photos!!, this)
        carousel.adapter = adapter
        carousel.currentItem = page
        carousel.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                page = position
            }

            override fun onPageSelected(state: Int) {}

        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> supportFinishAfterTransition()
            else -> return super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        var photos: ArrayList<Photos>? = null
        var page: Int = 0
    }
}
