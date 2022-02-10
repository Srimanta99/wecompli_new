package com.wecompli.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wecompli.databinding.ItemSiteSelectBinding
import com.wecompli.model.SiteListResponseModel
import com.wecompli.model.UserListResponseModel
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.FaultListFragment
import com.wecompli.screens.fragment.UserEditFragment
import com.wecompli.utils.customfont.CustomTypeface

class SiteSelectionListAdapterFaultList(
    val activity: MainActivity,
    val siteList: ArrayList<SiteListResponseModel.SiteDetails>,
    val faultListFragment: FaultListFragment,

)
    : RecyclerView.Adapter<SiteSelectionListAdapterFaultList.ViewHolder>(){
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
      /*  for(p in 0 until siteListRow.size){
            if(siteList.get(position).site_name.equals(selectedSirList.get(p).site_name))
                faultListFragment.siteListRow!!.get(position).isselect=true
        }
*/
        if ( faultListFragment.siteListRow!!.get(position).isselect) {
            itemView!!.chkSelect.isChecked=true
        }
        else
            itemView!!.chkSelect.isChecked=false
        itemView!!.chkSelect.setOnCheckedChangeListener { compoundButton, b ->
            if (b){
                faultListFragment.siteListRow!!.get(position).isselect=true
            }else
                faultListFragment.siteListRow!!.get(position).isselect=false
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