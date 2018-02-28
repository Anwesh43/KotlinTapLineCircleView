package ui.anwesome.com.circlelinetapmoveview

/**
 * Created by anweshmishra on 01/03/18.
 */
import android.graphics.*
import android.view.*
import android.content.*

class CircleLineTapMoveView(ctx : Context) : View(ctx) {
    val paint : Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
    data class State(var j : Int = 0, var dir : Float = 0f) {
        var scales : Array<Float> = arrayOf(0f, 0f)
        fun update(stopcb : () -> Unit) {
            scales[j] += 0.1f * dir
            if(Math.abs(scales[j]) > 1) {
                scales[j] =  dir
                j++
                if(j == scales.size) {
                    j = 0
                    dir = 0f
                    stopcb()
                }
            }
        }
        fun startUpdating(startcb : () -> Unit) {
            if(dir == 0f) {
                scales = arrayOf(0f, 0f)
                dir = 1f
                startcb()
            }
        }
    }
}