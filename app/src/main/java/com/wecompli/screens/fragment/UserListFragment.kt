package com.wecompli.screens.fragment

import ApiInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.gsscanner.utils.AppSheardPreference
import com.gsscanner.utils.PreferenceConstant
import com.sculptee.utils.customprogress.CustomProgressDialog
import com.wecompli.R
import com.wecompli.adapter.SiteListAdapter
import com.wecompli.model.SiteListResponseModel
import com.wecompli.model.UserListResponseModel
import com.wecompli.network.Retrofit
import com.wecompli.screens.MainActivity
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class UserListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        callApiforUserList()
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    private fun callApiforUserList() {
        var loginUserData= AppSheardPreference(activity as MainActivity).getUser(PreferenceConstant.userData)
        val  customProgress: CustomProgressDialog = CustomProgressDialog().getInstance()
        customProgress.showProgress(activity as MainActivity, "Please Wait..", false)
        val apiInterface= Retrofit.retrofitInstance?.create(ApiInterface::class.java)
        try {
            val paramObject = JSONObject()
            paramObject.put("company_id","9")
            paramObject.put("status_id", "1")
            var obj: JSONObject = paramObject
            var jsonParser: JsonParser = JsonParser()
            var gsonObject: JsonObject = jsonParser.parse(obj.toString()) as JsonObject;
            val sitelistapiCall = apiInterface.callUserListApi("Bearer "+loginUserData.token,gsonObject )
            sitelistapiCall.enqueue(object : Callback<UserListResponseModel> {
                override fun onResponse(call: Call<UserListResponseModel>, response: Response<UserListResponseModel>) {
                    customProgress.hideProgress()
                    if (response.isSuccessful) {
                        if (response.body()!!.process) {
                            // sitelistadapter!!.notifyDataSetChanged()
                        }
                    }

                }

                override fun onFailure(call: Call<UserListResponseModel>, t: Throwable) {
                    customProgress.hideProgress()
                }
            })
        }catch (e: Exception){

        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}