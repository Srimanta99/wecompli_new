package com.wecompli.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.wecompli.handler.DashBoardHandler

class DashBoardViewModel:ViewModel() {
    var dashBoardHandler:DashBoardHandler?=null
    fun onAdhocClick(view:View){
        dashBoardHandler!!.openAdhocfault()
    }
}