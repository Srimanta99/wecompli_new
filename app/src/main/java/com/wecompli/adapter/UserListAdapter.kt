package com.wecompli.adapter

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayout
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.sculptee.utils.customprogress.CustomProgressDialog
import com.wecompli.R
import com.wecompli.databinding.ItemUserListBinding

import com.wecompli.model.UserListResponseModel

import com.wecompli.network.Retrofit
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.UserListFragment
import com.wecompli.utils.customfont.CustomTypeface

class UserListAdapter(
    val activity: MainActivity,
    val userlist: ArrayList<UserListResponseModel.UserDetails>,
    val  userListFragment: UserListFragment
): RecyclerView.Adapter<UserListAdapter.ViewHolder>() {
    var  itemView: ItemUserListBinding?=null
    class ViewHolder(val  itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvuser: TextView = itemView.findViewById(R.id.tv_username)
        val expand_colps:RelativeLayout=itemView.findViewById(R.id.rl_expand)
        val llDetails:LinearLayout=itemView.findViewById(R.id.ll_userdetails)
        val email:TextView=itemView.findViewById(R.id.tv_email)
        val head:TextView=itemView.findViewById(R.id.tv_head)
        val info:TextView=itemView.findViewById(R.id.tv_info)
        val site:TextView=itemView.findViewById(R.id.tv_site)
        val role:TextView=itemView.findViewById(R.id.tv_role)
        val rolename:TextView=itemView.findViewById(R.id.tv_role_name)
        val status:TextView=itemView.findViewById(R.id.tv_status)
        val imgstatus:ImageView=itemView.findViewById(R.id.imgstatus)
        val sites:TextView=itemView.findViewById(R.id.tv_site)
        val flessites:FlexboxLayout=itemView.findViewById(R.id.flexboxlayout_sites)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      ///  val inflater = LayoutInflater.from(parent.context)
        //val binding = ItemUserListBinding.inflate(inflater)
       // itemView=binding
        val view=LayoutInflater.from(activity).inflate(R.layout.item_user_list,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder!!.tvuser.typeface=CustomTypeface.getRajdhaniBold(activity)
        holder!!.email.typeface=CustomTypeface.getRajdhaniMedium(activity)
        holder!!.head.typeface=CustomTypeface.getRajdhaniBold(activity)
        holder!!.info.typeface=CustomTypeface.getRajdhaniBold(activity)
        holder!!.status.typeface=CustomTypeface.getRajdhaniBold(activity)
        holder!!.role.typeface=CustomTypeface.getRajdhaniBold(activity)
        holder!!.rolename.typeface=CustomTypeface.getRajdhaniMedium(activity)
        holder!!.site.typeface=CustomTypeface.getRajdhaniMedium(activity)

        holder!!.tvuser.setText(userlist.get(position).user_first_name)
        holder!!.email.setText(userlist.get(position).user_email_ID)
        holder.rolename.setText(userlist.get(position).roles.get(0).role_name)
       /* if(userlist.get(position).status_id==1){
            holder.imgstatus.setBackgroundResource(activity.resources.getDrawable(R.drawable.active) as Int)
        }else{
            holder.imgstatus.setBackgroundResource(activity.resources.getDrawable(R.drawable.inactive) as Int)
        }*/
        holder!!.expand_colps.setOnClickListener {
            if(holder!!.llDetails.visibility==View.GONE)
                holder!!.llDetails.visibility=View.VISIBLE
            else
                holder!!.llDetails.visibility=View.GONE
        }

        for (i in 0 until userlist.get(position).sites.size){
            val inflater: LayoutInflater =(activity!! as MainActivity).getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var linearLayout = inflater.inflate(R.layout.flex_item_user_site_list, null)
            val LayoutParams: LinearLayout.LayoutParams =
                LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            LayoutParams.setMargins((activity!! as MainActivity).resources.getDimension(R.dimen._3sdp)
                .toInt(), (activity!! as MainActivity).resources.getDimension(R.dimen._3sdp)
                .toInt(), (activity!! as MainActivity).resources.getDimension(R.dimen._3sdp)
                .toInt(), (activity!! as MainActivity).resources.getDimension(R.dimen._3sdp)
                .toInt())
            linearLayout.layoutParams=LayoutParams
            val tvname: TextView = linearLayout.findViewById(R.id.tvname)
            tvname.setText(userlist.get(position).sites.get(i).site_name)
            holder!!.flessites!!.addView(linearLayout)
        }

    }



    override fun getItemCount(): Int {
        return  userlist.size;
    }
}