package com.wecompli.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.wecompli.handler.StartCheckHandler

class StartChechViewModel:ViewModel() {
    var startCheckHandler:StartCheckHandler?=null
     fun openSiteDropDown(view:View){
         startCheckHandler!!.selectSite()
     }
     fun openDateSelection(view:View){
         startCheckHandler!!.selectDate()
     }
    fun submitSearch(view:View){
        startCheckHandler!!.search()
    }

}