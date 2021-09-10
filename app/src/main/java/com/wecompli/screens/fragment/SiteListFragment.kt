package com.wecompli.screens.fragment

import ApiInterface
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
import com.wecompli.adapter.SiteListAdapter
import com.wecompli.databinding.FragmentSiteListBinding
import com.wecompli.handler.SiteListHandler
import com.wecompli.model.SiteListResponseModel
import com.wecompli.network.Retrofit
import com.wecompli.screens.MainActivity
import com.wecompli.viewmodel.SiteListViewModel
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SiteListFragment : Fragment() ,SiteListHandler{
    private var param1: String? = null
    private var param2: String? = null
    var sitelistViewModel:SiteListViewModel?=null
    //lateinit  var sitelistadapter: SiteListAdapter
    var siteListRow:ArrayList<SiteListResponseModel.SiteDetails>?=null
    var siteviews:FragmentSiteListBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // return inflater.inflate(R.layout.fragment_site_list, container, false)
        siteviews=DataBindingUtil.inflate(inflater, R.layout.fragment_site_list, container, false)
        sitelistViewModel=ViewModelProviders.of(this).get(SiteListViewModel::class.java)
        siteviews!!.siteListmodel=sitelistViewModel
        sitelistViewModel!!.siteListHandler=this
      //  sitelistadapter= SiteListAdapter(activity as MainActivity,siteListRow!!,this)
      //  siteviews!!.recSitelist.adapter=sitelistadapter
        callApifroSiteList()
        return siteviews!!.root

    }

    private fun callApifroSiteList() {
        var loginUserData=AppSheardPreference(activity as MainActivity).getUser(PreferenceConstant.userData)
        val  customProgress: CustomProgressDialog = CustomProgressDialog().getInstance()
        customProgress.showProgress(activity as MainActivity, "Please Wait..", false)
        val apiInterface= Retrofit.retrofitInstance?.create(ApiInterface::class.java)
        try {
            val paramObject = JSONObject()
            paramObject.put("company_id", loginUserData.company_id)
            var obj: JSONObject = paramObject
             var jsonParser: JsonParser = JsonParser()
            var gsonObject: JsonObject = jsonParser.parse(obj.toString()) as JsonObject;
            val map: HashMap<String, String> = HashMap()
            map.put("company_id",loginUserData.company_id)
            map.put("Authorization", loginUserData.token)
            val sitelistapiCall = apiInterface.callSiteListApi("Bearer "+loginUserData.token,gsonObject )
            sitelistapiCall.enqueue(object : Callback<SiteListResponseModel> {
                override fun onResponse(call: Call<SiteListResponseModel>, response: Response<SiteListResponseModel>) {
                    customProgress.hideProgress()
                    if (response.isSuccessful) {
                        if (response.body()!!.process) {
                            siteListRow = response!!.body()!!.rows
                            val sitelistadapter = SiteListAdapter(activity as MainActivity, siteListRow!!, this@SiteListFragment)
                            siteviews!!.recSitelist.adapter = sitelistadapter
                            // sitelistadapter!!.notifyDataSetChanged()
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

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SiteListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).activityMainBinding!!.tvHeaderText.setText(resources.getString(R.string.menu_sites))
    }

    override fun addSite() {
        (activity as MainActivity).openFragment(AddSiteFragment())
    }
}