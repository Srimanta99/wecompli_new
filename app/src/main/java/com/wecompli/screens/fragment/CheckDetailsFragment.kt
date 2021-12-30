package com.wecompli.screens.fragment

import ApiInterface
import android.database.DatabaseUtils
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.gsscanner.utils.AppSheardPreference
import com.gsscanner.utils.PreferenceConstant
import com.sculptee.utils.customprogress.CustomProgressDialog
import com.wecompli.R
import com.wecompli.adapter.CheckListAdapter
import com.wecompli.adapter.ChecksDetailsAdapter
import com.wecompli.databinding.FragmentCheckDetailsBinding
import com.wecompli.model.CheckListDetailsResponse
import com.wecompli.model.CheckListResponseModel
import com.wecompli.model.ChecksListModel
import com.wecompli.network.Retrofit
import com.wecompli.screens.MainActivity
import com.wecompli.utils.alert.CustomAlert
import com.wecompli.viewmodel.CheckdetailsViewModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CheckDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var adapter:ChecksDetailsAdapter?=null
    var chkDetailsView:FragmentCheckDetailsBinding?=null
     var list:ArrayList<CheckListDetailsResponse.Row>?=null
    var catid=""
    var date=""
    var siteid=""
    var categoryname=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        chkDetailsView=DataBindingUtil.inflate(inflater,R.layout.fragment_check_details,container,false)
        var CheckdetailsViewModel=ViewModelProviders.of(this).get(CheckdetailsViewModel::class.java)
        chkDetailsView!!.checkdetails=CheckdetailsViewModel

        val bundle=arguments
        catid= bundle!!.getString("category_Id")!!
        date=bundle!!.getString("selecteddate")!!
        siteid=bundle.getString("siteid")!!
        categoryname=bundle!!.getString("categoryname")!!
        checklistApicall()
        return chkDetailsView!!.root
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CheckDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun checklistApicall() {
        var loginUserData= AppSheardPreference(activity as MainActivity).getUser(PreferenceConstant.userData)
        val  customProgress: CustomProgressDialog = CustomProgressDialog().getInstance()
        customProgress.showProgress(activity as MainActivity, "Please Wait..", false)
        val apiInterface= Retrofit.retrofitInstance?.create(ApiInterface::class.java)
        try {
            val paramObject = JSONObject()
            paramObject.put("company_id","9")
            paramObject.put("check_date","6/9/2021")
            paramObject.put("site_id","12")
            paramObject.put("category_id","203")
            var obj: JSONObject = paramObject
            var jsonParser: JsonParser = JsonParser()
            var gsonObject: JsonObject = jsonParser.parse(obj.toString()) as JsonObject;
            val sitelistapiCall = apiInterface.callforChecksDetails("Bearer "+loginUserData.token,gsonObject )
            sitelistapiCall.enqueue(object : Callback<CheckListDetailsResponse> {
                override fun onResponse(call: Call<CheckListDetailsResponse>, response: Response<CheckListDetailsResponse>) {
                    customProgress.hideProgress()
                    if (response.isSuccessful) {
                        if(response.body()!!.process){
                            list=response!!.body()!!.rows
                            adapter= ChecksDetailsAdapter(activity as MainActivity,list!!,this@CheckDetailsFragment)
                            chkDetailsView!!.recCheckdetalis.adapter=adapter
                        }
                        else
                            CustomAlert.showalert(activity as MainActivity,"No Details Found.")
                    }

                }

                override fun onFailure(call: Call<CheckListDetailsResponse>, t: Throwable) {
                    customProgress.hideProgress()
                }
            })
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).activityMainBinding!!.mainHeader.visibility=View.VISIBLE
        (activity as MainActivity).activityMainBinding!!.tvHeaderText.text=categoryname
    }

}