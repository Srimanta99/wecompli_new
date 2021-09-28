package com.wecompli.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wecompli.databinding.ItemChecklistBinding
import com.wecompli.databinding.ItemRoleListBinding
import com.wecompli.model.CheckListResponseModel
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.ChecksListFragment

class CheckListAdapter(val activity:MainActivity,val checkList:ArrayList<CheckListResponseModel.RowDetails>,val checksListFragment: ChecksListFragment):RecyclerView.Adapter<CheckListAdapter.ViewHolder>() {
  var itemView:ItemChecklistBinding?=null
    class ViewHolder(val itemChecklistBinding: ItemChecklistBinding):RecyclerView.ViewHolder(itemChecklistBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemChecklistBinding.inflate(inflater)
        itemView=binding
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       itemView!!.tvChecklistname.setText(checkList!!.get(position).category_name)
        itemView!!.imgDownarrow.setOnClickListener {
            if(itemView!!.llCheckDetails.visibility==View.GONE)
                itemView!!.llCheckDetails.visibility=View.VISIBLE
            else
                itemView!!.llCheckDetails.visibility=View.GONE
        }
    }

    override fun getItemCount(): Int {
       return checkList.size
    }
}