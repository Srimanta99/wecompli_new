package com.wecompli.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.wecompli.handler.FailSubmitHandler

class FailSubmitViewModel:ViewModel() {
    var failsubmithandler:FailSubmitHandler?=null

    fun failSubmit(view:View){
        failsubmithandler!!.submitfail()
    }

    fun notify(view:View){
        failsubmithandler!!.notifywho()
    }
}