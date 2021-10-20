package com.wecompli.utils.customdialog

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import com.wecompli.databinding.CustomMonthDaySelectionLayoutBinding
import com.wecompli.databinding.CustomWeekSelectionLayoutBinding
import com.wecompli.databinding.CustomYearlyDateSelectionLayoutBinding
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.AddCheckFragment
import com.wecompli.utils.customfont.CustomTypeface
import java.util.*


class CustomYearlyDateSelectionDialog(
    val mainActivity: MainActivity,
    val addCheckFragment: AddCheckFragment): Dialog(mainActivity) {
    var customyearlyDateSelectionLayoutBinding: CustomYearlyDateSelectionLayoutBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCanceledOnTouchOutside(false)
        customyearlyDateSelectionLayoutBinding =CustomYearlyDateSelectionLayoutBinding.inflate(LayoutInflater.from(mainActivity))
         setContentView(customyearlyDateSelectionLayoutBinding!!.root)
        customyearlyDateSelectionLayoutBinding!!.tvSelectYearly.typeface=CustomTypeface.getRajdhaniBold(mainActivity)
        customyearlyDateSelectionLayoutBinding!!.tvSave.typeface=CustomTypeface.getRajdhaniSemiBold(mainActivity)
        customyearlyDateSelectionLayoutBinding!!.tvClose.typeface=CustomTypeface.getRajdhaniSemiBold(mainActivity)
        customyearlyDateSelectionLayoutBinding!!.tvQuater1.typeface=CustomTypeface.getRajdhaniSemiBold(mainActivity)

        customyearlyDateSelectionLayoutBinding!!.tvQuater1Date.typeface=CustomTypeface.getRajdhaniSemiBold(mainActivity)

        val calendar: Calendar = Calendar.getInstance()
        val year: Int = calendar.get(Calendar.YEAR)
        val month: Int = calendar.get(Calendar.MONTH)
        val dayOfMonth: Int = calendar.get(Calendar.DAY_OF_MONTH)
        customyearlyDateSelectionLayoutBinding!!.rlQuater1.setOnClickListener {
            val datePickerDialog = DatePickerDialog(mainActivity,
                { datePicker, year, month, day ->
                    customyearlyDateSelectionLayoutBinding!!.tvQuater1Date.setText(day.toString()+"/"+month.toString()+"/"+year.toString())
                }, year, month, dayOfMonth
            )
            datePickerDialog.show();
        }
        customyearlyDateSelectionLayoutBinding!!.tvClose.setOnClickListener {
            dismiss()
        }
        customyearlyDateSelectionLayoutBinding!!.tvSave.setOnClickListener {
               dismiss()
        }

    }
}