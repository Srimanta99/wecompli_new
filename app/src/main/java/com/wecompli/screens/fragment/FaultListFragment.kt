package com.wecompli.screens.fragment

import ApiInterface
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.gsscanner.utils.AppSheardPreference
import com.gsscanner.utils.PreferenceConstant
import com.sculptee.utils.customprogress.CustomProgressDialog
import com.wecompli.R
import com.wecompli.adapter.FaultListAdapter
import com.wecompli.adapter.SiteSelectionListAdapterFaultList
import com.wecompli.databinding.FragmentFaultListBinding
import com.wecompli.handler.FaultListHandler
import com.wecompli.model.FaultListResponseModel
import com.wecompli.model.SiteListResponseModel
import com.wecompli.network.Retrofit
import com.wecompli.screens.MainActivity
import com.wecompli.utils.alert.CustomAlert
import com.wecompli.utils.customdialog.CustomSiteSelectionDialog
import com.wecompli.utils.customdialog.CustomSiteSelectionDialogFautList
import com.wecompli.viewmodel.FaultListViewModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FaultListFragment : Fragment(),FaultListHandler {

    private var param1: String? = null
    private var param2: String? = null
   var fragmentFaultListBinding:FragmentFaultListBinding?=null
    var siteListRow:ArrayList<SiteListResponseModel.SiteDetails>?=null
    var selectedSitelistId:ArrayList<String>?= ArrayList()

    var faultList:ArrayList<FaultListResponseModel.Row>?=ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentFaultListBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_fault_list,container,false)
        var viewModel:FaultListViewModel=ViewModelProviders.of(this).get(FaultListViewModel::class.java)
        fragmentFaultListBinding!!.faultlist=viewModel
        viewModel!!.faultListHandler=this
        callApiForSiteList()
        return fragmentFaultListBinding!!.root
    }

    private fun callApiforfaultList() {
        var loginUserData= AppSheardPreference(activity as MainActivity).getUser(PreferenceConstant.userData)
        val  customProgress: CustomProgressDialog = CustomProgressDialog().getInstance()
        customProgress.showProgress(activity as MainActivity, "Please Wait..", false)
        val apiInterface= Retrofit.retrofitInstance?.create(ApiInterface::class.java)
        try {
            val paramObject = JSONObject()
            paramObject.put("company_id", loginUserData.company_id)
            paramObject.put("site_id", selectedSitelistId!!.joinToString(separator = ","))
            var obj: JSONObject = paramObject
            var jsonParser: JsonParser = JsonParser()
            var gsonObject: JsonObject = jsonParser.parse(obj.toString()) as JsonObject;
            val sitelistapiCall = apiInterface.callforfaultList("Bearer " + loginUserData.token, gsonObject)
            sitelistapiCall.enqueue(object : Callback<FaultListResponseModel> {
                override fun onResponse(call: Call<FaultListResponseModel>, response: Response<FaultListResponseModel>) {
                    customProgress.hideProgress()
                    if (response.isSuccessful) {
                        if (response.body()!!.process) {
                            faultList=response!!.body()!!.rows
                            if (faultList!!.get(0).fault_list.size>0) {
                                var faultListAdapter = FaultListAdapter(activity as MainActivity, faultList!!, this@FaultListFragment)
                                fragmentFaultListBinding!!.recFaults.adapter = faultListAdapter
                                fragmentFaultListBinding!!.tvfaultcounts.text =
                                    response!!.body()!!.fault_count.toString()
                            }
                        }else
                            CustomAlert.showalert((activity as MainActivity),"No fault found")
                    }

                }

                override fun onFailure(call: Call<FaultListResponseModel>, t: Throwable) {
                    customProgress.hideProgress()
                }
            })
        }catch (e: Exception){

        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FaultListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).activityMainBinding!!.mainHeader.visibility=View.GONE
    }
    private fun callApiForSiteList() {
        var loginUserData= AppSheardPreference(activity as MainActivity).getUser(PreferenceConstant.userData)
        val  customProgress: CustomProgressDialog = CustomProgressDialog().getInstance()
       // customProgress.showProgress(activity as MainActivity, "Please Wait..", false)
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
                  //  customProgress.hideProgress()
                    if (response.isSuccessful) {
                        if (response.body()!!.process) {
                            siteListRow = response!!.body()!!.rows
                        }
                    }

                }

                override fun onFailure(call: Call<SiteListResponseModel>, t: Throwable) {
                   // customProgress.hideProgress()
                }
            })
        }catch (e: Exception){

        }
    }

    override fun selectsite() {
        val customSIteListDialog= CustomSiteSelectionDialogFautList(activity as MainActivity, siteListRow!!, this)
        customSIteListDialog.show()
    }

    override fun download() {
        TODO("Not yet implemented")
    }

    override fun onpenrightsitedrawer() {
        TODO("Not yet implemented")
    }

    override fun openleftsidedrawer() {
        TODO("Not yet implemented")
    }

    override fun opendrawer() {
        ( activity as MainActivity).activityMainBinding!!.drawerlayout.openDrawer(Gravity.LEFT)
    }

    override fun opensearchdrawer() {
        fragmentFaultListBinding!!.drawerLayout.openDrawer(Gravity.RIGHT)
    }

    public fun setselection(){
        fragmentFaultListBinding!!.flexboxlayout.removeAllViews()
        selectedSitelistId!!.clear()
        for (i in 0 until siteListRow!!.size){
            if (siteListRow!!.get(i).isselect) {
                val inflater: LayoutInflater =(activity as MainActivity).getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var linearLayout = inflater.inflate(R.layout.flex_selected_site_item, null)
                val LayoutParams: LinearLayout.LayoutParams =
                    LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                LayoutParams.setMargins((activity as MainActivity).resources.getDimension(R.dimen._3sdp)
                    .toInt(), (activity as MainActivity).resources.getDimension(R.dimen._3sdp)
                    .toInt(), (activity as MainActivity).resources.getDimension(R.dimen._3sdp)
                    .toInt(), (activity as MainActivity).resources.getDimension(R.dimen._3sdp)
                    .toInt())
                linearLayout.layoutParams=LayoutParams
                val tvname: TextView = linearLayout.findViewById(R.id.tvname)
                val cross: ImageView = linearLayout.findViewById(R.id.crossview)
                tvname.setText(siteListRow!!.get(i).site_name)

                selectedSitelistId!!.add(siteListRow!!.get(i).id.toString())
                cross.setOnClickListener {
                    fragmentFaultListBinding!!.flexboxlayout.removeView(linearLayout)
                    siteListRow!!.get(i).isselect=false
                }
                fragmentFaultListBinding!!.flexboxlayout.addView(linearLayout)
            }

        }
        callApiforfaultList()
    }


}