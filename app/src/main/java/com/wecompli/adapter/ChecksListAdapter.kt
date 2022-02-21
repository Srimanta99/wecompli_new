package com.wecompli.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.flexbox.FlexboxLayout
import com.wecompli.R
import com.wecompli.databinding.ItemRoleListBinding
import com.wecompli.model.ChecksListModel
import com.wecompli.model.RoleListResponseModel
import com.wecompli.model.SiteListResponseModel
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.CheckSubmitFragment
import com.wecompli.screens.fragment.ChecksFragment
import com.wecompli.screens.fragment.RolesListFragment
import com.wecompli.screens.fragment.SiteListFragment
import com.wecompli.utils.customfont.CustomTypeface
import kotlinx.android.synthetic.main.item_role_list.view.*

class ChecksListAdapter(val activity: MainActivity, val checklist:ArrayList<ChecksListModel.Row>, val checksFragment: ChecksFragment)
    : RecyclerView.Adapter<ChecksListAdapter.ViewHolder>(){
     //  var  itemView:ItemRoleListBinding?=null
    class ViewHolder(val itemSite: View):RecyclerView.ViewHolder(itemSite){
        val checksname=itemSite.findViewById<TextView>(R.id.tv_checksname);
         val rlExpand:RelativeLayout=itemSite.findViewById(R.id.rlExpand);
      //   val llRoles:LinearLayout=itemSite.findViewById(R.id.ll_roles)
         val tvpass:TextView=itemSite.findViewById(R.id.tvpass)
        val tvfail:TextView=itemSite.findViewById(R.id.tv_fail)
         val tvminorfail:TextView=itemSite.findViewById(R.id.tv_mainorfail)
         val notes:TextView=itemSite.findViewById(R.id.tv_note)
         val notetext:TextView=itemSite.findViewById(R.id.tv_note_text)
         val checks:LinearLayout=itemSite.findViewById(R.id.checks)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       /* val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRoleListBinding.inflate(inflater)
        itemView=binding*/
        val view=LayoutInflater.from(activity).inflate(R.layout.item_checks,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(itemView: ViewHolder, position: Int) {


        itemView!!.checksname.typeface=CustomTypeface.getRajdhaniBold(activity);
        itemView!!.tvfail.typeface=CustomTypeface.getRajdhaniBold(activity);
        itemView!!.tvpass.typeface=CustomTypeface.getRajdhaniBold(activity);
        itemView!!.tvminorfail.typeface=CustomTypeface.getRajdhaniBold(activity);
        itemView!!.checksname.typeface=CustomTypeface.getRajdhaniBold(activity);
        itemView!!.notes.typeface=CustomTypeface.getRajdhaniBold(activity);
        itemView!!.notetext.typeface=CustomTypeface.getRajdhaniMedium(activity)

        itemView!!.checksname.setText(checklist.get(position).check_name)
        itemView!!.notetext.setText(checklist.get(position).check_note)

           itemView!!.rlExpand.setOnClickListener {
            if(itemView!!.checks.visibility==View.GONE)
                itemView!!.checks.visibility=View.VISIBLE
              else
                itemView!!.checks.visibility=View.GONE
             }


       // itemView!!.llRoles.visibility=View.GONE

        itemView.tvminorfail.setOnClickListener {
            val transaction = activity.supportFragmentManager.beginTransaction()
            var checkSubmitFragment= CheckSubmitFragment()
            val bundle= Bundle()
            // bundle.putString("category_Id",checkslist.get(position).id.toString())
            bundle.putString("selecteddate",checksFragment.currentDate)
            bundle.putString("siteid",checkSubmitFragment.siteid)
            bundle.putString("checkname",checklist.get(position).check_name)
            bundle.putString("check_type",checklist.get(position).check_type_id.toString())

            checkSubmitFragment.arguments=bundle
            transaction.add(R.id.content_frame, checkSubmitFragment)
            transaction.addToBackStack("")
            transaction.commit()
        }

        itemView.tvpass.setOnClickListener {
            val transaction = activity.supportFragmentManager.beginTransaction()
            var checkSubmitFragment= CheckSubmitFragment()
            val bundle= Bundle()
            // bundle.putString("category_Id",checkslist.get(position).id.toString())
            bundle.putString("selecteddate",checksFragment.currentDate)
            bundle.putString("siteid",checkSubmitFragment.siteid)
            bundle.putString("checkname",checklist.get(position).check_name)
            bundle.putString("check_type",checklist.get(position).check_type_id.toString())

            checkSubmitFragment.arguments=bundle
            transaction.add(R.id.content_frame, checkSubmitFragment)
            transaction.addToBackStack("")
            transaction.commit()
        }
        itemView.tvfail.setOnClickListener {
            val transaction = activity.supportFragmentManager.beginTransaction()
            var checkSubmitFragment= CheckSubmitFragment()
            val bundle= Bundle()
            // bundle.putString("category_Id",checkslist.get(position).id.toString())
            bundle.putString("selecteddate",checksFragment.currentDate)
            bundle.putString("siteid",checkSubmitFragment.siteid)
            bundle.putString("checkname",checklist.get(position).check_name)
            bundle.putString("check_type",checklist.get(position).check_type_id.toString())

            checkSubmitFragment.arguments=bundle
            transaction.add(R.id.content_frame, checkSubmitFragment)
            transaction.addToBackStack("")
            transaction.commit()
        }
    }

    override fun getItemCount(): Int {
       return checklist.size
    }
}