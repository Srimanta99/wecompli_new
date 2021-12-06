package com.wecompli.adapter

import android.content.Context
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
import com.wecompli.model.RoleListResponseModel
import com.wecompli.model.SiteListResponseModel
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.RolesListFragment
import com.wecompli.screens.fragment.SiteListFragment
import com.wecompli.utils.customfont.CustomTypeface
import kotlinx.android.synthetic.main.item_role_list.view.*

class RoleListAdapter(val activity: MainActivity, val rolelist:ArrayList<RoleListResponseModel.RoleDetails>, val siteListFragment: RolesListFragment)
    : RecyclerView.Adapter<RoleListAdapter.ViewHolder>(){
     //  var  itemView:ItemRoleListBinding?=null
    class ViewHolder(val itemSite: View):RecyclerView.ViewHolder(itemSite){
        val tvSitename=itemSite.findViewById<TextView>(R.id.tv_rolename);
         val rlExpand:RelativeLayout=itemSite.findViewById(R.id.rl_expand);
         val llRoles:LinearLayout=itemSite.findViewById(R.id.ll_roles)
         val flexboxlayoutRole:FlexboxLayout=itemSite.findViewById(R.id.flexboxlayout_role)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       /* val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRoleListBinding.inflate(inflater)
        itemView=binding*/
        val view=LayoutInflater.from(activity).inflate(R.layout.item_role_list,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(itemView: ViewHolder, position: Int) {

          itemView!!.tvSitename.setText(rolelist.get(position).role_name)
           itemView!!.tvSitename.typeface=CustomTypeface.getRajdhaniBold(activity);
           itemView!!.rlExpand.setOnClickListener {
            if(itemView!!.llRoles.visibility==View.GONE)
                itemView!!.llRoles.visibility=View.VISIBLE
              else
                itemView!!.llRoles.visibility=View.GONE
             }
         for (i in 0 until rolelist.get(position).role_list.size){
             val inflater: LayoutInflater =(activity!! as MainActivity).getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
             var linearLayout = inflater.inflate(R.layout.flex_item_role_list, null)
             val LayoutParams: LinearLayout.LayoutParams =
                 LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
             LayoutParams.setMargins((activity!! as MainActivity).resources.getDimension(R.dimen._3sdp)
                 .toInt(), (activity!! as MainActivity).resources.getDimension(R.dimen._3sdp)
                 .toInt(), (activity!! as MainActivity).resources.getDimension(R.dimen._3sdp)
                 .toInt(), (activity!! as MainActivity).resources.getDimension(R.dimen._3sdp)
                 .toInt())
             linearLayout.layoutParams=LayoutParams
             val tvname: TextView = linearLayout.findViewById(R.id.tvname)
             val img: ImageView = linearLayout.findViewById(R.id.crossview)
             tvname.setText(rolelist.get(position).role_list.get(i).function_display_name)
             Glide.with(activity)
                 .load(rolelist.get(position).role_list.get(i).function_icon_1st)
                 .into(img)
             itemView!!.flexboxlayoutRole!!.addView(linearLayout)
         }

       // itemView!!.llRoles.visibility=View.GONE
    }

    override fun getItemCount(): Int {
       return rolelist.size
    }
}