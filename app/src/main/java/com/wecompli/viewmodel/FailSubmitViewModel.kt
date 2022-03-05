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

    fun  selectImg1(view:View){
        failsubmithandler!!.selectImg1()
    }
    fun  selectImg2(view:View){
        failsubmithandler!!.selectImg2()
    }
    fun  selectImg3(view:View){
        failsubmithandler!!.selectImg3()
    }
    fun  selectImg4(view:View){
        failsubmithandler!!.selectImg4()
    }
}