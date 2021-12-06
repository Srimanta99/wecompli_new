package com.wecompli.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.wecompli.handler.AddChecksHandler

class AddChecksViewModel:ViewModel() {
        var addchecksHandler:AddChecksHandler?=null

    fun showDropdown(view:View){
        addchecksHandler!!.siteDropdoen()
    }

    fun showStatus(view:View){
        addchecksHandler!!.openStatusSelection()
    }

    fun submitCheck(view:View){
        addchecksHandler!!.submitCheck()
    }
    fun hasQrcode(view:View){
        addchecksHandler!!.hasqrCode()
    }
}