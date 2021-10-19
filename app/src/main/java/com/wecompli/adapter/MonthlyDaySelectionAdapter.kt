package com.wecompli.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.wecompli.R
import com.wecompli.databinding.ItemMonthDaySelectionBinding
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.AddCheckFragment
import com.wecompli.utils.customfont.CustomTypeface


class MonthlyDaySelectionAdapter(val daylist: ArrayList<String>?, val activity:MainActivity,  val addCheckFragment: AddCheckFragment): RecyclerView.Adapter<MonthlyDaySelectionAdapter.ViewHolder>() {
    var  itemView: ItemMonthDaySelectionBinding?=null
    class ViewHolder (val itemMonthDaySelectionBinding: View):RecyclerView.ViewHolder(itemMonthDaySelectionBinding){
        val tvDayval: TextView = itemView.findViewById(R.id.tv_dayval)
        val toggleDay: ToggleButton = itemView.findViewById(R.id.toggle_day)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(activity).inflate(R.layout.item_month_day_selection,parent,false)
        /*val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMonthDaySelectionBinding.inflate(inflater)
        itemView=binding*/
        return MonthlyDaySelectionAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder!!.tvDayval.setText(daylist!!.get(position))
        holder!!.tvDayval.typeface=CustomTypeface.getRajdhaniSemiBold(activity)
        holder!!.toggleDay.setOnCheckedChangeListener { compoundButton, b ->
               if (b)
                addCheckFragment.seletedmonthdDay!!.add(holder!!.tvDayval.text.toString())
            else
                   addCheckFragment.seletedmonthdDay!!.remove(holder!!.tvDayval.text.toString())
        }
    }

    override fun getItemCount(): Int {
       return daylist!!.size
    }
}