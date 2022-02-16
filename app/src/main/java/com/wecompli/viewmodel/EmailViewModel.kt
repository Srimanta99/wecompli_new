package com.wecompli.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.wecompli.handler.EmailListHandler

class EmailViewModel:ViewModel() {
    var emailListHandler:EmailListHandler?=null
    fun addEmail(view:View){
        emailListHandler!!.addEmail()
    }
}