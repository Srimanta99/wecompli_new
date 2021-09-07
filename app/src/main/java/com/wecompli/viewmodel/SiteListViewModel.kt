package com.wecompli.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.wecompli.handler.SiteListHandler

class SiteListViewModel:ViewModel() {
    var siteListHandler:SiteListHandler?=null

    fun openAddSite(view:View){
        siteListHandler!!.addSite()
    }
}