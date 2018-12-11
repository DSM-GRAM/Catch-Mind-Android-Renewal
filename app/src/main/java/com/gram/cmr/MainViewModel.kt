package com.gram.cmr

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import com.gram.cmr.Model.PassModel
import com.gram.cmr.Model.PlayerModel
import com.gram.cmr.Model.SettingModel
import com.gram.cmr.Util.*
import com.jinwoo.catch_mind.AutoDrawClass
import com.jinwoo.catch_mind.DrawClass
import org.jetbrains.anko.doAsync


class MainViewModel{

    lateinit var drawClass: DrawClass
    lateinit var autoDrawClass: AutoDrawClass

    var context: Context
    var mainActivityNavigator: MainActivityNavigator? = null
    var subMainActivityNavigator: SubMainActivityNavigator? = null

    val event by lazy { Event }
    val playerModel: PlayerModel by lazy { PlayerModel }
    val settingModel: SettingModel by lazy { SettingModel }
    val passModel: PassModel by lazy { passModel }

    constructor (navigator: MainActivityNavigator, context: Context, drawlayout: FrameLayout){

        this.context = context
        mainActivityNavigator = navigator
        drawClass = DrawClass(context)
        drawlayout.addView(drawClass)
        mainGameSetting()
        doAsync { timer("main") }
        event.receivePass()
        doAsync { checkPass() }
        endCheck()
    }

    constructor (navigator: SubMainActivityNavigator, context: Context, drawlayout: FrameLayout){

        this.context = context
        subMainActivityNavigator = navigator
        autoDrawClass = AutoDrawClass(context)
        drawlayout.addView(autoDrawClass)
        subMainGameSetting()
        doAsync { timer("sub") }
        endCheck()

    }

    fun colorClick(v: View)  {
        Log.d("gdfgfdgdfgdfgdf", v.id.toString())
        drawClass.setColor(colorSearcher(v.id.toString()), 15f)
    }

    fun eraserClick() = drawClass.setColor("#FFFFFF", 50f)

    fun answerCheck() {
        if(subMainActivityNavigator!!.getAnswer() == playerModel.word){
            endRound()
            event.sendPass()
            event.roundChange()
            subMainActivityNavigator!!.subMainToMain()
        }
    }

    fun timer(navigator: String){
        var timeCounter = 0
        var timeMinute = 3
        while (timeMinute > 0 || timeCounter > 0) {
            Thread.sleep(1000)
            timeCounter--
            if (timeCounter < 0) {
                timeCounter = 59
                timeMinute--
            }
            if(navigator == "main")
                mainActivityNavigator!!.setTime("$timeMinute:$timeCounter")
            else
                subMainActivityNavigator!!.setTime("$timeMinute:$timeCounter")
        }
        settingModel.round += 1
        event.roundChange()
        if (navigator == "main")
            mainActivityNavigator!!.mainToSubMain()
        else
            subMainActivityNavigator!!.subMainToMain()
    }

    fun mainGameSetting(){
        mainActivityNavigator!!.setRound("ROUND ${settingModel.round}")
        mainActivityNavigator!!.setMyScore(settingModel.myScore.toString())
        mainActivityNavigator!!.setOtherScore(settingModel.otherScore.toString())
        mainActivityNavigator!!.setWord(playerModel.word)
    }

    fun subMainGameSetting(){
        subMainActivityNavigator!!.setRound("ROUND ${settingModel.round}")
        subMainActivityNavigator!!.setMyScore(settingModel.myScore.toString())
        subMainActivityNavigator!!.setOtherScore(settingModel.otherScore.toString())
    }

    fun checkPass(){ // 옵저빙 패턴이나 rxJava 넣으면 더 좋은 코드가 될 듯
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

    fun endRound(){
        settingModel.otherScore += 10
        settingModel.round += 1
    }

    fun endCheck(){
        if(settingModel.round == 5)
            mainActivityNavigator!!.showDialog()
    }

    fun colorSearcher(id: String): String{
        when(id){
            "2131230843" -> return "#FF8585"
            "2131230829" -> return "#FFCE85"
            "2131230759" -> return "#000000"
            "2131230840" -> return "#9570FF"
            "2131230795" -> return "#757575"
            "2131230796" -> return "#56E69E"
            "2131230809" -> return "#A4A7FF"
            "2131230837" -> return "#FF98C8"
            "2131230868" -> return "#ABF9F4"
            "2131230909" -> return "#FBFB8E"
            "2131230910" -> return "#A7FBB0"
            "2131230753" -> return "#D784FF"
        }
        return "#000000"
    }
}