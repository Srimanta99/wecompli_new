package com.wecompli.screens.fragment

import ApiInterface
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.gsscanner.utils.AppSheardPreference
import com.gsscanner.utils.PreferenceConstant
import com.sculptee.utils.customprogress.CustomProgressDialog
import com.wecompli.R
import com.wecompli.databinding.FragmentFaultDetailsBinding
import com.wecompli.model.FaultDetailsResponse
import com.wecompli.model.SiteListResponseModel
import com.wecompli.network.Retrofit
import com.wecompli.screens.MainActivity
import com.wecompli.viewmodel.FaultDetailsViewModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FaultDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var faultDetailsBinding:FragmentFaultDetailsBinding?=null
    var faultid:String?=""
    var siteId:String?=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?): View? {
        faultDetailsBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_fault_details, container, false)
        val viewBindingFaultDetails=ViewModelProviders.of(this).get(FaultDetailsViewModel::class.java)
        faultDetailsBinding!!.faultDetailsViewModel=viewBindingFaultDetails

        val bundle=arguments
        if (bundle != null) {
            faultid = bundle.getString("faultid")
        }
       // callApiforfaultDetails(faultid,siteId)
        return faultDetailsBinding!!.root
    }

    private fun callApiforfaultDetails(faultid: String?, siteId: String?) {
        var loginUserData= AppSheardPreference(activity as MainActivity).getUser(PreferenceConstant.userData)
        val  customProgress: CustomProgressDialog = CustomProgressDialog().getInstance()
         customProgress.showProgress(activity as MainActivity, "Please Wait..", false)
        val apiInterface= Retrofit.retrofitInstance?.create(ApiInterface::class.java)
        try {
            val paramObject = JSONObject()
            paramObject.put("company_id", loginUserData.company_id)
            paramObject.put("site_id", siteId)
            paramObject.put("fault_id",faultid)
            var obj: JSONObject = paramObject
            var jsonParser: JsonParser = JsonParser()
            var gsonObject: JsonObject = jsonParser.parse(obj.toString()) as JsonObject;
            val sitelistapiCall = apiInterface.callforfaultDetails("Bearer " + loginUserData.token, gsonObject)
            sitelistapiCall.enqueue(object : Callback<FaultDetailsResponse> {
                override fun onResponse(call: Call<FaultDetailsResponse>, response: Response<FaultDetailsResponse>) {
                      customProgress.hideProgress()
                    if (response.isSuccessful) {
                        if (response.body()!!.process) {

                        }
                    }

                }

                override fun onFailure(call: Call<FaultDetailsResponse>, t: Throwable) {
                    customProgress.hideProgress()
                }
            })
        }catch (e: Exception){

        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FaultDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).activityMainBinding!!.mainHeader.visibility=View.VISIBLE
        (activity as MainActivity).activityMainBinding!!.tvHeaderText.text="Fault Name"
    }

    override fun onPause() {
        super.onPause()
        (activity as MainActivity).activityMainBinding!!.mainHeader.visibility=View.GONE
    }

}