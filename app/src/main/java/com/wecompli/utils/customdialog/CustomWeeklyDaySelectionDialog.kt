package com.wecompli.utils.customdialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import android.widget.Toast
import com.wecompli.databinding.CustomWeekSelectionLayoutBinding
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.AddCheckFragment
import com.wecompli.utils.customfont.CustomTypeface


class CustomWeeklyDaySelectionDialog(
    val mainActivity: MainActivity,
   val addCheckFragment: AddCheckFragment): Dialog(mainActivity) {
    var customWeekSelectionLayoutBinding:CustomWeekSelectionLayoutBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCanceledOnTouchOutside(false)
       // setCanceledOnTouchOutside(true)
        customWeekSelectionLayoutBinding= CustomWeekSelectionLayoutBinding.inflate(LayoutInflater.from(mainActivity))
        setContentView(customWeekSelectionLayoutBinding!!.root)
        customWeekSelectionLayoutBinding!!.tvSelectWeekly.typeface=CustomTypeface.getRajdhaniBold(mainActivity)
        customWeekSelectionLayoutBinding!!.chkMon.typeface=CustomTypeface.getRajdhaniSemiBold(mainActivity)
        customWeekSelectionLayoutBinding!!.chkTues.typeface=CustomTypeface.getRajdhaniSemiBold(mainActivity)
        customWeekSelectionLayoutBinding!!.chkWed.typeface=CustomTypeface.getRajdhaniSemiBold(mainActivity)
        customWeekSelectionLayoutBinding!!.chkThurs.typeface=CustomTypeface.getRajdhaniSemiBold(mainActivity)
        customWeekSelectionLayoutBinding!!.chkFri.typeface=CustomTypeface.getRajdhaniSemiBold(mainActivity)
        customWeekSelectionLayoutBinding!!.chkSatur.typeface=CustomTypeface.getRajdhaniSemiBold(mainActivity)
        customWeekSelectionLayoutBinding!!.chkSunday.typeface=CustomTypeface.getRajdhaniSemiBold(mainActivity)
        customWeekSelectionLayoutBinding!!.tvSave.typeface=CustomTypeface.getRajdhaniSemiBold(mainActivity)
        customWeekSelectionLayoutBinding!!.tvClose.typeface=CustomTypeface.getRajdhaniSemiBold(mainActivity)

        customWeekSelectionLayoutBinding!!.tvClose.setOnClickListener {
            dismiss()
        }
        customWeekSelectionLayoutBinding!!.tvSave.setOnClickListener {
           // addCheckFragment.

            if (customWeekSelectionLayoutBinding!!.chkMon.isChecked)
                addCheckFragment.selectedweekdays!!.add("7")
            if (customWeekSelectionLayoutBinding!!.chkTues.isChecked)
                addCheckFragment.selectedweekdays!!.add("8")
            if (customWeekSelectionLayoutBinding!!.chkWed.isChecked)
                addCheckFragment.selectedweekdays!!.add("9")
            if (customWeekSelectionLayoutBinding!!.chkThurs.isChecked)
                addCheckFragment.selectedweekdays!!.add("10")
            if (customWeekSelectionLayoutBinding!!.chkFri.isChecked)
                addCheckFragment.selectedweekdays!!.add("11")
            if (customWeekSelectionLayoutBinding!!.chkSatur.isChecked)
                addCheckFragment.selectedweekdays!!.add("12")
            if (customWeekSelectionLayoutBinding!!.chkSunday.isChecked)
                addCheckFragment.selectedweekdays!!.add("13")
            if (addCheckFragment.selectedweekdays!!.size>0){
                addCheckFragment.addcheckView!!.tvselecttype.setText("WEEKLY")
                dismiss()
            }else
                Toast.makeText(mainActivity,"Please select Day", Toast.LENGTH_LONG).show()


        }

    }
}