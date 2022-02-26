package com.wecompli.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.wecompli.handler.CheckListHandler

class ChecksListViewModel:ViewModel() {
    var checklisthander:CheckListHandler?=null
    fun addnew(view: View){
        checklisthander!!.addnewCheck()
    }
    fun openSearchDrawer(view:View){
        checklisthander!!.opensearchDrawer()
    }
    fun openmenu(view:View){
        checklisthander!!.openmenu()
    }
}