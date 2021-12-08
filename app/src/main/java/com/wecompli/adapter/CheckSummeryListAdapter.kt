package com.wecompli.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wecompli.databinding.ItemChecksSummeryLayoutBinding

import com.wecompli.model.RoleListResponseModel
import com.wecompli.model.StartCheckResponseModel
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.AddUserFragment
import com.wecompli.screens.fragment.StartCheckFragment
import com.wecompli.utils.customdialog.CustomRoleSelectionDialogAddUser
import com.wecompli.utils.customfont.CustomTypeface

class CheckSummeryListAdapter(
    val activity: MainActivity,
    val checkslist: ArrayList<StartCheckResponseModel.Row>,
    val startCheckFragment: StartCheckFragment, )
    : RecyclerView.Adapter<CheckSummeryListAdapter.ViewHolder>(){ var itemView:ItemChecksSummeryLayoutBinding?=null
    class ViewHolder(val itemSite: ItemChecksSummeryLayoutBinding):RecyclerView.ViewHolder(itemSite.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemChecksSummeryLayoutBinding.inflate(inflater)
        itemView=binding
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        itemView!!.tvTitle.typeface=CustomTypeface.getRajdhaniSemiBold(activity)
        itemView!!.checkNote.typeface=CustomTypeface.getRajdhaniSemiBold(activity)
        itemView!!.checkNoteText.typeface=CustomTypeface.getRajdhaniMedium(activity)
        CustomTypeface.getRajdhaniMedium(activity)?.let { itemView!!.customIndicator.customTickTextsTypeface(it) }
        val checked_percentage=checkslist.get(position).progress_percentage
        itemView!!.customIndicator.setProgress(checked_percentage.toFloat())
        itemView!!.customIndicator.setIndicatorTextFormat("\${PROGRESS} %")
        // if (checked_percentage<=0) {
        //  summeryItemView.seekBarWithProgress.setEnabled(false);
        itemView!!.customIndicator.setUserSeekAble(false)

        if (checked_percentage > 0 && checked_percentage <= 30) {
            itemView!!.seekRed.setVisibility(View.VISIBLE)
            itemView!!.seekRed.setProgress(checked_percentage)
            itemView!!.seekRed.setOnTouchListener(View.OnTouchListener { v, event -> true })
            // summeryItemView.seek_red.setEnabled(false);
        } else if (checked_percentage > 30 && checked_percentage <= 60) {
            itemView!!.seekYellow.setVisibility(View.VISIBLE)
            itemView!!.seekYellow.setProgress(checked_percentage)
            itemView!!.seekYellow.setOnTouchListener(View.OnTouchListener { v, event -> true })
            //  summeryItemView.seek_yellow.setEnabled(false);
        } else if (checked_percentage > 60 && checked_percentage <= 100) {
            itemView!!.seekGreen.setVisibility(View.VISIBLE)
            itemView!!.seekGreen.setProgress(checked_percentage)
            itemView!!.seekGreen.setOnTouchListener(View.OnTouchListener { v, event -> true })
            // summeryItemView.seek_green.setEnabled(false);
        }

       /* if ( adduserfragment.roleList!!.get(position).isselect) {
            itemView!!.chkSelect.isChecked=true
        }
        itemView!!.chkSelect.setOnCheckedChangeListener { compoundButton, b ->
            if (b){
                adduserfragment.siteListRow!!.get(position).isselect=true
            }else
                adduserfragment.siteListRow!!.get(position).isselect=false
        }*/
        itemView!!.tvTitle.text=checkslist.get(position).category_name
        itemView!!.checkNoteText.text=checkslist.get(position).category_note
    }

    override fun getItemCount(): Int {
       return checkslist.size
    }
}