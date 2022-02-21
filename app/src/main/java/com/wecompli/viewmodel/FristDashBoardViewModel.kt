package com.wecompli.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.wecompli.handler.FristTimeDashBoardHandler

class FristDashBoardViewModel:ViewModel() {
    var fristTimeDashBoardHandler:FristTimeDashBoardHandler?=null

    fun openAddSite(view: View){
        fristTimeDashBoardHandler!!.addSite()
    }

    fun openAddUser(view:View){
        fristTimeDashBoardHandler!!.adduser()
    }
}