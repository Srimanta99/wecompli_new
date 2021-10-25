package com.wecompli.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.recyclerview.widget.RecyclerView
import com.wecompli.R
import com.wecompli.databinding.ItemSiteSelectBinding
import com.wecompli.model.SiteListResponseModel
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.DashBoardFragment
import com.wecompli.utils.customfont.CustomTypeface

class SiteSelectionListAdapter(
    val activity: MainActivity,
    val siteList: ArrayList<SiteListResponseModel.SiteDetails>,
    val dashBoardFragment: DashBoardFragment
)
    : RecyclerView.Adapter<SiteSelectionListAdapter.ViewHolder>(){
    //var itemView:ItemSiteSelectBinding?=null
    class ViewHolder(val itemSite: View):RecyclerView.ViewHolder(itemSite){
        val tvSitename:TextView=itemSite.findViewById(R.id.tv_sitename)
        val chkSelect:AppCompatCheckBox=itemSite.findViewById(R.id.chk_select)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(activity).inflate(R.layout.item_site_select,parent,false)
       /* val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSiteSelectBinding.inflate(inflater)
        itemView=binding*/
        return ViewHolder(view)
    }

    override fun onBindViewHolder(itemView: ViewHolder, position: Int) {
        itemView!!.tvSitename.setText(siteList.get(position).site_name)
        itemView!!.tvSitename.typeface=CustomTypeface.getRajdhaniMedium(activity)
        if ( dashBoardFragment.siteListRow!!.get(position).isselect) {
            itemView!!.chkSelect.isChecked=true
        }else
            itemView!!.chkSelect.isChecked=false

        itemView!!.chkSelect.setOnCheckedChangeListener { compoundButton, b ->
            if (b){
                dashBoardFragment.siteListRow!!.get(position).isselect=true
            }else
                dashBoardFragment.siteListRow!!.get(position).isselect=false
        }

    }

    override fun getItemCount(): Int {
       return siteList.size
    }
    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }
}