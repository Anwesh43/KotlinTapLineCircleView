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
    data class Animator(var view : View, var animated : Boolean = false) {
        fun animate(updatecb : () -> Unit) {
            if(animated) {
                updatecb()
                try {
                    Thread.sleep(50)
                    view.invalidate()
                }
                catch(ex : Exception) {

                }
            }
        }
        fun start() {
            if(!animated) {
                animated = true
                view.postInvalidate()
            }
        }
        fun stop() {
            if(animated) {
                animated = false
            }
        }
    }
    data class CircleLineTapMove(var x : Float, var y : Float, var r : Float , var deg : Float = 0f, var sx : Float = x, var sy : Float = y,var dx : Float = x, var dy : Float = y, var x1 : Float = x, var y1 : Float = y) {
        val state = State()
        fun draw(canvas : Canvas, paint : Paint) {
            paint.style = Paint.Style.STROKE
            canvas.save()
            canvas.translate(x, y)
            canvas.drawArc(RectF(-r, -r, r, r), deg * state.scales[0], 360f * (1 - state.scales[0]), false, paint)
            canvas.restore()
            val getUpdatedPoint : (Int) -> PointF = { PointF(sx + (dx - sx) * state.scales[it], sy + (dy - sy) * state.scales[it]) }

            canvas.save()
            canvas.translate(x1, y1)
            canvas.drawArc(RectF(-r, -r , r, r), (180 - deg), 360f * state.scales[0], false, paint)
            canvas.restore()
        }
        fun update(stopcb : () -> Unit) {
            state.update(stopcb)
        }
        fun startUpdating(x:Float, y: Float, startcb : () -> Unit) {
            if(state.dir == 0f) {
                deg = AngleUtils.getAngle(this.x, this.y, x, y)
                val x_projection = Math.cos(deg * Math.PI / 180).toFloat()
                val y_projection = Math.sin(deg * Math.PI / 180).toFloat()
                x1 = x + x_projection * (2 * Math.PI * r + 2 * r).toFloat()
                y1 = y + y_projection * (2 * Math.PI * r + 2 * r).toFloat()
                sx = x+ x_projection * r
                sy = y + y_projection * r
                dx = x + x_projection *  (2 * Math.PI * r + r).toFloat()
                dy = y + y_projection * (2 * Math.PI * r +  r).toFloat()
                startcb()
            }
        }
    }
}
fun Canvas.drawLinePoint(point1 : PointF, point2 : PointF, paint : Paint) {
    drawLine(point1.x, point1.y, point2.x, point2.y, paint)
}