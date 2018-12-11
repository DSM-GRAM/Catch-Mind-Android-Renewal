package com.gram.cmr.Util

import android.app.Dialog

interface SubMainActivityNavigator{
    fun subMainToMain()

    fun showDialog()

    fun setTime(time: String)
    fun setRound(round :String)
    fun setMyScore(myScore: String)
    fun setOtherScore(otherScore: String)

    fun getAnswer(): String
}