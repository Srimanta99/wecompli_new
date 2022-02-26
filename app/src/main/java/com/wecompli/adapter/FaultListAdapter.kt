package com.wecompli.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.wecompli.R
import com.wecompli.model.FaultListResponseModel
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.FaultDetailsFragment
import com.wecompli.screens.fragment.FaultListFragment
import com.wecompli.utils.customfont.CustomTypeface


class FaultListAdapter(
    val activity: MainActivity,
    val faultList: ArrayList<FaultListResponseModel.Row>,
    val faultListFragment: FaultListFragment
)
    : RecyclerView.Adapter<FaultListAdapter.ViewHolder>(){
     //  var  itemView:ItemRoleListBinding?=null
    class ViewHolder(val itemSite: View):RecyclerView.ViewHolder(itemSite){
         val llfaultmain=itemSite.findViewById<LinearLayout>(R.id.llfaultmain)
         val llfaultdetails=itemSite.findViewById<LinearLayout>(R.id.ll_details)
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
        val view=LayoutInflater.from(activity).inflate(R.layout.item_fault_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(itemView: ViewHolder, position: Int) {
        itemView.tv_faultname.typeface=CustomTypeface.getRajdhaniBold(activity)
        itemView.tv_status.typeface=CustomTypeface.getRajdhaniBold(activity)
        itemView.tv_statusval.typeface=CustomTypeface.getRajdhaniMedium(activity)
        itemView.tv_date.typeface=CustomTypeface.getRajdhaniMedium(activity)
        itemView.tv_remarks.typeface=CustomTypeface.getRajdhaniBold(activity)
        itemView.tv_remarkvalue.typeface=CustomTypeface.getRajdhaniSemiBold(activity)

        itemView.tv_faultname.setText(faultList.get(0).fault_list.get(position).check_name)
        itemView.tv_statusval.setText(faultList.get(0).fault_list.get(position).fault_description)
        itemView.tv_statusval.setText("Date :"+faultList.get(0).fault_list.get(position).fault_entry_date)

        itemView.llfaultdetails.setOnClickListener {
            //activity.openFragment(FaultDetailsFragment())
            var detailsFragment= FaultDetailsFragment()
         //   val fm: FragmentManager = (activity as MainActivity).supportFragmentManager
         //   val ft: FragmentTransaction = fm.beginTransaction()
            val transaction = activity.supportFragmentManager.beginTransaction()
            val bundle=Bundle();
           // bundle.putString("faultid","22")
            bundle.putString("faultid", faultList.get(0)!!.fault_list.get(position).id.toString())
            bundle.putString("siteid", faultList.get(position)!!.site_id.toString())
            detailsFragment!!.arguments=bundle
            transaction.add(R.id.content_frame, detailsFragment)
            transaction.addToBackStack("")
            transaction.commit()

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
       return faultList!!.get(0).fault_list.size
    }
}