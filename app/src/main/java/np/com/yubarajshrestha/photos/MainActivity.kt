package np.com.yubarajshrestha.photos

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpModels()
        setUpAdapter()
    }

    private fun setUpModels() {
        photos = ArrayList()
        photos!!.add(Photos("Kim Ha Yul", "https://i.pinimg.com/originals/e2/25/11/e22511072cdc9cdf12bcbd7761695e74.jpg"))
        photos!!.add(Photos("Kim Ha Yul", "https://i.pinimg.com/originals/90/42/00/904200cd1be14c752581238ea457da72.jpg"))
        photos!!.add(Photos("KOPPO", "https://i.pinimg.com/564x/2e/dc/62/2edc621f1821415ad3454962be886cbb.jpg"))
        photos!!.add(Photos("KOPPO", "https://i.pinimg.com/564x/4f/10/9c/4f109c8dd9873cebba88d50cdd7b8884.jpg"))
        photos!!.add(Photos("KOPPO", "https://i.pinimg.com/564x/86/6f/c6/866fc628b9d176452c4e920d04749f77.jpg"))
        photos!!.add(Photos("KOPPO", "https://i.pinimg.com/564x/18/54/2a/18542a70bcb3e274b6eb3eb3ac92b2ca.jpg"))
        photos!!.add(Photos("ART", "https://i.pinimg.com/564x/33/2f/d4/332fd4bcea8ee4ebbed6435cb6b577f8.jpg"))
        photos!!.add(Photos("Glass", "https://i.pinimg.com/564x/42/db/0a/42db0ad27bb3fbab7208faa46c5995bb.jpg"))
        photos!!.add(Photos("RED", "https://i.pinimg.com/564x/cc/9d/0f/cc9d0fbe212f7116b4445bba3dd764ab.jpg"))
        photos!!.add(Photos("adfds", "https://i.pinimg.com/564x/ce/b0/fa/ceb0fa1c618c228425a5491f1dec71a0.jpg"))
        photos!!.add(Photos("adsfsadf", "https://i.pinimg.com/originals/9f/ec/47/9fec471edaa52ce015f741e2a1ec6e7c.jpg"))
        photos!!.add(Photos("asdfsf", "https://i.pinimg.com/originals/0c/ac/6c/0cac6c093fbda1d0392ecac7d36c536f.jpg"))
        photos!!.add(Photos("adfasdf", "https://78.media.tumblr.com/52354fa355681fcffe461a9bc121d27a/tumblr_o4mzr9oZcR1uwjqjdo1_500.jpg"))
    }

    private fun setUpAdapter() {
        photoList.layoutManager = GridLayoutManager(this, 2) as RecyclerView.LayoutManager
        photoList.adapter = GridViewAdapter(photos, this)
    }

    companion object {
        private var photos: ArrayList<Photos>? = null
    }
}
