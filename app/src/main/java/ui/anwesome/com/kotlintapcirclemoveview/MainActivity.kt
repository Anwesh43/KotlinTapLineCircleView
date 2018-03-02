package ui.anwesome.com.kotlintapcirclemoveview

import android.graphics.PointF
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import ui.anwesome.com.circlelinetapmoveview.CircleLineTapMoveView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = CircleLineTapMoveView.create(this)
        fullScreen()
        view.addOnCircleMoveListener { point1, point2 ->
            Toast.makeText(this,"moved from ${point1.toXYString()} to ${point2.toXYString()}", Toast.LENGTH_SHORT).show()
        }
    }
}
fun MainActivity.fullScreen() {
    supportActionBar?.hide()
    window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
}
fun PointF.toXYString():String = "${Math.floor(x.toDouble())}, ${Math.floor(y.toDouble())}"
