package com.wecompli.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.wecompli.handler.CheckSubmitHandler

class CheckSubmitViewModel:ViewModel(){
    var checkSubmitHandler:CheckSubmitHandler?=null

    fun  selectImg1(view:View){
        checkSubmitHandler!!.selectImg1()
    }
    fun  selectImg2(view:View){
        checkSubmitHandler!!.selectImg2()
    }
    fun  selectImg3(view:View){
        checkSubmitHandler!!.selectImg3()
    }
    fun  selectImg4(view:View){
       // checkSubmitHandler!!.selectImg4()
    }
    fun submit(view: View){
        checkSubmitHandler!!.checkSubmit()
    }

    fun taptosign(view: View){
        checkSubmitHandler!!.taptosign()
    }

    fun settemparature(view: View){
        checkSubmitHandler!!.settemp()
    }

    fun openqrscanner(view:View){
        checkSubmitHandler!!.openqrscanner()
    }
}