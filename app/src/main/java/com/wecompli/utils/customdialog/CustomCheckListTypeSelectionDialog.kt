package com.wecompli.utils.customdialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import androidx.lifecycle.ViewModelProviders
import com.wecompli.databinding.CustomChecklistSelectionDialogLayoutBinding
import com.wecompli.handler.CheckTypeSelectionHandler

import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.AddCheckFragment
import com.wecompli.utils.customfont.CustomTypeface
import com.wecompli.viewmodel.CustomCheckListTypeViewModel

class CustomCheckListTypeSelectionDialog(val mainActivity: MainActivity, val addCheckFragment: AddCheckFragment): Dialog(mainActivity),
    CheckTypeSelectionHandler {
    var customcheckTypeview: CustomChecklistSelectionDialogLayoutBinding?=null
    var cusChkListTyViewModel:CustomCheckListTypeViewModel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCanceledOnTouchOutside(true)
        customcheckTypeview=CustomChecklistSelectionDialogLayoutBinding.inflate(LayoutInflater.from(mainActivity))
        cusChkListTyViewModel=ViewModelProviders.of(mainActivity).get(CustomCheckListTypeViewModel::class.java)
        customcheckTypeview!!.selectCheckList=cusChkListTyViewModel
        cusChkListTyViewModel!!.checkTypeSelectionHandler=this
        setContentView(customcheckTypeview!!.root)
        customcheckTypeview!!.tvSelectType.setTypeface(CustomTypeface.getRajdhaniBold(mainActivity))
        customcheckTypeview!!.tvDaily.setTypeface(CustomTypeface.getRajdhaniSemiBold(mainActivity))
        customcheckTypeview!!.tvInterday.setTypeface(CustomTypeface.getRajdhaniSemiBold(mainActivity))
        customcheckTypeview!!.tvWeekly.setTypeface(CustomTypeface.getRajdhaniSemiBold(mainActivity))
        customcheckTypeview!!.quaterly.setTypeface(CustomTypeface.getRajdhaniSemiBold(mainActivity))
        customcheckTypeview!!.tvHalfYearly.setTypeface(CustomTypeface.getRajdhaniSemiBold(mainActivity))
        customcheckTypeview!!.tvMonthly.setTypeface(CustomTypeface.getRajdhaniSemiBold(mainActivity))
        customcheckTypeview!!.tvYearly.setTypeface(CustomTypeface.getRajdhaniSemiBold(mainActivity))


    }

    override fun dailySelect() {
        addCheckFragment.addcheckView!!.tvselecttype.setText("DAILY")
        dismiss()
    }

    override fun weeklySelect() {
        addCheckFragment.addcheckView!!.tvselecttype.setText("WEEKLY")
        dismiss()
        val customweklySelectionDialog=CustomWeeklyDaySelectionDialog(mainActivity,addCheckFragment)
        customweklySelectionDialog.show()

    }

    override fun monthlySelect() {
        addCheckFragment.addcheckView!!.tvselecttype.setText("MONTHLY")
        dismiss()
        val customMonthlySelectionDialog=CustomMonthlyDaySelectionDialog(mainActivity,addCheckFragment)
        customMonthlySelectionDialog.show()
    }

    override fun quaterlySeelct() {
        addCheckFragment.addcheckView!!.tvselecttype.setText("QUATERLY")
        dismiss()
        val customquaterlySelectionDialog=CustomQuaterlyDateSelectionDialog(mainActivity,addCheckFragment)
        customquaterlySelectionDialog.show()
    }

    override fun halfyearlySelect() {
        addCheckFragment.addcheckView!!.tvselecttype.setText("HALF-YEARLY")
        dismiss()
        val customhalfyearlySelectionDialog=CustomHalfYearlyDateSelectionDialog(mainActivity,addCheckFragment)
        customhalfyearlySelectionDialog.show()
    }

    override fun interdaySelect() {
        addCheckFragment.addcheckView!!.tvselecttype.setText("INTER DAY")
        dismiss()
    }

    override fun yearlySelect() {
        addCheckFragment.addcheckView!!.tvselecttype.setText("YEARLY")
        dismiss()
        val customyearlySelectionDialog=CustomYearlyDateSelectionDialog(mainActivity,addCheckFragment)
        customyearlySelectionDialog.show()
    }
}