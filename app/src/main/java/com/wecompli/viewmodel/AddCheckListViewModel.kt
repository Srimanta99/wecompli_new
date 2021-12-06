package com.wecompli.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.wecompli.handler.AddCheckHandler

class AddCheckListViewModel:ViewModel() {
    var addCheckHandler:AddCheckHandler?=null
    fun openiteSelection(view:View){
      addCheckHandler!!.openSite()
    }

    fun submitCheck(view:View){
        addCheckHandler!!.submitCheck()
    }

    fun selectType(view:View){
        addCheckHandler!!.selectType()
    }

    fun selectStatus(view:View){
        addCheckHandler!!.selectStatus()
    }

}