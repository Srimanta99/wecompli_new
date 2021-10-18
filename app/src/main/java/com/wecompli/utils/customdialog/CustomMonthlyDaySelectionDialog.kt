package com.wecompli.utils.customdialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import com.wecompli.databinding.CustomMonthDaySelectionLayoutBinding
import com.wecompli.databinding.CustomWeekSelectionLayoutBinding
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.AddCheckFragment
import com.wecompli.utils.customfont.CustomTypeface


class CustomMonthlyDaySelectionDialog(
    val mainActivity: MainActivity,
     val addCheckFragment: AddCheckFragment): Dialog(mainActivity) {
    var customMonthDaySelectionLayoutBinding: CustomMonthDaySelectionLayoutBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCanceledOnTouchOutside(false)
        customMonthDaySelectionLayoutBinding =CustomMonthDaySelectionLayoutBinding.inflate(LayoutInflater.from(mainActivity))
         setContentView(customMonthDaySelectionLayoutBinding!!.root)
        customMonthDaySelectionLayoutBinding!!.tvClose.setOnClickListener {
            dismiss()
        }
        customMonthDaySelectionLayoutBinding!!.tvSave.setOnClickListener {
               dismiss()
        }

    }
}