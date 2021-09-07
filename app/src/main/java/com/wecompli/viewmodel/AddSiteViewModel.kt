package com.wecompli.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.wecompli.handler.AddSiteHandler

class AddSiteViewModel:ViewModel() {

    var addSiteHandler:AddSiteHandler?=null

     fun onClickSubmit(view :View){
         addSiteHandler!!.addSiteSubmit()
     }
    fun onselectStatusSelection(view:View){
        addSiteHandler!!.openStatusSelection()
    }
}