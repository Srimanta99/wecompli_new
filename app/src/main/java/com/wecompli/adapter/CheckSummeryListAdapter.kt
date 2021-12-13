package com.wecompli.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.warkiz.widget.IndicatorSeekBar
import com.wecompli.R
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
    : RecyclerView.Adapter<CheckSummeryListAdapter.ViewHolder>(){
     // var itemView:ItemChecksSummeryLayoutBinding?=null
    class ViewHolder(val itemSite: View):RecyclerView.ViewHolder(itemSite){
        val tvTitle=itemView.findViewById<TextView>(R.id.tv_title)
        val checkNote=itemView.findViewById<TextView>(R.id.check_note)
         val checkNoteText=itemView.findViewById<TextView>(R.id.check_note_text)
         val customIndicator=itemSite.findViewById<IndicatorSeekBar>(R.id.custom_indicator)
         val seekRed=itemSite.findViewById<SeekBar>(R.id.seek_red)
         val seekYellow=itemSite.findViewById<SeekBar>(R.id.seek_yellow)
         val seekGreen=itemSite.findViewById<SeekBar>(R.id.seek_green)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       // val inflater = LayoutInflater.from(parent.context)
       // val binding = ItemChecksSummeryLayoutBinding.inflate(inflater)
       // itemView=binding
        val view=LayoutInflater.from(activity).inflate(R.layout.item_checks_summery_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder!!.tvTitle.typeface=CustomTypeface.getRajdhaniSemiBold(activity)
        holder!!.checkNote.typeface=CustomTypeface.getRajdhaniSemiBold(activity)
        holder!!.checkNoteText.typeface=CustomTypeface.getRajdhaniMedium(activity)
        CustomTypeface.getRajdhaniMedium(activity)?.let { holder!!.customIndicator.customTickTextsTypeface(it) }
        val checked_percentage=checkslist.get(position).progress_percentage
        holder!!.customIndicator.setProgress(checked_percentage.toFloat())
        holder!!.customIndicator.setIndicatorTextFormat("\${PROGRESS} %")
        // if (checked_percentage<=0) {
        //  summeryItemView.seekBarWithProgress.setEnabled(false);
        holder!!.customIndicator.setUserSeekAble(false)

        if (checked_percentage > 0 && checked_percentage <= 30) {
            holder!!.seekRed.setVisibility(View.VISIBLE)
            holder!!.seekRed.setProgress(checked_percentage)
            holder!!.seekRed.setOnTouchListener(View.OnTouchListener { v, event -> true })
            // summeryItemView.seek_red.setEnabled(false);
        } else if (checked_percentage > 30 && checked_percentage <= 60) {
            holder!!.seekYellow.setVisibility(View.VISIBLE)
            holder!!.seekYellow.setProgress(checked_percentage)
            holder!!.seekYellow.setOnTouchListener(View.OnTouchListener { v, event -> true })
            //  summeryItemView.seek_yellow.setEnabled(false);
        } else if (checked_percentage > 60 && checked_percentage <= 100) {
            holder!!.seekGreen.setVisibility(View.VISIBLE)
            holder!!.seekGreen.setProgress(checked_percentage)
            holder!!.seekGreen.setOnTouchListener(View.OnTouchListener { v, event -> true })
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
        holder!!.tvTitle.text=checkslist.get(position).category_name
        holder!!.checkNoteText.text=checkslist.get(position).category_note
    }

    override fun getItemCount(): Int {
       return checkslist.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }
}