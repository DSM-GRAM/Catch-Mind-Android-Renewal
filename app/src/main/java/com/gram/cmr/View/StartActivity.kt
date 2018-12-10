package com.gram.cmr.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.gram.cmr.Util.StartActivityNavigator
import com.gram.cmr.ExtraViewModel
import com.gram.cmr.R
import com.gram.cmr.databinding.ActivityStartBinding
import org.jetbrains.anko.startActivity

class StartActivity: AppCompatActivity(), StartActivityNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityStartBinding>(this, R.layout.activity_start)
        val extraViewModel = ExtraViewModel(this)
        binding.extraViewModel = extraViewModel
    }

    override fun startToReady() = startActivity<ReadyActivity>()
}