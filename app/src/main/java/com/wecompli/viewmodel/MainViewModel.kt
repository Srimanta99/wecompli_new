package com.wecompli.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.wecompli.handler.MainHandler

class MainViewModel:ViewModel() {
    var mainHandler:MainHandler?=null

    fun openDrawer(view: View){
        mainHandler!!.opendrawer()
    }

    fun Logout(view:View){
        mainHandler!!.logout()
    }
    fun opensiteList(view:View){
        mainHandler!!.openSitelist()
    }
    fun openDashboard(view: View){
        mainHandler!!.opdashboard()
    }
    fun openRolesList(view: View){
        mainHandler!!.openUserList()
    }
    fun opencheckList(view: View){
        mainHandler!!.openCheckLists()
    }

    fun subcription(view:View){
        mainHandler!!.subcription()
    }
    fun faultList(view:View){
        mainHandler!!.openFaults()
    }
    fun openDocument(view:View){
        mainHandler!!.documents()
    }
    fun openincidents(view:View){
        mainHandler!!.incidents()
    }

    fun openemailList(view: View){
        mainHandler!!.email()
    }
}