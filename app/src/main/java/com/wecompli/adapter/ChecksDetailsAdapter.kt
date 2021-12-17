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
import com.wecompli.model.CheckListDetailsResponse
import com.wecompli.model.ChecksListModel
import com.wecompli.model.RoleListResponseModel
import com.wecompli.model.SiteListResponseModel
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.CheckDetailsFragment
import com.wecompli.screens.fragment.ChecksFragment
import com.wecompli.screens.fragment.RolesListFragment
import com.wecompli.screens.fragment.SiteListFragment
import com.wecompli.utils.customfont.CustomTypeface
import kotlinx.android.synthetic.main.item_role_list.view.*

class ChecksDetailsAdapter(val activity: MainActivity, val checklistDetailsresponselist:ArrayList<CheckListDetailsResponse.Row>, val checkdetailsfragment: CheckDetailsFragment)
    : RecyclerView.Adapter<ChecksDetailsAdapter.ViewHolder>(){
     //  var  itemView:ItemRoleListBinding?=null
    class ViewHolder(val itemSite: View):RecyclerView.ViewHolder(itemSite){
        val checksname=itemSite.findViewById<TextView>(R.id.tv_check_name);
         val rlExpand:RelativeLayout=itemSite.findViewById(R.id.rlExpand);
      //   val llRoles:LinearLayout=itemSite.findViewById(R.id.ll_roles)
         val tvpass:TextView=itemSite.findViewById(R.id.tvpass)
         val tvfail:TextView=itemSite.findViewById(R.id.tv_fail)
         val tvminorfail:TextView=itemSite.findViewById(R.id.tv_minorfail)
         val notes:TextView=itemSite.findViewById(R.id.tv_note)
         val notetext:TextView=itemSite.findViewById(R.id.tv_note_text)
         val llChecknotesnotes:LinearLayout=itemSite.findViewById(R.id.llChecknotesnotes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       /* val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRoleListBinding.inflate(inflater)
        itemView=binding*/
        val view=LayoutInflater.from(activity).inflate(R.layout.item_check_details,parent,false)
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

        itemView!!.checksname.setText(checklistDetailsresponselist.get(position).check_name)
        itemView!!.notetext.setText(checklistDetailsresponselist.get(position).check_note)

           itemView!!.rlExpand.setOnClickListener {
            if(itemView!!.llChecknotesnotes.visibility==View.GONE)
                itemView!!.llChecknotesnotes.visibility=View.VISIBLE
              else
                itemView!!.llChecknotesnotes.visibility=View.GONE
             }


       // itemView!!.llRoles.visibility=View.GONE
    }

    override fun getItemCount(): Int {
       return checklistDetailsresponselist.size
    }
}