package com.wecompli.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wecompli.databinding.ItemSiteSelectBinding
import com.wecompli.model.SiteListResponseModel
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.AddUserFragment
import com.wecompli.screens.fragment.DashBoardFragment
import com.wecompli.utils.customfont.CustomTypeface

class SiteSelectionListAdapterAddUser(
    val activity: MainActivity,
    val siteList: ArrayList<SiteListResponseModel.SiteDetails>,
    val adduserfragment: AddUserFragment
)
    : RecyclerView.Adapter<SiteSelectionListAdapterAddUser.ViewHolder>(){
       var itemView:ItemSiteSelectBinding?=null
    class ViewHolder(val itemSite: ItemSiteSelectBinding):RecyclerView.ViewHolder(itemSite.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSiteSelectBinding.inflate(inflater)
        itemView=binding
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        itemView!!.tvSitename.setText(siteList.get(position).site_name)
        itemView!!.tvSitename.typeface=CustomTypeface.getRajdhaniMedium(activity)
        if ( adduserfragment.siteListRow!!.get(position).isselect) {
            itemView!!.chkSelect.isChecked=true
        }else
            itemView!!.chkSelect.isChecked=false

        itemView!!.chkSelect.setOnCheckedChangeListener { compoundButton, b ->
            if (b){
                adduserfragment.siteListRow!!.get(position).isselect=true
            }else
                adduserfragment.siteListRow!!.get(position).isselect=false
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