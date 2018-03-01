package ui.anwesome.com.kotlintapcirclemoveview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ui.anwesome.com.circlelinetapmoveview.CircleLineTapMoveView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CircleLineTapMoveView.create(this)
    }
}
