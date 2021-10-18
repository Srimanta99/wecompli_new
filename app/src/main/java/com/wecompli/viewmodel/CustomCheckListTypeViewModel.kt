package com.wecompli.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.wecompli.handler.CheckTypeSelectionHandler

class CustomCheckListTypeViewModel:ViewModel() {
    var checkTypeSelectionHandler:CheckTypeSelectionHandler?=null

    fun dailySelect(view:View){
        checkTypeSelectionHandler!!.dailySelect()
    }
    fun weeklySelect(view:View){
        checkTypeSelectionHandler!!.weeklySelect()
    }
    fun monthlySelect(view:View){
        checkTypeSelectionHandler!!.monthlySelect()
    }
    fun quaterlySeelct(view: View){
        checkTypeSelectionHandler!!.quaterlySeelct()
    }
    fun halfyearlySelect(view: View){
        checkTypeSelectionHandler!!.halfyearlySelect()
    }
    fun interdaySelect(view: View){
        checkTypeSelectionHandler!!.interdaySelect()
    }
    fun yearlySelect(view:View){
        checkTypeSelectionHandler!!.yearlySelect()
    }
}