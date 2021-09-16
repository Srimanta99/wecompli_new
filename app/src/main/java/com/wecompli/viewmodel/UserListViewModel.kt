package com.wecompli.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.wecompli.handler.UserListhandler

class UserListViewModel:ViewModel() {
    var userListHandler: UserListhandler?=null

    fun roleList(view:View){
        userListHandler!!.showrolelist()
    }
    fun addrole(view:View){
       userListHandler!!.addrole()
    }
    fun addsite(view:View){
        userListHandler!!.adduser()
    }
}