package com.wecompli.adapter

import android.view.LayoutInflater
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
    }

    override fun getItemCount(): Int {
       return siteList.size
    }
}