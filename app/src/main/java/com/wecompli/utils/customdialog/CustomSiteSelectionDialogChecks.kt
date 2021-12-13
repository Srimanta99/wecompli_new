package com.wecompli.utils.customdialog

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wecompli.R
import com.wecompli.adapter.SiteSelectionListAdapterChecks
import com.wecompli.adapter.SiteSelectionListAdapterStartCheck
import com.wecompli.model.SiteListResponseModel
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.ChecksFragment
import com.wecompli.screens.fragment.StartCheckFragment
import com.wecompli.utils.customfont.CustomTypeface
import java.util.ArrayList

class CustomSiteSelectionDialogChecks(
    val mainActivity: MainActivity,
    val siteList: ArrayList<SiteListResponseModel.SiteDetails>,
    val CheckFragment: ChecksFragment
):Dialog(mainActivity) {
    var siteSelectionListAdapte:SiteSelectionListAdapterChecks?=null
    var reclyerview:RecyclerView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCanceledOnTouchOutside(true)
        setContentView(R.layout.custom_site_selection_dialog_layout)
         reclyerview=findViewById(R.id.rec_sitelist)
        val tv_ok:TextView=findViewById(R.id.tv_ok)
        val cancel:TextView=findViewById(R.id.tv_no)
        val tv_selectsite:TextView=findViewById(R.id.tv_selectsite)
        tv_selectsite.typeface=CustomTypeface.getRajdhaniBold(mainActivity)
        tv_ok.typeface=CustomTypeface.getRajdhaniMedium(mainActivity)
        cancel.typeface=CustomTypeface.getRajdhaniMedium(mainActivity)
        tv_ok.setOnClickListener {
            CheckFragment.setselection();
            dismiss()
        }
        cancel.setOnClickListener {
            dismiss()
        }
        siteSelectionListAdapte= SiteSelectionListAdapterChecks(mainActivity,siteList,CheckFragment,this)
        reclyerview!!.adapter=siteSelectionListAdapte

    }

    public  fun setselection(position: Int) {
        for (i in 0 until siteList!!.size) {
            if (position==i) {
                siteList!!.get(i).isselect=true
            }else
                siteList!!.get(i).isselect=false
        }
        siteSelectionListAdapte= SiteSelectionListAdapterChecks(mainActivity,siteList,CheckFragment,this)
        reclyerview!!.adapter=siteSelectionListAdapte
    }


}