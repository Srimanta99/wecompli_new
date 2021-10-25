package com.wecompli.utils.customdialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.wecompli.adapter.MonthlyDaySelectionAdapter
import com.wecompli.databinding.CustomMonthDaySelectionLayoutBinding
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.AddCheckFragment
import com.wecompli.utils.customfont.CustomTypeface


class CustomMonthlyDaySelectionDialog(
    val mainActivity: MainActivity,
     val addCheckFragment: AddCheckFragment): Dialog(mainActivity) {
    var customMonthDaySelectionLayoutBinding: CustomMonthDaySelectionLayoutBinding?=null
    var monthlyDaySelectionAdapter:MonthlyDaySelectionAdapter?=null
    var daysArray:ArrayList<String>?= ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCanceledOnTouchOutside(false)
        customMonthDaySelectionLayoutBinding =CustomMonthDaySelectionLayoutBinding.inflate(LayoutInflater.from(mainActivity))
         setContentView(customMonthDaySelectionLayoutBinding!!.root)
        for ( i in 1 until 32){
            daysArray!!.add(i.toString())
        }
        customMonthDaySelectionLayoutBinding!!.tvSelectMonthly.typeface=CustomTypeface.getRajdhaniBold(mainActivity)
        customMonthDaySelectionLayoutBinding!!.tvClose.typeface=CustomTypeface.getRajdhaniSemiBold(mainActivity)
        customMonthDaySelectionLayoutBinding!!.tvSave.typeface=CustomTypeface.getRajdhaniSemiBold(mainActivity)
        customMonthDaySelectionLayoutBinding!!.recMonthlyday.layoutManager=GridLayoutManager(mainActivity,3)
        monthlyDaySelectionAdapter= MonthlyDaySelectionAdapter(daysArray,mainActivity,addCheckFragment)
        customMonthDaySelectionLayoutBinding!!.recMonthlyday.adapter=monthlyDaySelectionAdapter
        customMonthDaySelectionLayoutBinding!!.tvClose.setOnClickListener {
            dismiss()
        }
        customMonthDaySelectionLayoutBinding!!.tvSave.setOnClickListener {
            if ( addCheckFragment.seletedmonthdDay!!.size>0) {
                addCheckFragment.addcheckView!!.tvselecttype.setText("MONTHLY")
                dismiss()
            }else
                Toast.makeText(mainActivity,"Please select date", Toast.LENGTH_LONG).show()
        }

    }
}