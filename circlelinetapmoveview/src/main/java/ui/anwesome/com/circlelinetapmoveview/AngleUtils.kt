package ui.anwesome.com.circlelinetapmoveview

/**
 * Created by anweshmishra on 01/03/18.
 */
class AngleUtils {
    companion object {
        fun getAngle(x : Float, y : Float, x1 : Float , y1 : Float) : Float {
            if(x1 == x) {
                return 90f
            }
            var deg = Math.atan( (y1 - y).toDouble() / (x1 - x)).toFloat()
            if(y1 < y) {
                if(x1 < x) {
                    return 180 + deg
                }
                else {
                    return 360 - deg
                }
            }
            else {
                if(x1 < x) {
                    return 180 - deg
                }
            }
            return deg
        }
    }
}