package np.com.yubarajshrestha.photos

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.grid_photo_item.view.*

class GridViewAdapter(private val photos: ArrayList<Photos>?, private val activity: Activity): RecyclerView.Adapter<GridViewAdapter.ViewHolder>() {

    lateinit var context: Context
    private lateinit var options: RequestOptions

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        options = RequestOptions().centerCrop().placeholder(R.drawable.model).error(R.drawable.model)
        val view = LayoutInflater.from(context).inflate(R.layout.grid_photo_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = photos!!.size

    override fun onBindViewHolder(holder: GridViewAdapter.ViewHolder, position: Int) {
        holder.bindItem(photos!![position], position, options)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private var imageHolder = itemView.imageView
        lateinit var photo: Photos
        var photoPosition: Int = 0

        init {
            itemView.setOnClickListener(this)
        }

        fun bindItem(item: Photos, photoPosition: Int, options: RequestOptions) {
            photo = item
            this.photoPosition = photoPosition
            Glide.with(itemView.context).load(item.profileImage).apply(options).into(imageHolder)
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onClick(v: View?) {
            val photoActivity = Intent(itemView.context, PhotoActivity::class.java)
            photoActivity.putExtra("image", photo.profileImage)
            photoActivity.putParcelableArrayListExtra("photos", photos)
            photoActivity.putExtra("position", photoPosition)
            val activityOptions = ActivityOptions.makeSceneTransitionAnimation(activity, android.util.Pair<View, String>(imageHolder, "image"))
            itemView.context.startActivity(photoActivity, activityOptions.toBundle())
        }
    }

}