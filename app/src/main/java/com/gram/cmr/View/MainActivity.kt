package com.gram.cmr.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.gram.cmr.R
import com.gram.cmr.Util.MainActivityNavigator
import com.gram.cmr.MainViewModel
import com.gram.cmr.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), MainActivityNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val mainViewModel = MainViewModel(this, this, drawlayout)
        binding.mainViewModel = mainViewModel
    }

    override fun mainToSubMain() = startActivity<SubMainActivity>()

    override fun setTime(time: String) { runOnUiThread{ timer.setText(time) } }

    override fun setRound(round: String) = roundText.setText(round)

    override fun setMyScore(myScore: String) = myscore.setText(myScore)

    override fun setOtherScore(otherScore: String) = otherscore.setText(otherScore)

    override fun setWord(word: String) = wordText.setText(word)

    override fun showDialog() {
        val dialog = EndDialog(this)
        dialog.show()
        onStop()
    }
}
