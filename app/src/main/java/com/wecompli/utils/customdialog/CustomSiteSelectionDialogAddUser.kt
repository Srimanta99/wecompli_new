package com.wecompli.utils.customdialog

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wecompli.R
import com.wecompli.adapter.SiteSelectionListAdapter
import com.wecompli.adapter.SiteSelectionListAdapterAddUser
import com.wecompli.model.SiteListResponseModel
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.AddUserFragment
import com.wecompli.screens.fragment.DashBoardFragment
import com.wecompli.utils.customfont.CustomTypeface
import java.util.ArrayList

class CustomSiteSelectionDialogAddUser(
    val mainActivity: MainActivity,
    val siteList: ArrayList<SiteListResponseModel.SiteDetails>,
    val addUserFragment: AddUserFragment):Dialog(mainActivity) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCanceledOnTouchOutside(true)
        setContentView(R.layout.custom_site_selection_dialog_layout)
        val reclyerview:RecyclerView=findViewById(R.id.rec_sitelist)
        val tv_ok:TextView=findViewById(R.id.tv_ok)
        val cancel:TextView=findViewById(R.id.tv_no)
        val tv_selectsite:TextView=findViewById(R.id.tv_selectsite)
        tv_selectsite.typeface=CustomTypeface.getRajdhaniBold(mainActivity)
        tv_ok.typeface=CustomTypeface.getRajdhaniMedium(mainActivity)
        cancel.typeface=CustomTypeface.getRajdhaniMedium(mainActivity)
        tv_ok.setOnClickListener {
            addUserFragment.setselection()
            dismiss()
        }
        cancel.setOnClickListener {
            dismiss()
        }
        var siteSelectionListAdapter=SiteSelectionListAdapterAddUser(mainActivity,siteList,addUserFragment)
        reclyerview.adapter=siteSelectionListAdapter

    }
}