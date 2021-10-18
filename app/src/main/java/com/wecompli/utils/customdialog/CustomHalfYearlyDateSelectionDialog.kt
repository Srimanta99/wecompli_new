package com.wecompli.utils.customdialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import com.wecompli.databinding.CustomHalfYearlyDateSelectionLayoutBinding
import com.wecompli.databinding.CustomMonthDaySelectionLayoutBinding
import com.wecompli.databinding.CustomWeekSelectionLayoutBinding
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.AddCheckFragment
import com.wecompli.utils.customfont.CustomTypeface


class CustomHalfYearlyDateSelectionDialog(
    val mainActivity: MainActivity,
     val addCheckFragment: AddCheckFragment): Dialog(mainActivity) {
    var customHalfYearlyDateSelectionLayoutBinding: CustomHalfYearlyDateSelectionLayoutBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCanceledOnTouchOutside(false)
        customHalfYearlyDateSelectionLayoutBinding =CustomHalfYearlyDateSelectionLayoutBinding.inflate(LayoutInflater.from(mainActivity))
         setContentView(customHalfYearlyDateSelectionLayoutBinding!!.root)
        customHalfYearlyDateSelectionLayoutBinding!!.tvClose.setOnClickListener {
            dismiss()
        }
        customHalfYearlyDateSelectionLayoutBinding!!.tvSave.setOnClickListener {
               dismiss()
        }

    }
}