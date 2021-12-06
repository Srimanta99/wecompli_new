package com.wecompli.adapter

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayout
import com.wecompli.R
import com.wecompli.databinding.ItemChecklistBinding
import com.wecompli.model.CheckListResponseModel
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.AddChecksFragment
import com.wecompli.screens.fragment.ChecksFragment
import com.wecompli.screens.fragment.ChecksListFragment
import com.wecompli.utils.customfont.CustomTypeface
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class CheckListAdapter(
    val activity: MainActivity,
    val checkList: ArrayList<CheckListResponseModel.RowDetails>,
    val checksListFragment: ChecksListFragment):RecyclerView.Adapter<CheckListAdapter.ViewHolder>() {
 // var itemView:ItemChecklistBinding?=null
    class ViewHolder(val view: View):RecyclerView.ViewHolder(view){
        val tvChecklistname:TextView=view.findViewById(R.id.tv_checklistname)
        val tvCheckModule:TextView=view.findViewById(R.id.tv_check_module)
        val tvNote:TextView=view.findViewById(R.id.tv_note)
        val tvNoteText:TextView=view.findViewById(R.id.tv_note_text)
        val tvSite:TextView=view.findViewById(R.id.tv_site)
        val tvChecksType:TextView=view.findViewById(R.id.tv_checks_type)
        val tvChecks:TextView=view.findViewById(R.id.tv_checks)
        val tvView:TextView=view.findViewById(R.id.tv_view)
        val tvCheckvalue:TextView=view.findViewById(R.id.tv_checkvalue)
        val tvStatus:TextView=view.findViewById(R.id.tv_status)
        val tvStatusVal:TextView=view.findViewById(R.id.tv_status_val)
        val tvCheckscount:TextView=view.findViewById(R.id.tv_checkscount)

        val imgActive:ImageView=view.findViewById(R.id.img_active)
        val flexSite:FlexboxLayout=view.findViewById(R.id.flex_site)
        val llCheckDetails:LinearLayout=view.findViewById(R.id.ll_check_details)
         val imgDownarrow:ImageView=view.findViewById(R.id.img_downarrow)
     val llCheckView:LinearLayout=view.findViewById(R.id.llCheckView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(activity).inflate(R.layout.item_checklist,parent,false)
       /* val inflater = LayoutInflater.from(parent.context)
        val binding = ItemChecklistBinding.inflate(inflater)
        itemView=binding*/


        return ViewHolder(view)
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }
    override fun onBindViewHolder(itemView: ViewHolder, position: Int) {
        var season:String?=""
        var checkdataMonthly:String?=""
        var checkDateQuaterly:String?=""
        var checkdateHalfYearly:String?=""
        var checkdateYearly:String?=""

        itemView!!.tvChecklistname!!.typeface=CustomTypeface.getRajdhaniBold(activity)
        itemView!!.tvCheckModule.typeface=CustomTypeface.getRajdhaniBold(activity)
        itemView!!.tvNote.typeface=CustomTypeface.getRajdhaniBold(activity)
        itemView!!.tvNoteText.typeface=CustomTypeface.getRajdhaniMedium(activity)
        itemView!!.tvSite.typeface=CustomTypeface.getRajdhaniBold(activity)
        itemView!!.tvChecksType.typeface=CustomTypeface.getRajdhaniBold(activity)
        itemView!!.tvChecks.typeface=CustomTypeface.getRajdhaniBold(activity)
        itemView!!.tvView.typeface=CustomTypeface.getRajdhaniMedium(activity)
        itemView!!.tvCheckvalue.typeface=CustomTypeface.getRajdhaniMedium(activity)
        itemView!!.tvStatus.typeface=CustomTypeface.getRajdhaniBold(activity)
        itemView!!.tvStatusVal.typeface=CustomTypeface.getRajdhaniMedium(activity)


        itemView!!.tvChecklistname.setText(checkList!!.get(position).category_name)
        itemView!!.tvNoteText.setText(checkList.get(position).category_note)
        itemView!!.tvCheckscount.setText(checkList.get(position).checkslistchecks_count.toString())
        if(checkList.get(position).status_id==1){
            itemView!!.imgActive.setBackgroundResource(R.drawable.active)
            itemView!!.tvStatusVal!!.setText(R.string.active_site)
        }else{
            itemView!!.imgActive.setBackgroundResource(R.drawable.inactive)
            itemView!!.tvStatusVal!!.setText(R.string.inactive_site)
        }

       for( p in 0 until checkList!!.get(position).checklistseasonmap.size ){
           if(checkList!!.get(position).checklistseasonmap.get(p).id==1) {
               itemView!!.tvCheckModule!!.setText("DAIlY")
               itemView!!.tvChecksType!!.setText("DAILY")
               itemView!!.tvCheckvalue!!.setText("All Days")
           }
           /*else if(checkList!!.get(position).checklistseasonmap.get(p).id==7){
               itemView!!.tvCheckModule!!.setText("Weekly")
               itemView!!.tvChecksType!!.setText("Weekly")
               season=season+","+checkList!!.get(position).checklistseasonmap.get(p).season_name
             //  itemView!!.tvCheckvalue.setText(season.substring(1))
           }
           else if(checkList!!.get(position).checklistseasonmap.get(p).id==8){
               itemView!!.tvCheckModule!!.setText("Weekly")
               itemView!!.tvChecksType!!.setText("Weekly")
               season=season+","+checkList!!.get(position).checklistseasonmap.get(p).season_name
             //  itemView!!.tvCheckvalue.setText(season.substring(1))
           }
           else if(checkList!!.get(position).checklistseasonmap.get(p).id==9){
               itemView!!.tvCheckModule!!.setText("Weekly")
               itemView!!.tvChecksType!!.setText("Weekly")
               season=season+","+checkList!!.get(position).checklistseasonmap.get(p).season_name
             //  itemView!!.tvCheckvalue.setText(season.substring(1))
           }
           else if(checkList!!.get(position).checklistseasonmap.get(p).id==10){
               itemView!!.tvCheckModule!!.setText("Weekly")
               itemView!!.tvChecksType!!.setText("Weekly")
               season=season+","+checkList!!.get(position).checklistseasonmap.get(p).season_name
              // itemView!!.tvCheckvalue.setText(season.substring(1))
           }
           else if(checkList!!.get(position).checklistseasonmap.get(p).id==11){
               itemView!!.tvCheckModule!!.setText("Weekly")
               itemView!!.tvChecksType!!.setText("Weekly")
               season=season+","+checkList!!.get(position).checklistseasonmap.get(p).season_name
              // itemView!!.tvCheckvalue.setText(season.substring(1))
           }
           else if(checkList!!.get(position).checklistseasonmap.get(p).id==12){
               itemView!!.tvCheckModule!!.setText("Weekly")
               itemView!!.tvChecksType!!.setText("Weekly")
               season=season+","+checkList!!.get(position).checklistseasonmap.get(p).season_name
             //  itemView!!.tvCheckvalue.setText(season.substring(1))
           }
           else if(checkList!!.get(position).checklistseasonmap.get(p).id==13){
               itemView!!.tvCheckModule!!.setText("Weekly")
               itemView!!.tvChecksType!!.setText("Weekly")
               season=season+","+checkList!!.get(position).checklistseasonmap.get(p).season_name
              // itemView!!.tvCheckvalue.setText(season.substring(1))
           }*/
           else if(checkList!!.get(position).checklistseasonmap.get(p).id==2||checkList!!.get(position).checklistseasonmap.get(p).id==8||checkList!!.get(position).checklistseasonmap.get(p).id==9||
               checkList!!.get(position).checklistseasonmap.get(p).id==10||checkList!!.get(position).checklistseasonmap.get(p).id==11||checkList!!.get(position).checklistseasonmap.get(p).id==12||checkList!!.get(position).checklistseasonmap.get(p).id==13)
           {
               itemView!!.tvCheckModule!!.setText("Weekly")
               itemView!!.tvChecksType!!.setText("Weekly")
               season=season+","+checkList!!.get(position).checklistseasonmap.get(p).season_name
               itemView!!.tvCheckvalue.setText(season.substring(1))
               //Log.d("days name", season)

           }
           else if (checkList!!.get(position).checklistseasonmap.get(p).id==3) {
               itemView!!.tvCheckModule!!.setText("Monthly")
               itemView!!.tvChecksType!!.setText("Monthly")
               checkdataMonthly=checkdataMonthly+","+checkList!!.get(position).checklistseasonmap.get(p).check_date
               itemView!!.tvCheckvalue.setText(checkdataMonthly.substring(1))
           }
           else if (checkList!!.get(position).checklistseasonmap.get(p).id==4) {
               itemView!!.tvCheckModule!!.setText("Quaterly")
               itemView!!.tvChecksType!!.setText("Quaterly")
               checkDateQuaterly=checkDateQuaterly+","+checkList!!.get(position).checklistseasonmap.get(p).check_date
              // itemView!!.tvCheckvalue.setText(checkDateQuaterly!!.substring(1))
           }
           else if (checkList!!.get(position).checklistseasonmap.get(p).id==5) {
               itemView!!.tvCheckModule!!.setText("Half-Early")
               itemView!!.tvChecksType!!.setText("Half-Early")
               checkdateHalfYearly=checkdateHalfYearly+","+checkList!!.get(position).checklistseasonmap.get(p).check_date
            //   itemView!!.tvCheckvalue.setText(checkdateHalfYearly!!.substring(1))
           }else if (checkList!!.get(position).checklistseasonmap.get(p).id==6) {
               itemView!!.tvCheckModule!!.setText("Yearly")
               itemView!!.tvChecksType!!.setText("Yearly")
               checkdateYearly=checkdateYearly+","+checkList!!.get(position).checklistseasonmap.get(p).check_date
             //  itemView!!.tvCheckvalue.setText(checkdateYearly!!.substring(1))
           }else if (checkList!!.get(position).checklistseasonmap.get(p).id==14) {
               itemView!!.tvCheckModule!!.setText("Inter day Check")
               itemView!!.tvChecksType!!.setText("Inter day Check")
               itemView!!.tvCheckvalue!!.setText("Inter day Check")
           }

        }

        if (!checkdateHalfYearly.equals("")){
           var halfYearlydate:String?=""
            val halfYarlylist: List<String> = checkdateHalfYearly!!.substring(1).split(",").toList()
            for (h in 0 until halfYarlylist.size){
                val listdate:String=  convertmonthDay(halfYarlylist.get(h))
                halfYearlydate=halfYearlydate+", "+listdate
            }
            itemView!!.tvCheckvalue.setText(halfYearlydate!!.substring(1))

        }

      /*  if (!checkdataMonthly.equals("")){
           var monthlyDate:String?=""
            val monthlylist: List<String> = checkdataMonthly!!.substring(1).split(",").toList()
            for (h in 0 until monthlylist.size){
                val listdate:String=  convertmonthDay(monthlylist.get(h))
                monthlyDate=monthlyDate+", "+listdate
            }
            itemView!!.tvCheckvalue.setText(monthlyDate!!.substring(1))
        }
*/
        if (!checkDateQuaterly.equals("")){
            var quaterly:String?=""
            val quaterlylist: List<String> = checkDateQuaterly!!.substring(1).split(",").toList()
            for (h in 0 until quaterlylist.size){
                val listdate:String=  convertmonthDay(quaterlylist.get(h))
                quaterly=quaterly+", "+listdate
            }
            itemView!!.tvCheckvalue.setText(quaterly!!.substring(1))
        }

        if (!checkdateYearly.equals("")){
            var yearly:String?=""
            val yaerlylist: List<String> = checkdateYearly!!.substring(1).split(",").toList()
            for (h in 0 until yaerlylist.size){
                val listdate:String=  convertmonthDay(yaerlylist.get(h))
                yearly=yearly+", "+listdate
            }
            itemView!!.tvCheckvalue.setText(yearly!!.substring(1))
        }
        if (!checkdataMonthly.equals("")){
            var monthlyDate:String?=""
            val monthlylist: List<String> = checkdataMonthly!!.substring(1).split(",").toList()
            for (h in 0 until monthlylist.size){
                val listdate:String=  getDayOfMonthSuffix(monthlylist.get(h).toInt())
                monthlyDate=monthlyDate+", "+listdate
            }
            itemView!!.tvCheckvalue.setText(monthlyDate!!.substring(1))
        }


        for (i in 0 until checkList!!.get(position).checklist_site_map.size){
            val inflater: LayoutInflater =(activity!! as MainActivity).getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var linearLayout = inflater.inflate(R.layout.flex_item_checklist_site_list, null)
            val LayoutParams: LinearLayout.LayoutParams =
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            LayoutParams.setMargins(
                (activity!! as MainActivity).resources.getDimension(R.dimen._3sdp)
                    .toInt(), (activity!! as MainActivity).resources.getDimension(R.dimen._3sdp)
                    .toInt(), (activity!! as MainActivity).resources.getDimension(R.dimen._3sdp)
                    .toInt(), (activity!! as MainActivity).resources.getDimension(R.dimen._3sdp)
                    .toInt()
            )
            linearLayout.layoutParams=LayoutParams
            val tvname: TextView = linearLayout.findViewById(R.id.tvname)
            tvname.typeface=CustomTypeface.getRajdhaniMedium(activity)
            tvname.setText(checkList!!.get(position).checklist_site_map.get(i).site_name)
            itemView!!.flexSite!!.addView(linearLayout)
        }

        itemView!!.imgDownarrow.setOnClickListener {
            if(itemView!!.llCheckDetails.visibility== View.GONE)
                itemView!!.llCheckDetails!!.visibility=View.VISIBLE
            else
                itemView!!.llCheckDetails!!.visibility=View.GONE
        }

        itemView!!.llCheckView.setOnClickListener {
            activity.drawerlayout!!.closeDrawer(Gravity.LEFT)
            val transaction = activity.supportFragmentManager.beginTransaction()
            var checksFragment=ChecksFragment()
            val bundle= Bundle()
            bundle.putString("category_Id",checkList!!.get(position).id.toString())
            bundle.putString("category_purpose",itemView!!.tvCheckModule.text.toString())
            bundle.putString("category_name",checkList!!.get(position).category_name)
            checksFragment.arguments=bundle
            transaction.replace(R.id.content_frame, checksFragment)
            transaction.addToBackStack("")
            transaction.commit()
         //   activity.openFragment(ChecksFragment())
        }
    }

    private fun convertmonthDay(inputDateStr: String): String {
        var formatedDate=""
        try {
            val inputFormat: DateFormat = SimpleDateFormat("dd-MM")
            val outputFormat: DateFormat = SimpleDateFormat("dd MMM")
            val date: Date = inputFormat.parse(inputDateStr)
            formatedDate= outputFormat.format(date)
        }catch (e: Exception){
            e.printStackTrace()
        }
            return  formatedDate;
    }

    fun getDayOfMonthSuffix(n: Int): String {
        /*checkArgument(n >= 1 && n <= 31, "illegal day of month: $n")
        return if (n >= 11 && n <= 13) {
            "th"
        } else when (n % 10) {
            1 -> "st"
            2 -> "nd"
            3 -> "rd"
            else -> "th"
        }*/
        val j = n % 10
        val k  = n % 100
        if (j == 1 && k != 11) {
            return n.toString() + "st"
        }
        if (j == 2 && k != 12) {
            return n.toString() + "nd"
        }
        return if (j == 3 && k != 13) {
            n.toString() + "rd"
        } else n.toString() + "th"
    }

    override fun getItemCount(): Int {
       return checkList.size
    }
    override fun getItemViewType(position: Int): Int {
        return position
    }
}
