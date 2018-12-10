package com.gram.cmr.View

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.gram.cmr.R
import com.gram.cmr.DialogViewModel
import com.gram.cmr.databinding.GgDialogBinding

class EndDialog(context: Context): Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = DataBindingUtil.inflate<GgDialogBinding>(LayoutInflater.from(context), R.layout.gg_dialog, null, false)
        val dialogViewModel = DialogViewModel()
        binding.dialogViewModel = dialogViewModel
    }
}