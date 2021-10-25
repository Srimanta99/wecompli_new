package com.wecompli.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wecompli.R


import com.wecompli.model.SiteListResponseModel
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.EditSiteFragment
import com.wecompli.screens.fragment.SiteListFragment
import com.wecompli.utils.customfont.CustomTypeface

class SiteListAdapter(val activity: MainActivity,val siteList:ArrayList<SiteListResponseModel.SiteDetails>,val siteListFragment: SiteListFragment)
    : RecyclerView.Adapter<SiteListAdapter.ViewHolder>(){
     // var itemView:ItemSiteListBinding?=null
    class ViewHolder(val itemSite: View):RecyclerView.ViewHolder(itemSite){
        val tvSitename: TextView = itemView.findViewById(R.id.tv_sitename)
         val imagStatus:ImageView=itemSite.findViewById(R.id.img_active)
        val expand_colps: ImageView =itemView.findViewById(R.id.img_downarrow)
        val llDetails: LinearLayout =itemView.findViewById(R.id.ll_sitedetails)
        val tvUsercount: TextView =itemView.findViewById(R.id.tv_userscount)
        val tvuserCountVal: TextView =itemView.findViewById(R.id.tv_user_count)
        val checklist: TextView =itemView.findViewById(R.id.tv_checklist)
        val tvchecklistcount: TextView =itemView.findViewById(R.id.tv_checklistCount)
        val checks: TextView =itemView.findViewById(R.id.tv_checks)
        val checsCount: TextView =itemView.findViewById(R.id.tv_checkscount)
        val fault: TextView =itemView.findViewById(R.id.tv_fault)
        val faultcount: TextView =itemView.findViewById(R.id.tv_faultcount)
        val document: TextView =itemView.findViewById(R.id.tv_document)
         val tv_documentcount:TextView=itemSite.findViewById(R.id.tv_documentcount);
         val incidents: TextView =itemView.findViewById(R.id.tv_incidents)
         val incidentscount:TextView=itemSite.findViewById(R.id.tv_incidentscount);
         val rr_edit:RelativeLayout=itemSite.findViewById(R.id.rr_edit)
         val rl_delete:RelativeLayout=itemSite.findViewById(R.id.rl_delete)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(activity).inflate(R.layout.item_site_list,parent,false)
       // val inflater = LayoutInflater.from(parent.context)
       // val binding = ItemSiteListBinding.inflate(inflater)
      //  itemView=binding
        return ViewHolder(view)
    }

    override fun onBindViewHolder(itemView: ViewHolder, position: Int) {
        itemView.tvSitename.typeface=CustomTypeface.getRajdhaniBold(activity)
        itemView.tvUsercount.typeface=CustomTypeface.getRajdhaniSemiBold(activity)
        itemView.checklist.typeface=CustomTypeface.getRajdhaniSemiBold(activity)
        itemView.checks.typeface=CustomTypeface.getRajdhaniSemiBold(activity)
        itemView.fault.typeface=CustomTypeface.getRajdhaniSemiBold(activity)
        itemView.document.typeface=CustomTypeface.getRajdhaniSemiBold(activity)
        itemView.incidents.typeface=CustomTypeface.getRajdhaniSemiBold(activity)

        itemView.tvuserCountVal.typeface=CustomTypeface.getRajdhaniBold(activity)
        itemView.tvchecklistcount.typeface=CustomTypeface.getRajdhaniBold(activity)
        itemView.checsCount.typeface=CustomTypeface.getRajdhaniBold(activity)
        itemView.faultcount.typeface=CustomTypeface.getRajdhaniBold(activity)
        itemView.tv_documentcount.typeface=CustomTypeface.getRajdhaniBold(activity)
        itemView.incidentscount.typeface=CustomTypeface.getRajdhaniBold(activity)



         itemView!!.tvSitename.setText(siteList.get(position).site_name)
        itemView!!.tvuserCountVal.setText(siteList.get(position).users_count.toString())
        itemView!!.tvchecklistcount.setText(siteList.get(position).checkslist_count.toString())
        itemView!!.checsCount.setText(siteList.get(position).checkslistchecks_count.toString())
        itemView!!.faultcount.setText(siteList.get(position).faults.toString())
        itemView!!.tv_documentcount.setText(siteList.get(position).documents_count.toString())
        itemView!!.incidentscount.setText(siteList.get(position).incidents_count.toString())
        if (siteList.get(position)!!.status_id==1){
            itemView!!.imagStatus.setBackgroundResource(R.drawable.active)

        }else {
            itemView!!.imagStatus.setBackgroundResource(R.drawable.inactive)


        }
        itemView!!.expand_colps.setOnClickListener {
            if(itemView!!.llDetails.visibility==View.VISIBLE){
                itemView!!.llDetails.visibility=View.GONE
            }else
                itemView!!.llDetails.visibility=View.VISIBLE
        }
        itemView!!.rr_edit.setOnClickListener {
            val editSiteFragment=EditSiteFragment()
            var bundle=Bundle()
            bundle.putParcelable("siteinfo",siteList.get(position))
            editSiteFragment.arguments=bundle
            activity.openFragment(editSiteFragment)
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