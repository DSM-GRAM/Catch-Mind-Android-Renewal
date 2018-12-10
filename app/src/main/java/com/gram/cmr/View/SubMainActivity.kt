package com.gram.cmr.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.gram.cmr.R
import com.gram.cmr.Util.SubMainActivityNavigator
import com.gram.cmr.MainViewModel
import com.gram.cmr.databinding.ActivityMainSubBinding
import kotlinx.android.synthetic.main.activity_main_sub.*
import org.jetbrains.anko.startActivity

class SubMainActivity : AppCompatActivity(), SubMainActivityNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainSubBinding>(this, R.layout.activity_main_sub)
        val subMainViewModel = MainViewModel(this, this, drawlayout)
        binding.mainViewModel = subMainViewModel
    }

    override fun subMainToMain() = startActivity<MainActivity>()
}
