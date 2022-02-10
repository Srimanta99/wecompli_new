package com.wecompli.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.wecompli.handler.FaultListHandler

class FaultListViewModel:ViewModel() {
    var faultListHandler:FaultListHandler?=null
    fun download(view: View){

    }
    fun openlist(view: View){
        faultListHandler!!.selectsite()
    }
}