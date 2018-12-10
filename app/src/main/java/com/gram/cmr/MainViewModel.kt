package com.gram.cmr

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.activity_main.*
import com.gram.cmr.Model.PassModel
import com.gram.cmr.Model.PlayerModel
import com.gram.cmr.Model.SettingModel
import com.gram.cmr.Util.*
import com.gram.cmr.View.EndDialog
import com.jinwoo.catch_mind.DrawClass


class MainViewModel{

    var context: Context
    var drawClass: DrawClass
    var mainActivityNavigator: MainActivityNavigator? = null
    var subMainActivityNavigator: SubMainActivityNavigator? = null

    val event by lazy { Event }
    val playerModel: PlayerModel by lazy { PlayerModel }
    val settingModel: SettingModel by lazy { SettingModel }
    val passModel: PassModel by lazy { passModel }

    var answer: String = ""
    var word: String = "기본값"
    var round: String = "ROUND 1"

    var myScore: Int = 0
    var otherScore: Int = 0

    var timeString = "1:30"

    constructor (navigator: MainActivityNavigator, context: Context, drawlayout: FrameLayout){
        this.context = context
        this.word = playerModel.word
        mainActivityNavigator = navigator
        drawClass = DrawClass(context)
        drawlayout.addView(drawClass) // main
        gameSetting()
        timer()
        event.receivePass()
        checkPass()
    }
    constructor (navigator: SubMainActivityNavigator, context: Context, drawlayout: FrameLayout){
        this.context = context
        subMainActivityNavigator = navigator
        drawClass = DrawClass(context)
        drawlayout.addView(drawClass) // submain
        timer()
    }

    fun colorClick(v: View) = drawClass.setColor(v.id, 5f)

    fun eraserClick() = drawClass.setColor(Color.parseColor("#FFFFFF"), 50f)

    fun answerCheck() {
        if(answer == playerModel.word){
            endRound()
            event.sendPass()
            event.roundChange()
            subMainActivityNavigator!!.subMainToMain()
            endCheck()
        }
    }

    fun timer(){
        var timeCounter = 30
        var timeMinute = 1
        Thread{
            while (true) {
                Thread.sleep(1000)
                timeCounter--
                if (timeCounter < 0) {
                    timeCounter = 59
                    timeMinute--
                }
                timeString = "$timeMinute:$timeCounter"
                if (timeMinute == 0 && timeCounter == 0) {
                    break
                }
            }
            settingModel.round += 1
            event.roundChange()
            mainActivityNavigator?.let {
                it.mainToSubMain()
            } .let {
                subMainActivityNavigator!!.subMainToMain()
            }
            endCheck()
        }
    }

    fun gameSetting(){
        this.round = "ROUND ${settingModel.round}"
        this.myScore = settingModel.myScore
        this.otherScore = settingModel.otherScore
    }

    fun checkPass(){ // 옵저빙 패턴이나 rxJava 넣으면 더 좋은 코드가 될 듯
        Thread{
            while(true){
                if(passModel.pass) {
                    endRound()
                    event.roundChange()
                    mainActivityNavigator!!.mainToSubMain()
                    endCheck()
                    break
                }
            }
        }
    }

    fun endRound(){
        settingModel.otherScore += 10
        settingModel.round += 1
    }

    fun endCheck(){
        if(settingModel.round == 5)
            makeDialog()
    }

    fun makeDialog(){
        val dialog = EndDialog(context)
        var params: WindowManager.LayoutParams = dialog.window.attributes
        params.width = WindowManager.LayoutParams.WRAP_CONTENT
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog.window.attributes = params
        dialog.show()
    }
}