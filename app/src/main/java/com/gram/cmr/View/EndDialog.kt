package com.gram.cmr.View

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.gram.cmr.R
import com.gram.cmr.Model.SettingModel
import com.gram.cmr.Util.Event
import com.gram.cmr.databinding.GgDialogBinding
import kotlinx.android.synthetic.main.gg_dialog.*

class EndDialog(context: Context): Dialog(context) {
    private val LAYOUT = R.layout.gg_dialog
    var result: String
    var myScore: String
    var otherScore: String
    val event: Event by lazy { Event }
    val settingModel: SettingModel by lazy { SettingModel }

    init {
        if (settingModel.myScore > settingModel.otherScore)
            result = "WIN!"
        else if (settingModel.myScore == settingModel.otherScore)
            result = "DRAW!"
        else
            result = "LOSE!"
        this.myScore = settingModel.myScore.toString()
        this.otherScore = settingModel.otherScore.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LAYOUT)

        win_or_lose.setText(result)
        Rmyscore.setText(myScore)
        Rotherscore.setText(otherScore)
        game_close.setOnClickListener { v ->
            event.gameEnd()
            event.socket.disconnect()
            System.exit(0)
        }
    }
}