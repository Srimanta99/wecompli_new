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
import com.wecompli.adapter.SiteListAdapter
import com.wecompli.adapter.UserListAdapter
import com.wecompli.databinding.FragmentUserListBinding

import com.wecompli.handler.UserListhandler
import com.wecompli.model.SiteListResponseModel
import com.wecompli.model.UserListResponseModel
import com.wecompli.network.Retrofit
import com.wecompli.screens.MainActivity
import com.wecompli.viewmodel.UserListViewModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class UserListFragment : Fragment(), UserListhandler {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var userlistView: FragmentUserListBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         userlistView=DataBindingUtil.inflate(inflater,R.layout.fragment_user_list, container, false)
        var userViewModel:UserListViewModel=ViewModelProviders.of(this).get(UserListViewModel::class.java)
        userlistView!!.userListModel=userViewModel
        callApiforUserList()
        userViewModel!!.userListHandler=this
        return userlistView!!.root
    }

    private fun callApiforUserList() {
        var loginUserData= AppSheardPreference(activity as MainActivity).getUser(PreferenceConstant.userData)
        val  customProgress: CustomProgressDialog = CustomProgressDialog().getInstance()
        customProgress.showProgress(activity as MainActivity, "Please Wait..", false)
        val apiInterface= Retrofit.retrofitInstance?.create(ApiInterface::class.java)
        try {
            val paramObject = JSONObject()
            paramObject.put("company_id",loginUserData.company_id)
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
                            val userlistadapter=UserListAdapter(activity as MainActivity,response!!.body()!!.rows,this@UserListFragment)
                            userlistView!!.recUsers.adapter=userlistadapter
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ( activity as MainActivity).activityMainBinding!!.mainHeader.visibility=View.GONE
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

    override fun showrolelist() {
        (activity as MainActivity).openFragment(RolesListFragment())
    }

    override fun addrole() {
        (activity as MainActivity).openFragment(AddRoleFragment())
    }

    override fun adduser() {
        (activity as MainActivity).openFragment(AddUserFragment())
    }
}