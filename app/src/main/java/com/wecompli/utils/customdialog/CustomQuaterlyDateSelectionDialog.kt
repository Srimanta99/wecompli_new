package com.wecompli.utils.customdialog

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import android.widget.Toast
import com.wecompli.databinding.CustomQuaterlyDateSelectionLayoutBinding
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.AddCheckFragment
import com.wecompli.utils.customfont.CustomTypeface
import java.util.*


class CustomQuaterlyDateSelectionDialog(
    val mainActivity: MainActivity,
    val addCheckFragment: AddCheckFragment
): Dialog(mainActivity) {
     var customQuaterlyDateSelectionLayoutBinding: CustomQuaterlyDateSelectionLayoutBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCanceledOnTouchOutside(false)
        customQuaterlyDateSelectionLayoutBinding =CustomQuaterlyDateSelectionLayoutBinding.inflate(
            LayoutInflater.from(
                mainActivity
            )
        )
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
        val calendar: Calendar = Calendar.getInstance()
        val year: Int = calendar.get(Calendar.YEAR)
        val month: Int = calendar.get(Calendar.MONTH)
        val dayOfMonth: Int = calendar.get(Calendar.DAY_OF_MONTH)
        customQuaterlyDateSelectionLayoutBinding!!.rlQuater1.setOnClickListener {
           val datePickerDialog = DatePickerDialog(mainActivity,
                { datePicker, year, month, day ->
                    customQuaterlyDateSelectionLayoutBinding!!.tvQuater1Date.setText(day.toString()+"/"+month.toString()+"/"+year.toString())
                    addCheckFragment!!.selectedquaterlyDay!!.add(day.toString()+"-"+month.toString())
                }, year, month, dayOfMonth
            )
            datePickerDialog.show();
        }

        customQuaterlyDateSelectionLayoutBinding!!.rlQuater2.setOnClickListener {
            val datePickerDialog = DatePickerDialog(mainActivity,
                { datePicker, year, month, day ->
                    customQuaterlyDateSelectionLayoutBinding!!.tvQuater2Date.setText(day.toString()+"/"+month.toString()+"/"+year.toString())
                    addCheckFragment!!.selectedquaterlyDay!!.add(day.toString()+"-"+month.toString())
                }, year, month, dayOfMonth
            )
            datePickerDialog.show();
        }

        customQuaterlyDateSelectionLayoutBinding!!.rlQuater3.setOnClickListener {
            val datePickerDialog = DatePickerDialog(mainActivity,
                { datePicker, year, month, day ->
                    customQuaterlyDateSelectionLayoutBinding!!.tvQuater3Date.setText(day.toString()+"/"+month.toString()+"/"+year.toString())
                    addCheckFragment!!.selectedquaterlyDay!!.add(day.toString()+"-"+month.toString())
                }, year, month, dayOfMonth
            )
            datePickerDialog.show();
        }

        customQuaterlyDateSelectionLayoutBinding!!.rlQuater4.setOnClickListener {
            val datePickerDialog = DatePickerDialog(mainActivity,
                { datePicker, year, month, day ->
                    customQuaterlyDateSelectionLayoutBinding!!.tvQuater4Date.setText(day.toString()+"/"+month.toString()+"/"+year.toString())
                    addCheckFragment!!.selectedquaterlyDay!!.add(day.toString()+"-"+month.toString())
                }, year, month, dayOfMonth
            )
            datePickerDialog.show();
        }

        customQuaterlyDateSelectionLayoutBinding!!.tvClose.setOnClickListener {
            dismiss()
        }
        customQuaterlyDateSelectionLayoutBinding!!.tvSave.setOnClickListener {
            if (!customQuaterlyDateSelectionLayoutBinding!!.tvQuater1Date.text.toString().equals("")||!customQuaterlyDateSelectionLayoutBinding!!.tvQuater2Date.text.toString().equals("")
                ||!customQuaterlyDateSelectionLayoutBinding!!.tvQuater3Date.text.toString().equals("")||!customQuaterlyDateSelectionLayoutBinding!!.tvQuater4Date.text.toString().equals(""))
            {
                addCheckFragment.addcheckView!!.tvselecttype.setText("QUATERLY")
                dismiss()
            }
            else
              Toast.makeText(mainActivity,"Please select date",Toast.LENGTH_LONG).show()


        }

    }
}