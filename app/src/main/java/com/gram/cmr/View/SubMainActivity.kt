package com.gram.cmr.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
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

    override fun setTime(time: String) { runOnUiThread{ timer.setText(time) } }

    override fun setRound(round: String) = roundText.setText(round)

    override fun setMyScore(myScore: String) = myscore.setText(myScore)

    override fun setOtherScore(otherScore: String) = otherscore.setText(otherScore)

    override fun getAnswer(): String = answer_edit.text.toString()

    override fun showDialog() {
        val dialog = EndDialog(this)
        var params: WindowManager.LayoutParams = dialog.window.attributes
        params.width = WindowManager.LayoutParams.WRAP_CONTENT
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog.window.attributes = params
        dialog.show()
        onStop()
    }


}
