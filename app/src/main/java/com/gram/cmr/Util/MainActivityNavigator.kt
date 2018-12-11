package com.gram.cmr.Util

import android.app.Dialog

interface MainActivityNavigator{
    fun mainToSubMain()

    fun showDialog()

    fun setTime(time: String)
    fun setRound(round :String)
    fun setMyScore(myScore: String)
    fun setOtherScore(otherScore: String)
    fun setWord(word: String)
}