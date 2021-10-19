package com.wecompli.utils.customdialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import com.wecompli.databinding.CustomQuaterlyDateSelectionLayoutBinding

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
        customQuaterlyDateSelectionLayoutBinding!!.tvSelectQuaterly.typeface=CustomTypeface.getRajdhaniBold(mainActivity)
        customQuaterlyDateSelectionLayoutBinding!!.tvSave.typeface=CustomTypeface.getRajdhaniSemiBold(mainActivity)
        customQuaterlyDateSelectionLayoutBinding!!.tvClose.typeface=CustomTypeface.getRajdhaniSemiBold(mainActivity)
        customQuaterlyDateSelectionLayoutBinding!!.tvQuater1.typeface=CustomTypeface.getRajdhaniSemiBold(mainActivity)
        customQuaterlyDateSelectionLayoutBinding!!.tvQuater2.typeface=CustomTypeface.getRajdhaniSemiBold(mainActivity)
        customQuaterlyDateSelectionLayoutBinding!!.tvQuater3.typeface=CustomTypeface.getRajdhaniSemiBold(mainActivity)
        customQuaterlyDateSelectionLayoutBinding!!.tvQuater4.typeface=CustomTypeface.getRajdhaniSemiBold(mainActivity)
        customQuaterlyDateSelectionLayoutBinding!!.tvQuater1Date.typeface=CustomTypeface.getRajdhaniSemiBold(mainActivity)
        customQuaterlyDateSelectionLayoutBinding!!.tvQuater2Date.typeface=CustomTypeface.getRajdhaniSemiBold(mainActivity)
        customQuaterlyDateSelectionLayoutBinding!!.tvQuater3Date.typeface=CustomTypeface.getRajdhaniSemiBold(mainActivity)
        customQuaterlyDateSelectionLayoutBinding!!.tvQuater4Date.typeface=CustomTypeface.getRajdhaniSemiBold(mainActivity)



        customQuaterlyDateSelectionLayoutBinding!!.tvClose.setOnClickListener {
            dismiss()
        }
        customQuaterlyDateSelectionLayoutBinding!!.tvSave.setOnClickListener {
               dismiss()
        }

    }
}