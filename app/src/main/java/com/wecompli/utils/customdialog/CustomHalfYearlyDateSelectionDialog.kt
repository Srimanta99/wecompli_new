package com.wecompli.utils.customdialog

import android.app.DatePickerDialog
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
import java.util.*


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
        customHalfYearlyDateSelectionLayoutBinding!!.tvSelectHalfyearly.typeface=CustomTypeface.getRajdhaniBold(mainActivity)
        customHalfYearlyDateSelectionLayoutBinding!!.tvSave.typeface=CustomTypeface.getRajdhaniSemiBold(mainActivity)
        customHalfYearlyDateSelectionLayoutBinding!!.tvClose.typeface=CustomTypeface.getRajdhaniSemiBold(mainActivity)
        customHalfYearlyDateSelectionLayoutBinding!!.tvQuater1.typeface=CustomTypeface.getRajdhaniSemiBold(mainActivity)
        customHalfYearlyDateSelectionLayoutBinding!!.tvQuater2.typeface=CustomTypeface.getRajdhaniSemiBold(mainActivity)

        customHalfYearlyDateSelectionLayoutBinding!!.tvQuater1Date.typeface=CustomTypeface.getRajdhaniSemiBold(mainActivity)
        customHalfYearlyDateSelectionLayoutBinding!!.tvQuater2Date.typeface=CustomTypeface.getRajdhaniSemiBold(mainActivity)
        val calendar: Calendar = Calendar.getInstance()
        val year: Int = calendar.get(Calendar.YEAR)
        val month: Int = calendar.get(Calendar.MONTH)
        val dayOfMonth: Int = calendar.get(Calendar.DAY_OF_MONTH)
        customHalfYearlyDateSelectionLayoutBinding!!.rlQuater1.setOnClickListener {
            val datePickerDialog = DatePickerDialog(mainActivity,
                { datePicker, year, month, day ->
                    customHalfYearlyDateSelectionLayoutBinding!!.tvQuater1Date.setText(day.toString()+"/"+month.toString()+"/"+year.toString())
                }, year, month, dayOfMonth
            )
            datePickerDialog.show();
        }

        customHalfYearlyDateSelectionLayoutBinding!!.rlQuater2.setOnClickListener {
            val datePickerDialog = DatePickerDialog(mainActivity,
                { datePicker, year, month, day ->
                    customHalfYearlyDateSelectionLayoutBinding!!.tvQuater2Date.setText(day.toString()+"/"+month.toString()+"/"+year.toString())
                }, year, month, dayOfMonth
            )
            datePickerDialog.show();
        }

        customHalfYearlyDateSelectionLayoutBinding!!.tvClose.setOnClickListener {
            dismiss()
        }
        customHalfYearlyDateSelectionLayoutBinding!!.tvSave.setOnClickListener {
               dismiss()
        }

    }
}