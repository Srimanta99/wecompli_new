package com.wecompli.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
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
import com.wecompli.screens.fragment.CheckDetailsFragment
import com.wecompli.screens.fragment.ChecksFragment
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
         val startcheck=itemSite.findViewById<RelativeLayout>(R.id.rl_startcheck)
        // val btnstartcheck=itemSite.findViewById<Button>(R.id.btn_start)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       // val inflater = LayoutInflater.from(parent.context)
       // val binding = ItemChecksSummeryLayoutBinding.inflate(inflater)
       // itemView=binding
        val view=LayoutInflater.from(activity).inflate(R.layout.item_checks_summery_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item:StartCheckResponseModel.Row=checkslist.get(position)
        var checked_percentage: Int=0
        holder!!.tvTitle.typeface=CustomTypeface.getRajdhaniSemiBold(activity)
        holder!!.checkNote.typeface=CustomTypeface.getRajdhaniSemiBold(activity)
        holder!!.checkNoteText.typeface=CustomTypeface.getRajdhaniMedium(activity)
        CustomTypeface.getRajdhaniMedium(activity)?.let { holder!!.customIndicator.customTickTextsTypeface(it) }
         checked_percentage=item.progress_percentage
        holder!!.customIndicator.setProgress(checked_percentage.toFloat())
        holder!!.customIndicator.setIndicatorTextFormat("\${PROGRESS} %")
        // if (checked_percentage<=0) {
        //  summeryItemView.seekBarWithProgress.setEnabled(false);
        holder!!.customIndicator.setUserSeekAble(false)
        if (checked_percentage==0){
            holder!!.seekRed.setVisibility(View.GONE)
            holder!!.seekGreen.setVisibility(View.GONE)
            holder!!.seekYellow.setVisibility(View.GONE)
        }

        else if (checked_percentage > 0 && checked_percentage <= 30) {
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


        holder!!.tvTitle.text=item.category_name
        holder!!.checkNoteText.text=item.category_note


          holder.startcheck.setOnClickListener {
              val transaction = activity.supportFragmentManager.beginTransaction()
              var checksFragment= CheckDetailsFragment()
              val bundle= Bundle()
              bundle.putString("category_Id",checkslist.get(position).id.toString())
              bundle.putString("selecteddate",startCheckFragment.selecteddate)
              bundle.putString("siteid",startCheckFragment.selectedSideId)
              bundle.putString("categoryname",checkslist.get(position).category_name)
              checksFragment.arguments=bundle
              transaction.add(R.id.content_frame, checksFragment)
              transaction.addToBackStack("")
              transaction.commit()
          }
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