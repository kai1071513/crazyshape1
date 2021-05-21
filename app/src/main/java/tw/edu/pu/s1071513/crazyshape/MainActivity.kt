package tw.edu.pu.s1071513.crazyshape

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import kotlinx.android.synthetic.main.activity_main.*


@GlideModule
public final class MyAppGlideModule : AppGlideModule()

class MainActivity : AppCompatActivity(),
    GestureDetector.OnGestureListener,View.OnTouchListener{

    lateinit var gDetector: GestureDetector
    var PictureNo:Int = 0  //目前顯示第幾張圖
    var TotalPictures:Int = 3//總共幾張圖片(假設僅顯示pu0-pu3)

    fun ShowPicture(){
        when (PictureNo){
            0 -> imgNext.setImageResource(R.drawable.square)
            1 -> imgNext.setImageResource(R.drawable.star)
            2 -> imgNext.setImageResource(R.drawable.triangle)
        }
    }


    override fun onLongPress(p0: MotionEvent?) {
        intent = Intent(this@MainActivity, GameActivity::class.java)
        startActivity(intent)
    }
    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        return true
    }
    override fun onDown(p0: MotionEvent?): Boolean {
        return true
    }
    override fun onShowPress(p0: MotionEvent?) {
    }
    override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
        return true
    }


    override fun onTouch(p0: View?, event: MotionEvent?): Boolean {
        gDetector.onTouchEvent(event)
        return true
    }
    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        var X: Int = (0..2).random()
        PictureNo = X
        ShowPicture()
        return true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gDetector = GestureDetector(this, this)
        imgNext.setOnTouchListener(this)

        val img:ImageView = findViewById(R.id.imgTitle)
        GlideApp.with(this)
            .load(R.drawable.cover)
            .override(800, 600)

            .into(img)
        Toast.makeText(baseContext, "作者：莊富凱", Toast.LENGTH_LONG).show()

        imgNext.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(p0: View?): Boolean {
                intent = Intent(this@MainActivity, GameActivity::class.java)
                startActivity(intent)
                return true
            }
        })
    }
}