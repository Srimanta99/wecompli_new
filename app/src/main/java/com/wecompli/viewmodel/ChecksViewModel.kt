package com.wecompli.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.wecompli.handler.ChecksHandler

class ChecksViewModel:ViewModel() {
    var checksHandler: ChecksHandler?=null
    fun openaddCheck(view: View){
        checksHandler!!.openAddCheck()
    }

}