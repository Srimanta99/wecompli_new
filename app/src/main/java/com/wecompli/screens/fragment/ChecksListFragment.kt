package com.wecompli.screens.fragment

import ApiInterface
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
import com.wecompli.adapter.UserListAdapter
import com.wecompli.databinding.FragmentChecksListBinding
import com.wecompli.handler.CheckListHandler


import com.wecompli.model.CheckListResponseModel
import com.wecompli.model.UserListResponseModel
import com.wecompli.network.Retrofit
import com.wecompli.screens.MainActivity
import com.wecompli.viewmodel.ChecksListViewModel
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ChecksListFragment : Fragment(), CheckListHandler {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var checklistView: FragmentChecksListBinding?=null
    var checksListViewModel:ChecksListViewModel?=null
    var checklist=ArrayList<CheckListResponseModel.RowDetails>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        checklistView=DataBindingUtil.inflate(inflater,R.layout.fragment_checks_list,container,false)
        checksListViewModel=ViewModelProviders.of(this).get(ChecksListViewModel::class.java)
        checklistView!!.checkList=checksListViewModel
        checksListViewModel!!.checklisthander=this
        checklistApicall()
        return checklistView!!.root
    }

    private fun checklistApicall() {
        var loginUserData= AppSheardPreference(activity as MainActivity).getUser(PreferenceConstant.userData)
        val  customProgress: CustomProgressDialog = CustomProgressDialog().getInstance()
        customProgress.showProgress(activity as MainActivity, "Please Wait..", false)
        val apiInterface= Retrofit.retrofitInstance?.create(ApiInterface::class.java)
        try {
            val paramObject = JSONObject()
            paramObject.put("company_id","9")

            var obj: JSONObject = paramObject
            var jsonParser: JsonParser = JsonParser()
            var gsonObject: JsonObject = jsonParser.parse(obj.toString()) as JsonObject;
            val sitelistapiCall = apiInterface.callChecklist("Bearer "+loginUserData.token,gsonObject )
            sitelistapiCall.enqueue(object : Callback<CheckListResponseModel> {
                override fun onResponse(call: Call<CheckListResponseModel>, response: Response<CheckListResponseModel>) {
                    customProgress.hideProgress()
                    if (response.isSuccessful) {
                       if(response.body()!!.process){
                           checklist=response!!.body()!!.rows
                           val checkListAdapter=CheckListAdapter(activity as MainActivity,checklist,this@ChecksListFragment)
                           checklistView!!.recChecklist!!.adapter=checkListAdapter
                       }
                    }

                }

                override fun onFailure(call: Call<CheckListResponseModel>, t: Throwable) {
                    customProgress.hideProgress()
                }
            })
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChecksListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    override fun onResume() {
        super.onResume()
        (activity as MainActivity).activityMainBinding!!.mainHeader.visibility=View.GONE
    }

    override fun addnewCheck() {
        (activity as MainActivity).openFragment(AddCheckFragment())
    }

}