package com.wecompli.adapter

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.flexbox.FlexboxLayout
import com.google.gson.Gson
import com.gsscanner.utils.AppSheardPreference
import com.gsscanner.utils.PreferenceConstant
import com.sculptee.utils.customprogress.CustomProgressDialog
import com.wecompli.R
import com.wecompli.databinding.ItemRoleListBinding
import com.wecompli.model.*
import com.wecompli.network.NetworkUtility
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.*
import com.wecompli.utils.alert.CustomAlert
import com.wecompli.utils.customfont.CustomTypeface
import kotlinx.android.synthetic.main.item_role_list.view.*
import okhttp3.*
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class ChecksDetailsAdapter(val activity: MainActivity, val checklistDetailsresponselist:ArrayList<CheckListDetailsResponse.Row>, val checkdetailsfragment: CheckDetailsFragment)
    : RecyclerView.Adapter<ChecksDetailsAdapter.ViewHolder>(){
     //  var  itemView:ItemRoleListBinding?=null
    class ViewHolder(val itemSite: View):RecyclerView.ViewHolder(itemSite){
        val checksname=itemSite.findViewById<TextView>(R.id.tv_check_name);
         val rlExpand:RelativeLayout=itemSite.findViewById(R.id.rlExpand);
      //   val llRoles:LinearLayout=itemSite.findViewById(R.id.ll_roles)
         val tvpass:TextView=itemSite.findViewById(R.id.tvpass)
         val tvfail:TextView=itemSite.findViewById(R.id.tv_fail)
         val editpass:ImageView=itemSite.findViewById(R.id.img_editpass)
       //  val tvminorfail:TextView=itemSite.findViewById(R.id.tv_minorfail)
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
       // itemView!!.tvminorfail.typeface=CustomTypeface.getRajdhaniBold(activity);
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

      /*  itemView.tvminorfail.setOnClickListener {
            val transaction = activity.supportFragmentManager.beginTransaction()
            var checkSubmitFragment= CheckSubmitFragment()
            val bundle= Bundle()
           // bundle.putString("category_Id",checkslist.get(position).id.toString())
            bundle.putString("selecteddate",checkdetailsfragment.date)
            bundle.putString("siteid",checkdetailsfragment.siteid)
            bundle.putString("checkname",checklistDetailsresponselist.get(position).check_name)
            bundle.putString("check_type","3")
            checkSubmitFragment.arguments=bundle
            transaction.add(R.id.content_frame, checkSubmitFragment)
            transaction.addToBackStack("")
            transaction.commit()
        }*/

        itemView.editpass.setOnClickListener {
            val transaction = activity.supportFragmentManager.beginTransaction()
            var checkSubmitFragment= CheckSubmitFragment()
            val bundle= Bundle()
            // bundle.putString("category_Id",checkslist.get(position).id.toString())
            bundle.putString("selecteddate",checkdetailsfragment.date)
            bundle.putString("siteid",checkdetailsfragment.siteid)
            bundle.putString("checkname",checklistDetailsresponselist.get(position).check_name)
            bundle.putString("check_type","1")
            checkSubmitFragment.arguments=bundle
            transaction.add(R.id.content_frame, checkSubmitFragment)
            transaction.addToBackStack("")
            transaction.commit()
        }
        itemView.tvfail.setOnClickListener {
            val transaction = activity.supportFragmentManager.beginTransaction()
            var checkSubmitFragment= FailSubmitFragment()
            val bundle= Bundle()
            // bundle.putString("category_Id",checkslist.get(position).id.toString())
            bundle.putString("selecteddate",checkdetailsfragment.date)
            bundle.putString("siteid",checkdetailsfragment.siteid)
            bundle.putString("checkname",checklistDetailsresponselist.get(position).check_name)
            bundle.putString("check_type","2")
            checkSubmitFragment.arguments=bundle
            transaction.add(R.id.content_frame, checkSubmitFragment)
            transaction.addToBackStack("")
            transaction.commit()
        }

        itemView.tvpass.setOnClickListener {
            checkSubmit()
        }


       // itemView!!.llRoles.visibility=View.GONE
    }

    override fun getItemCount(): Int {
       return checklistDetailsresponselist.size
    }
    fun checkSubmit() {

        var datetime = ""
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
            datetime = current.format(formatter)

        } else {
            var date = Date()
            val formatter = SimpleDateFormat("dd/MM/yyyy HH:mma  ")
            datetime = formatter.format(date)
        }
        var loginUserData =
            AppSheardPreference(activity as MainActivity).getUser(PreferenceConstant.userData)
        val customProgress: CustomProgressDialog = CustomProgressDialog().getInstance()
        customProgress.showProgress(activity as MainActivity, "Please Wait..", false)
        try {
            val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
            builder.addFormDataPart("process_remark", "")
            builder.addFormDataPart("company_id", loginUserData.company_id)
            builder.addFormDataPart("site_id", "siteid")
            builder.addFormDataPart("check_id", "1")
            builder.addFormDataPart("season_id", "1")
            builder.addFormDataPart("check_type_id", "checktype")
            builder.addFormDataPart("check_process_type", "checks")
            builder.addFormDataPart("check_type_values_id", "1")
            builder.addFormDataPart("process_status", "y")
            builder.addFormDataPart("checks_process_log_entry_date", datetime)

            builder.addFormDataPart("isTemperature", "0")
            builder.addFormDataPart("temperature", "0")


            // builder.addFormDataPart("signaturename",fragmentCheckSubmitBinding!!.etName.text.toString())
            // builder.addFormDataPart("signaturefiles", "signimage", okhttp3.RequestBody.create(MediaType.parse("image/jpeg"),signimgfile))

            /* if (imagearraylist.size > 0) {
                 for (i in imagearraylist.indices) {
                     builder.addFormDataPart(
                         "processfiles[]",
                         imagearraylist.get(i).name,
                         okhttp3.RequestBody.create(
                             MediaType.parse("image/jpeg"), imagearraylist.get(i)
                         )
                     )
                 }
             }*/

            val requestBody = builder.build()
            var request: Request? = null
            request = Request.Builder()
                .addHeader("Authorization", "Bearer " + loginUserData.token)
                //.addHeader("site_id",AppSheardPreference(activity as MainActivity).getvalue_in_preference(PreferenceConstent.site_id))
                .addHeader("Content-Type", "application/json")
                .url(NetworkUtility.BASE_URL + NetworkUtility.LOGCREATE)
                .post(requestBody)
                .build()

            val client = okhttp3.OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .build()

            val call = client.newCall(request)
            call.enqueue(object : okhttp3.Callback {
                override fun onResponse(call: Call, response: Response) {
                    customProgress.hideProgress()
                    try {
                        if (response.isSuccessful) {
                            val gson = Gson()
                            var resStr: String = response.body()!!.string()
                            //var response_obj = JSONObject(resStr)
                            val processLogCreateResponseModel: ProcessLogCreateResponseModel =
                                gson.fromJson(
                                    resStr,
                                    ProcessLogCreateResponseModel::class.java
                                )
                            //val response_obj = JSONObject(response.body()!!.string())
                            if (processLogCreateResponseModel.process) {
                                // Toast.makeText(activity as MainActivity, "sucess", Toast.LENGTH_LONG).show()
                                // val check_process_log_id:String=response_obj.getInt("check_process_log_id").toString()
                                //callApiforfaultcreate(check_process_log_id);
                                (activity as MainActivity).runOnUiThread {
                                    CustomAlert.showalert(
                                        activity as MainActivity,
                                        processLogCreateResponseModel.message
                                    )
                                }

                            } else {
                                (activity as MainActivity).runOnUiThread {
                                    CustomAlert.showalert(
                                        activity as MainActivity,
                                        processLogCreateResponseModel.message
                                    )
                                }
                                //Toast.makeText(activity as MainActivity, processLogCreateResponseModel.message, Toast.LENGTH_LONG).show()

                            }
                        }
                    } catch (e: java.lang.Exception) {
                        customProgress.hideProgress()
                        e.printStackTrace()
                        (activity as MainActivity).runOnUiThread {
                            Toast.makeText(
                                activity as MainActivity,
                                "Try later. Something Wrong.",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    customProgress.hideProgress()
                    (activity as MainActivity).runOnUiThread {
                        Toast.makeText(activity as MainActivity, "Faliure", Toast.LENGTH_LONG)
                            .show()
                    }

                }
            })

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}