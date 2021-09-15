package com.wecompli.screens.fragment

import ApiInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import androidx.recyclerview.widget.RecyclerView


import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.gsscanner.utils.AppSheardPreference
import com.gsscanner.utils.PreferenceConstant
import com.sculptee.utils.customprogress.CustomProgressDialog
import com.wecompli.R
import com.wecompli.adapter.RoleListAdapter
import com.wecompli.adapter.SiteListAdapter

import com.wecompli.databinding.FragmentRolesListBinding
import com.wecompli.handler.RoleListHandler
import com.wecompli.model.RoleListResponseModel
import com.wecompli.model.SiteListResponseModel
import com.wecompli.network.Retrofit
import com.wecompli.screens.MainActivity
import com.wecompli.utils.alert.CustomAlert
import com.wecompli.viewmodel.RoleListViewModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



class RolesListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var roleListViewModel: RoleListViewModel?=null
    var viewRolelist: FragmentRolesListBinding?=null
    var roleview:View?=null
    var recRoles:RecyclerView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
          viewRolelist= FragmentRolesListBinding.inflate(inflater,container,false)
       // roleview=inflater.inflate(R.layout.fragment_roles_list, container, false)
       // roleListViewModel= ViewModelProviders.of(this).get(RoleListViewModel::class.java)
      //  viewRolelist!!.roleListViewModel=roleListViewModel
       // roleListViewModel!!.roleListHandler=this
          callApiforRolesList();
          return viewRolelist!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ( activity as MainActivity).activityMainBinding!!.mainHeader.visibility=View.GONE

        // recRoles=roleview!!.findViewById(R.id.rec_roles)

    }
    private fun callApiforRolesList() {
        var loginUserData= AppSheardPreference(activity as MainActivity).getUser(PreferenceConstant.userData)
        val  customProgress: CustomProgressDialog = CustomProgressDialog().getInstance()
        customProgress.showProgress(activity as MainActivity, "Please Wait..", false)
        val apiInterface= Retrofit.retrofitInstance?.create(ApiInterface::class.java)
        try {
            val paramObject = JSONObject()
            paramObject.put("company_id", "79")
            paramObject.put("status_id","1")
            var obj: JSONObject = paramObject
            var jsonParser: JsonParser = JsonParser()
            var gsonObject: JsonObject = jsonParser.parse(obj.toString()) as JsonObject;
            val sitelistapiCall = apiInterface.callRoeListApi("Bearer "+loginUserData.token,gsonObject )
            sitelistapiCall.enqueue(object : Callback<RoleListResponseModel> {
                override fun onResponse(call: Call<RoleListResponseModel>, response: Response<RoleListResponseModel>) {
                    customProgress.hideProgress()
                    if (response.isSuccessful) {
                        if (response.body()!!.process) {

                          val roleListAdapter=RoleListAdapter(activity as  MainActivity,response!!.body()!!.rows,this@RolesListFragment)
                            viewRolelist!!.contentRole.recRoles!!.adapter=roleListAdapter
                        }
                        else
                            CustomAlert.showalert(activity as  MainActivity,"No List found")

                    }

                }

                override fun onFailure(call: Call<RoleListResponseModel>, t: Throwable) {
                    customProgress.hideProgress()
                }
            })
        }catch (e: Exception){

        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RolesListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}