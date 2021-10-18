package com.wecompli.utils.customdialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import com.wecompli.databinding.CustomMonthDaySelectionLayoutBinding
import com.wecompli.databinding.CustomQuaterlyDateSelectionLayoutBinding
import com.wecompli.databinding.CustomWeekSelectionLayoutBinding
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.AddCheckFragment
import com.wecompli.utils.customfont.CustomTypeface


class CustomQuaterlyDateSelectionDialog(
    val mainActivity: MainActivity,
     val addCheckFragment: AddCheckFragment): Dialog(mainActivity) {
    var customQuaterlyDateSelectionLayoutBinding: CustomQuaterlyDateSelectionLayoutBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCanceledOnTouchOutside(false)
        customQuaterlyDateSelectionLayoutBinding =CustomQuaterlyDateSelectionLayoutBinding.inflate(LayoutInflater.from(mainActivity))
         setContentView(customQuaterlyDateSelectionLayoutBinding!!.root)
        customQuaterlyDateSelectionLayoutBinding!!.tvClose.setOnClickListener {
            dismiss()
        }
        customQuaterlyDateSelectionLayoutBinding!!.tvSave.setOnClickListener {
               dismiss()
        }

    }
}