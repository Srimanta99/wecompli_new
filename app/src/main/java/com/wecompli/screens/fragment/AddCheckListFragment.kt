package com.wecompli.screens.fragment

import ApiInterface
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.gsscanner.utils.AppSheardPreference
import com.gsscanner.utils.PreferenceConstant
import com.sculptee.utils.customprogress.CustomProgressDialog
import com.wecompli.R

import com.wecompli.databinding.FragmentAddChecklistBinding
import com.wecompli.handler.AddCheckHandler
import com.wecompli.model.AddCheckListResponseModel
import com.wecompli.model.SiteListResponseModel
import com.wecompli.network.Retrofit
import com.wecompli.screens.MainActivity
import com.wecompli.utils.alert.CustomAlert
import com.wecompli.utils.customdialog.*
import com.wecompli.viewmodel.AddCheckListViewModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AddCheckFragment : Fragment(), AddCheckHandler {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var addcheckView:FragmentAddChecklistBinding?=null
    var viewModel:AddCheckListViewModel?=null
    var siteListRow:ArrayList<SiteListResponseModel.SiteDetails>?=null
    var siteStatus="1"
    var siteids=""
    var selectedweekdays:ArrayList<String>?=ArrayList()
    var seletedmonthdDay:ArrayList<String>?=ArrayList()
    var selectedquaterlyDay:ArrayList<String>?= ArrayList()
    var selectedhalfYearlyyDay:ArrayList<String>?=ArrayList()
    var selectedyearlyDay:ArrayList<String>?=ArrayList()

    var checkListSessionId=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         addcheckView=DataBindingUtil.inflate(inflater,R.layout.fragment_add_checklist,container,false)
         viewModel=ViewModelProviders.of(this).get(AddCheckListViewModel::class.java)
         addcheckView!!.addCheck=viewModel
         viewModel!!.addCheckHandler=this
         callApiForSiteList()
         return  addcheckView!!.root
         //  return inflater.inflate(R.layout.fragment_add_check, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddCheckFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    override fun onResume() {
        super.onResume()
        (activity as MainActivity).activityMainBinding!!.mainHeader.visibility=View.VISIBLE
        (activity as MainActivity).activityMainBinding!!.tvHeaderText.setText("ADD CHECKLIST")
    }
    private fun callApiForSiteList() {
        var loginUserData= AppSheardPreference(activity as MainActivity).getUser(PreferenceConstant.userData)
        val  customProgress: CustomProgressDialog = CustomProgressDialog().getInstance()
        customProgress.showProgress(activity as MainActivity, "Please Wait..", false)
        val apiInterface= Retrofit.retrofitInstance?.create(ApiInterface::class.java)
        try {
            val paramObject = JSONObject()
            paramObject.put("company_id", loginUserData.company_id)
            var obj: JSONObject = paramObject
            var jsonParser: JsonParser = JsonParser()
            var gsonObject: JsonObject = jsonParser.parse(obj.toString()) as JsonObject;
            val sitelistapiCall = apiInterface.callSiteListApi("Bearer " + loginUserData.token, gsonObject)
            sitelistapiCall.enqueue(object : Callback<SiteListResponseModel> {
                override fun onResponse(call: Call<SiteListResponseModel>, response: Response<SiteListResponseModel>) {
                    customProgress.hideProgress()
                    if (response.isSuccessful) {
                        if (response.body()!!.process) {
                            siteListRow = response!!.body()!!.rows
                        }
                    }

                }

                override fun onFailure(call: Call<SiteListResponseModel>, t: Throwable) {
                    customProgress.hideProgress()
                }
            })
        }catch (e: Exception){

        }
    }

    public fun setselection(){
        addcheckView!!.flexboxlayout.removeAllViews()
        siteids="";
        for (i in 0 until siteListRow!!.size){
            if (siteListRow!!.get(i).isselect) {
                siteids=siteids+","+siteListRow!!.get(i).id.toString()
                val inflater: LayoutInflater =(activity!! as MainActivity).getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var linearLayout = inflater.inflate(R.layout.flex_selected_site_item, null)
                val LayoutParams: LinearLayout.LayoutParams =
                    LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                LayoutParams.setMargins((activity!! as MainActivity).resources.getDimension(R.dimen._3sdp)
                    .toInt(), (activity!! as MainActivity).resources.getDimension(R.dimen._3sdp)
                    .toInt(), (activity!! as MainActivity).resources.getDimension(R.dimen._3sdp)
                    .toInt(), (activity!! as MainActivity).resources.getDimension(R.dimen._3sdp)
                    .toInt())
                linearLayout.layoutParams=LayoutParams
                val tvname: TextView = linearLayout.findViewById(R.id.tvname)
                val cross: ImageView = linearLayout.findViewById(R.id.crossview)
                tvname.setText(siteListRow!!.get(i).site_name)
                cross.setOnClickListener {
                    addcheckView!!.flexboxlayout.removeView(linearLayout)
                    siteListRow!!.get(i).isselect=false
                }
                addcheckView!!.flexboxlayout!!.addView(linearLayout)
            }
        }
    }
    public fun changeActiveStatus(){
        addcheckView!!.statusValue.setText("Active")
        addcheckView!!.imgStatus.setBackgroundResource(R.drawable.active)
        siteStatus="1"
        //  addSiteView!!.llStatus.setBackgroundResource(R.drawable.asscolor_round)
        //  addSiteView!!.tvStatus1.setTextColor(activity!!.resources.getColor(R.color.textColor))

    }

    public fun changeInActiveStatus(){
        addcheckView!!.statusValue.setText("Inactive")
        addcheckView!!.imgStatus.setBackgroundResource(R.drawable.inactive)
        siteStatus="0"
    }
    override fun openSite() {
        val customstatustDialog= CustomSiteSelectionDialogAddCheckList(activity as MainActivity, siteListRow!!, this)
        customstatustDialog.show()
    }

    override fun selectType() {
      val customCheckListTypeSelectionDialog=CustomCheckListTypeSelectionDialog(activity as MainActivity,this)
        customCheckListTypeSelectionDialog.show()
    }

    override fun submitCheck() {
        if (!siteids.equals("")) {
            if (!addcheckView!!.etChecklistname.text.toString().equals("")) {
                addcheckView!!.etChecklistname.setBackgroundResource(R.drawable.rectangular_shape_rounded_corner_white)
                if (!addcheckView!!.etNote.text.toString().equals("")) {
                    addcheckView!!.etNote.setBackgroundResource(R.drawable.rectangular_shape_rounded_corner_white)
                    if (checkListSessionId > 0) {
                        // var ss: String = selectedquaterlyDay!!.joinToString(separator = ",")
                        callApiforCheckadd();
                    } else
                        Toast.makeText(activity as MainActivity, "Select Check type", Toast.LENGTH_LONG).show()

                } else
                    addcheckView!!.etNote.setBackgroundResource(R.drawable.rectangular_shape_rounded_corner_red_broder)

            } else
                addcheckView!!.etChecklistname.setBackgroundResource(R.drawable.rectangular_shape_rounded_corner_red_broder)
        } else
            Toast.makeText(activity as MainActivity, "Select site", Toast.LENGTH_LONG).show()
    }


    private fun callApiforCheckadd() {
        var loginUserData= AppSheardPreference(activity as MainActivity).getUser(PreferenceConstant.userData)
        val  customProgress: CustomProgressDialog = CustomProgressDialog().getInstance()
        customProgress.showProgress(activity as MainActivity, "Please Wait..", false)
        val apiInterface= Retrofit.retrofitInstance?.create(ApiInterface::class.java)
        try {
            val paramObject = JSONObject()
            paramObject.put("company_id", loginUserData.company_id)
            paramObject.put("category_name",addcheckView!!.etChecklistname.text.toString())
            paramObject.put("site_ids",siteids.substring(1))
            paramObject.put("category_note",addcheckView!!.etNote.text.toString())
            paramObject.put("category_purpose","checks")
            if (checkListSessionId==1) {
                paramObject.put("check_date", "")
                paramObject.put("season_id", checkListSessionId)
            }else if(checkListSessionId==2) {
                paramObject.put("season_id", selectedweekdays!!.joinToString(separator = ","))
                paramObject.put("check_date","")
            }
            else if(checkListSessionId==3) {
                paramObject.put("check_date", seletedmonthdDay!!.joinToString(separator = ","))
                paramObject.put("season_id", checkListSessionId)
            }else if(checkListSessionId==4) {
                paramObject.put("check_date", selectedquaterlyDay!!.joinToString(separator = ","))
                paramObject.put("season_id", checkListSessionId)
            }else if(checkListSessionId==5) {
                paramObject.put("check_date", selectedhalfYearlyyDay!!.joinToString(separator = ","))
                paramObject.put("season_id", checkListSessionId)
            }else if(checkListSessionId==6) {
                paramObject.put("check_date", selectedyearlyDay!!.joinToString(separator = ","))
                paramObject.put("season_id", checkListSessionId)
            }
            else if(checkListSessionId==14) {
                paramObject.put("check_date", "")
                paramObject.put("season_id", checkListSessionId)
            }
            paramObject.put("status_id",siteStatus)
            var obj: JSONObject = paramObject
            var jsonParser: JsonParser = JsonParser()
            var gsonObject: JsonObject = jsonParser.parse(obj.toString()) as JsonObject;
            val sitelistapiCall = apiInterface.callCheckCreate("Bearer " + loginUserData.token, gsonObject)
            sitelistapiCall.enqueue(object : Callback<AddCheckListResponseModel> {
                override fun onResponse(
                    call: Call<AddCheckListResponseModel>, response: Response<AddCheckListResponseModel>) {
                    customProgress.hideProgress()
                    if (response.isSuccessful) {
                        if(response.body()!!.process){
                            CustomAlert.showalert(activity as MainActivity,response!!.body()!!.message)
                        }else{
                            if (!response!!.body()!!.error.category_name.equals(""))
                                CustomAlert.showalert(activity as MainActivity,response!!.body()!!.error.category_name)
                            if (!response!!.body()!!.error.category_purpose.equals(""))
                                CustomAlert.showalert(activity as MainActivity,response!!.body()!!.error.category_purpose)
                            if (!response!!.body()!!.error.check_date.equals(""))
                                CustomAlert.showalert(activity as MainActivity,response!!.body()!!.error.check_date)
                            if (!response!!.body()!!.error.company_id.equals(""))
                                CustomAlert.showalert(activity as MainActivity,response!!.body()!!.error.company_id)
                            if (!response!!.body()!!.error.season_id.equals(""))
                                CustomAlert.showalert(activity as MainActivity,response!!.body()!!.error.season_id)
                            if (!response!!.body()!!.error.site_ids.equals(""))
                                CustomAlert.showalert(activity as MainActivity,response!!.body()!!.error.site_ids)
                            if (!response!!.body()!!.error.status_id.equals(""))
                                CustomAlert.showalert(activity as MainActivity,response!!.body()!!.error.status_id)

                        }
                    }

                }

                override fun onFailure(call: Call<AddCheckListResponseModel>, t: Throwable) {
                    customProgress.hideProgress()
                }
            })
        }catch (e: Exception){

        }
    }

    override fun selectStatus() {
        val customStatusSelectionDialog= CustomStatusSelectionAddChecks(activity as MainActivity,this)
        customStatusSelectionDialog.show()
    }
}