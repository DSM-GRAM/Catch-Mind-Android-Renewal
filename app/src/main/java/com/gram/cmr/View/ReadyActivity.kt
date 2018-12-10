package com.gram.cmr.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gram.cmr.R
import com.gram.cmr.Util.ReadyActivityNavigator
import com.gram.cmr.ExtraViewModel
import org.jetbrains.anko.startActivity

class ReadyActivity: AppCompatActivity(), ReadyActivityNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ready)
        val extraViewModel = ExtraViewModel(this)
    }

    override fun readyToMain() = startActivity<MainActivity>()

    override fun readyToSubMain() = startActivity<SubMainActivity>()

}