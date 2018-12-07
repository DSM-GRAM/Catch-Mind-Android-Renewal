package com.gram.cmr.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.gram.cmr.Util.Event
import com.jinwoo.catch_mind.DrawClass
import kotlinx.android.synthetic.main.activity_main.*

class MainViewModel(context: Context): ViewModel(){
    val event by lazy { Event }
    val drawClass: DrawClass

    init {
        event.
        drawClass = DrawClass(context)
        drawlayout.addView(drawClass)
    }

    fun ColorClick(color: String) = drawClass.setColor(color, 5f)

    fun EraserClick() = drawClass.setColor("#FFFFFF", 50f)
}