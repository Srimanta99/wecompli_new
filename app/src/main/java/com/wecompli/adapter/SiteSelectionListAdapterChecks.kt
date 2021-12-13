package com.wecompli.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wecompli.databinding.ItemSiteSelectBinding
import com.wecompli.model.SiteListResponseModel
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.ChecksFragment
import com.wecompli.screens.fragment.StartCheckFragment
import com.wecompli.utils.customdialog.CustomSiteSelectionDialogChecks
import com.wecompli.utils.customdialog.CustomSiteSelectionDialogStartCheck
import com.wecompli.utils.customfont.CustomTypeface


class SiteSelectionListAdapterChecks(
    val activity: MainActivity,
    val siteList: ArrayList<SiteListResponseModel.SiteDetails>,
    val checkfragment: ChecksFragment,
    val customSiteSelectionDialogCheck: CustomSiteSelectionDialogChecks
)
    : RecyclerView.Adapter<SiteSelectionListAdapterChecks.ViewHolder>(){
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
        if ( checkfragment.siteListRow!!.get(position).isselect) {
            itemView!!.chkSelect.isChecked=true
        }else
            itemView!!.chkSelect.isChecked=false

        itemView!!.chkSelect.setOnCheckedChangeListener { compoundButton, b ->
            if (b){
                checkfragment.siteListRow!!.get(position).isselect=true
                checkfragment!!.selectedSideId=checkfragment.siteListRow!!.get(position).id.toString()
                checkfragment!!.selectedSidname=checkfragment.siteListRow!!.get(position).site_name.toString()

                customSiteSelectionDialogCheck.setselection(position)
             //   notifyDataSetChanged()
            }else {
                checkfragment.siteListRow!!.get(position).isselect = false
               // startcheckfragment!!.setselection(position)
               // notifyDataSetChanged()
            }
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

    fun selectSite(position: Int){
        for (i in 0 until siteList!!.size) {
            if (i!=position) {
                siteList.get(i).isselect=false
            }
        }
       // notifyDataSetChanged()

    }
}