package com.gram.cmr

import com.gram.cmr.Model.SettingModel
import com.gram.cmr.Util.Event

class DialogViewModel{

    val event by lazy { Event }
    val settingModel: SettingModel by lazy { SettingModel }

    var result: String = ""

    init {
        if (settingModel.myScore > settingModel.otherScore)
            result = "WIN!"
        else if (settingModel.myScore == settingModel.otherScore)
            result = "DRAW!"
        else
            result = "LOSE!"
    }

    fun endClick(){
        event.gameEnd()
        event.socket.disconnect()
        System.exit(0)
    }
}