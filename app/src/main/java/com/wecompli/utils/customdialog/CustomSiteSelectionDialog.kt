package com.wecompli.utils.customdialog

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wecompli.R
import com.wecompli.adapter.SiteSelectionListAdapter
import com.wecompli.model.SiteListResponseModel
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.DashBoardFragment
import java.util.ArrayList

class CustomSiteSelectionDialog(
    val mainActivity: MainActivity,
    val siteList: ArrayList<SiteListResponseModel.SiteDetails>,
    val dashBoardFragment: DashBoardFragment
):Dialog(mainActivity) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCanceledOnTouchOutside(true)
        setContentView(R.layout.custom_site_selection_dialog_layout)
        val reclyerview:RecyclerView=findViewById(R.id.rec_sitelist)
        val tv_ok:TextView=findViewById(R.id.tv_ok)
        val cancel:TextView=findViewById(R.id.tv_no)
        tv_ok.setOnClickListener {
         dashBoardFragment.setselection()
            dismiss()
        }
        cancel.setOnClickListener {
            dismiss()
        }
        var siteSelectionListAdapter= SiteSelectionListAdapter(mainActivity,siteList,dashBoardFragment)
        reclyerview.adapter=siteSelectionListAdapter

    }
}