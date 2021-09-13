package com.wecompli.screens.fragment

import ApiInterface
import android.app.Activity
import android.content.Intent
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
import com.wecompli.databinding.FragmentAddSiteBinding
import com.wecompli.handler.AddSiteHandler
import com.wecompli.model.AddSiteModel
import com.wecompli.model.LoginResponseModel
import com.wecompli.network.Retrofit
import com.wecompli.screens.MainActivity
import com.wecompli.utils.alert.CustomAlert
import com.wecompli.utils.customdialog.CustomStatusSelectionDialog
import com.wecompli.viewmodel.AddSiteViewModel
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AddSiteFragment : Fragment(),AddSiteHandler {

    private var param1: String? = null
    private var param2: String? = null
    var addSiteView:FragmentAddSiteBinding?=null
    var siteStatus="1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        addSiteView=DataBindingUtil.inflate(inflater,R.layout.fragment_add_site,container,false)
        val viewModel=ViewModelProviders.of(this).get(AddSiteViewModel::class.java)
        addSiteView!!.addSite=viewModel
        viewModel.addSiteHandler=this
        return addSiteView!!.root
     }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddSiteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun addSiteSubmit() {
        if (!addSiteView!!.etSitename.text.toString().equals("")){
            callApiforAddSite()
        }else{
            CustomAlert.showalert(activity as MainActivity,"Please enter site name")
        }

    }

    private fun callApiforAddSite() {
        var loginUserData=AppSheardPreference(activity as MainActivity).getUser(PreferenceConstant.userData)

        val  customProgress: CustomProgressDialog = CustomProgressDialog().getInstance()
        customProgress.showProgress(activity as MainActivity, "Please Wait..", false)
        val apiInterface= Retrofit.retrofitInstance?.create(ApiInterface::class.java)
        try {
            val paramObject = JSONObject()
            paramObject.put("site_name", addSiteView!!.etSitename.text.toString())
            paramObject.put("company_id", loginUserData.company_id)
            paramObject.put("status_id", siteStatus)
            var obj: JSONObject = paramObject
            var jsonParser: JsonParser = JsonParser()
            var gsonObject: JsonObject = jsonParser.parse(obj.toString()) as JsonObject;
            val loginApiCall = apiInterface.callSiteCreate("Bearer "+loginUserData.token,gsonObject)
            loginApiCall.enqueue(object : Callback<AddSiteModel> {
                override fun onResponse(call: Call<AddSiteModel>, response: Response<AddSiteModel>) {
                    customProgress.hideProgress()
                    if (response.isSuccessful) {
                       if (response.body()!!.process) {
                           CustomAlert.showalert(activity as MainActivity,response!!.body()!!.message)
                        }else{
                           CustomAlert.showalert(activity as MainActivity,response!!.body()!!.message)
                       }
                    }
                }

                override fun onFailure(call: Call<AddSiteModel>, t: Throwable) {
                    customProgress.hideProgress()
                }
            })

        }catch (e: Exception)
        {
            e.printStackTrace()
        }
    }

    override fun openStatusSelection() {
        val customStatusSelectionDialog=CustomStatusSelectionDialog(activity as MainActivity,this)
        customStatusSelectionDialog.show()
    }

    public fun changeActiveStatus(){
        addSiteView!!.tvstatus.setText("Active")
        addSiteView!!.imgStatus.setBackgroundResource(R.drawable.active)
        siteStatus="1"
      //  addSiteView!!.llStatus.setBackgroundResource(R.drawable.asscolor_round)
      //  addSiteView!!.tvStatus1.setTextColor(activity!!.resources.getColor(R.color.textColor))

    }

     public fun changeInActiveStatus(){
         addSiteView!!.tvstatus.setText("Inactive")
         addSiteView!!.imgStatus.setBackgroundResource(R.drawable.inactive)
         siteStatus="0"
    }
}