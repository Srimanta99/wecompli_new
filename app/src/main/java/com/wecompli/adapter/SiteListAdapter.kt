package com.wecompli.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wecompli.databinding.SiteListItemBinding
import com.wecompli.model.SiteListResponseModel
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.SiteListFragment

class SiteListAdapter(val activity: MainActivity,val siteList:ArrayList<SiteListResponseModel.SiteDetails>,val siteListFragment: SiteListFragment)
    : RecyclerView.Adapter<SiteListAdapter.ViewHolder>(){
       var itemView:SiteListItemBinding?=null
    class ViewHolder(val itemSite: SiteListItemBinding):RecyclerView.ViewHolder(itemSite.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SiteListItemBinding.inflate(inflater)
        itemView=binding
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         itemView!!.tvSitename.setText(siteList.get(position).site_name)
        itemView!!.tvUserCount.setText(siteList.get(position).users_count.toString())
        itemView!!.tvChecklistCount.setText(siteList.get(position).checkslist_count.toString())
        itemView!!.tvCheckscount.setText(siteList.get(position).checkslistchecks_count.toString())

        itemView!!.tvDocumentcount.setText(siteList.get(position).documents_count.toString())
        itemView!!.tvIncidentscount.setText(siteList.get(position).incidents_count.toString())
        itemView!!.tvFaultcount.setText(siteList.get(position).faults.toString())
        itemView!!.imgDownarrow.setOnClickListener {
            if(itemView!!.llSitedetails.visibility==View.VISIBLE){
                itemView!!.llSitedetails.visibility=View.GONE
            }else
                itemView!!.llSitedetails.visibility=View.VISIBLE
        }

    }

    override fun getItemCount(): Int {
       return siteList.size
    }
}