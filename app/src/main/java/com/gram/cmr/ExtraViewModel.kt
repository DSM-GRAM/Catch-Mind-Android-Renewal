package com.gram.cmr

import com.gram.cmr.Model.PlayerModel
import com.gram.cmr.Util.Event
import com.gram.cmr.Util.ReadyActivityNavigator
import com.gram.cmr.Util.StartActivityNavigator

class ExtraViewModel{

    val event by lazy { Event }
    val playerModel: PlayerModel by lazy { PlayerModel }

    var readyActivityNavigator: ReadyActivityNavigator? = null
    var startActivityNavigator: StartActivityNavigator? = null

    constructor (navigator: ReadyActivityNavigator){

        readyActivityNavigator = navigator
        event.start()
        if (playerModel.player)
            readyActivityNavigator!!.readyToMain()
        else
            readyActivityNavigator!!.readyToSubMain()

    }

    constructor (navigator: StartActivityNavigator){
        startActivityNavigator =  navigator
    }

    fun startClick() = startActivityNavigator!!.startToReady()
}