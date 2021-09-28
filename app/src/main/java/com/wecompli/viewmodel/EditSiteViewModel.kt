package com.wecompli.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.wecompli.handler.EditSiteHandler

class EditSiteViewModel:ViewModel() {
    var editSiteHandler:EditSiteHandler?=null
    fun onClickSubmit(view : View){
        editSiteHandler!!.addSiteSubmit()
    }
    fun onselectStatusSelection(view: View){
        editSiteHandler!!.openStatusSelection()
    }
}