package com.wecompli.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wecompli.databinding.ItemSiteSelectBinding
import com.wecompli.model.SiteListResponseModel
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.DashBoardFragment

class SiteSelectionListAdapter(
    val activity: MainActivity,
    val siteList: ArrayList<SiteListResponseModel.SiteDetails>,
    val dashBoardFragment: DashBoardFragment
)
    : RecyclerView.Adapter<SiteSelectionListAdapter.ViewHolder>(){
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
        if ( dashBoardFragment.siteListRow!!.get(position).isselect) {
            itemView!!.chkSelect.isChecked=true
        }
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
}