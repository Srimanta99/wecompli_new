package com.wecompli.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.wecompli.handler.AddRoleHandler

class AddRoleViewModel:ViewModel() {
    var addRoleHandler:AddRoleHandler?=null


    fun dasdBoardClick(view: View){
        addRoleHandler!!.dashboardcheckClick()
    }
    fun sitesClick(view:View){
        addRoleHandler!!.sitecheckClick()
    }

    fun userClick(view:View){
        addRoleHandler!!.usercheckClick()
    }

    fun rolesClick(view:View){
        addRoleHandler!!.rolesCheckclick()
    }

    fun checksCLick(view:View){
        addRoleHandler!!.checksClick()
    }

    fun faultClick(view:View){
        addRoleHandler!!.faultsCheckClick()
    }

    fun incidentsClick(view:View){
        addRoleHandler!!.incidentscheckClick()
    }

    fun documentsClick(view: View){
        addRoleHandler!!.documentCheckClick()
    }

    fun helpClick(view: View){
        addRoleHandler!!.helpChecksClick()
    }

    fun settingClick(view:View){
        addRoleHandler!!.settingChecksClick()
    }

    fun submitclick(view:View){
       addRoleHandler!!.addroleSubmit()
    }

}