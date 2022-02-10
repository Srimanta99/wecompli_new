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
import com.wecompli.model.FaultListResponseModel
import com.wecompli.model.RoleListResponseModel
import com.wecompli.model.SiteListResponseModel
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.FaultDetailsFragment
import com.wecompli.screens.fragment.FaultListFragment
import com.wecompli.screens.fragment.RolesListFragment
import com.wecompli.screens.fragment.SiteListFragment
import com.wecompli.utils.customfont.CustomTypeface
import kotlinx.android.synthetic.main.item_role_list.view.*

class FaultListAdapter(val activity: MainActivity, val faultList:ArrayList<FaultListResponseModel.Row>, val siteListFragment: FaultListFragment)
    : RecyclerView.Adapter<FaultListAdapter.ViewHolder>(){
     //  var  itemView:ItemRoleListBinding?=null
    class ViewHolder(val itemSite: View):RecyclerView.ViewHolder(itemSite){
         val llfaultmain=itemSite.findViewById<LinearLayout>(R.id.llfaultmain)
        val tv_faultname=itemSite.findViewById<TextView>(R.id.tv_faultname);
         val tv_status:TextView=itemSite.findViewById(R.id.tv_status);
         val tv_statusval:TextView=itemSite.findViewById(R.id.tv_statusval)
         val tv_date:TextView=itemSite.findViewById(R.id.tv_date)
         val img_downarrow=itemSite.findViewById<ImageView>(R.id.img_downarrow);
         val imagestatus:ImageView=itemSite.findViewById(R.id.imagestatus);
         val ll_details:LinearLayout=itemSite.findViewById(R.id.ll_details)
         val tv_remarks=itemSite.findViewById<TextView>(R.id.tv_remarks);
         val img_fault1:ImageView=itemSite.findViewById(R.id.img_fault1);
         val img_fault2:ImageView=itemSite.findViewById(R.id.img_fault2);
         val img_fault3:ImageView=itemSite.findViewById(R.id.img_fault3);
         val img_fault4:ImageView=itemSite.findViewById(R.id.img_fault4);
         val rl_delete:RelativeLayout=itemSite.findViewById(R.id.rl_delete);
         val rl_edit:RelativeLayout=itemSite.findViewById(R.id.rl_edit);
         val tv_remarkvalue:TextView=itemSite.findViewById(R.id.tv_remarkvalue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       /* val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRoleListBinding.inflate(inflater)
        itemView=binding*/
        val view=LayoutInflater.from(activity).inflate(R.layout.item_fault_list,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(itemView: ViewHolder, position: Int) {
        itemView.tv_faultname.typeface=CustomTypeface.getRajdhaniBold(activity)
        itemView.tv_status.typeface=CustomTypeface.getRajdhaniBold(activity)
        itemView.tv_statusval.typeface=CustomTypeface.getRajdhaniMedium(activity)
        itemView.tv_date.typeface=CustomTypeface.getRajdhaniMedium(activity)
        itemView.tv_remarks.typeface=CustomTypeface.getRajdhaniBold(activity)
        itemView.tv_remarkvalue.typeface=CustomTypeface.getRajdhaniSemiBold(activity)
        itemView.llfaultmain.setOnClickListener {
            activity.openFragment(FaultDetailsFragment())
        }
        itemView.img_downarrow.setOnClickListener {
            if (itemView.ll_details.visibility==View.GONE){
                itemView.ll_details.visibility= View.VISIBLE
            }else
                itemView.ll_details.visibility= View.GONE
        }



       // itemView!!.llRoles.visibility=View.GONE
    }

    override fun getItemCount(): Int {
       return 10
    }
}