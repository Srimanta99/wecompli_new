package com.wecompli.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.wecompli.handler.AddUserHandler

class EditUserViewModel:ViewModel() {
    var addUserHandler: AddUserHandler?=null
    fun opesiteList(view: View){
        addUserHandler!!.siteselection()
    }
    fun openRoleList(view: View){
        addUserHandler!!.roleselection()
    }
    fun selectStatus(view: View){
        addUserHandler!!.onstatusSelect()
    }
    fun onSubmit(view: View){
        addUserHandler!!.onsubmit()
    }
}