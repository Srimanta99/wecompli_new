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
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.gsscanner.utils.AppSheardPreference
import com.gsscanner.utils.PreferenceConstant
import com.sculptee.utils.customprogress.CustomProgressDialog
import com.wecompli.R
import com.wecompli.databinding.FragmentAddUserBinding
import com.wecompli.handler.AddUserHandler
import com.wecompli.model.AddSiteModel
import com.wecompli.model.RoleListResponseModel
import com.wecompli.model.SiteListResponseModel
import com.wecompli.network.Retrofit
import com.wecompli.screens.MainActivity
import com.wecompli.utils.alert.CustomAlert
import com.wecompli.utils.customdialog.CustomRoleSelectionDialogAddUser
import com.wecompli.utils.customdialog.CustomSiteSelectionDialogAddUser
import com.wecompli.utils.customdialog.CustomStatusSelectionAddUser
import com.wecompli.viewmodel.AddUserViewModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class AddUserFragment : Fragment(), AddUserHandler {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var addUserView:FragmentAddUserBinding?=null
    var addUserViewModel:AddUserViewModel?=null
    var siteListRow:ArrayList<SiteListResponseModel.SiteDetails>?=null
    var siteStatus="1"
    var roleList:ArrayList<RoleListResponseModel.RoleDetails>?= ArrayList()
    var siteids=""
    var roleId=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        addUserView=DataBindingUtil.inflate(inflater,R.layout.fragment_add_user,container,false)
        addUserViewModel=ViewModelProviders.of(this).get(AddUserViewModel::class.java)
        addUserView!!.addUser=addUserViewModel
        addUserViewModel!!.addUserHandler=this
        callApiForSiteList()
        callApiforRolesList()
        return addUserView!!.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddUserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ( activity as MainActivity).activityMainBinding!!.mainHeader.visibility=View.VISIBLE
        ( activity as MainActivity).activityMainBinding!!.tvHeaderText.setText("ADD USER")
    }

    override fun onsubmit() {
    callApiForUserAdd();
    }

    private fun callApiForUserAdd() {
        var loginUserData=AppSheardPreference(activity as MainActivity).getUser(PreferenceConstant.userData)

        val  customProgress: CustomProgressDialog = CustomProgressDialog().getInstance()
        customProgress.showProgress(activity as MainActivity, "Please Wait..", false)
        val apiInterface= Retrofit.retrofitInstance?.create(ApiInterface::class.java)
        try {
            val paramObject = JSONObject()
            paramObject.put("user_first_name", addUserView!!.etUserName.text.toString())
            paramObject.put("user_email_ID", addUserView!!.etUserEmail.text.toString())
            paramObject.put("company_id", loginUserData.company_id)
            paramObject.put("status_id", siteStatus)
            paramObject.put("site_ids", siteids)
            paramObject.put("role_id", roleId)
            var obj: JSONObject = paramObject
            var jsonParser: JsonParser = JsonParser()
            var gsonObject: JsonObject = jsonParser.parse(obj.toString()) as JsonObject;
            val loginApiCall = apiInterface.callUserCreate("Bearer "+loginUserData.token,gsonObject)
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

    override fun onstatusSelect() {
        val customStatusSelectionDialog= CustomStatusSelectionAddUser(activity as MainActivity,this)
        customStatusSelectionDialog.show()
    }

    override fun siteselection() {
        val customstatustDialog= CustomSiteSelectionDialogAddUser(activity as MainActivity, siteListRow!!, this)
        customstatustDialog.show()
    }

    override fun roleselection() {
        val customSIteListDialog= CustomRoleSelectionDialogAddUser(activity as MainActivity, roleList!!, this)
        customSIteListDialog.show()
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
                override fun onResponse(
                    call: Call<SiteListResponseModel>,
                    response: Response<SiteListResponseModel>
                ) {
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
    private fun callApiforRolesList() {
        var loginUserData= AppSheardPreference(activity as MainActivity).getUser(PreferenceConstant.userData)
        val  customProgress: CustomProgressDialog = CustomProgressDialog().getInstance()
        customProgress.showProgress(activity as MainActivity, "Please Wait..", false)
        val apiInterface= Retrofit.retrofitInstance?.create(ApiInterface::class.java)
        try {
            val paramObject = JSONObject()
            paramObject.put("company_id", "9")
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
                            /*for (i in 0 until response.body()!!.rows.size){
                                 for (p in 0 until  response!!.body()!!.rows.get(i).role_list.size){
                                     roleList!!.add(response!!.body()!!.rows.get(i).role_list.get(p).function_display_name)
                                 }
                            }*/
                           roleList=response!!.body()!!.rows
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
    public fun setselection(){
        addUserView!!.flexboxlayout.removeAllViews()
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
                    addUserView!!.flexboxlayout.removeView(linearLayout)
                    siteListRow!!.get(i).isselect=false
                }
                addUserView!!.flexboxlayout!!.addView(linearLayout)
            }
        }
    }
    public fun changeActiveStatus(){
        addUserView!!.tvstatus.setText("Active")
        addUserView!!.imgStatus.setBackgroundResource(R.drawable.active)
        siteStatus="1"
        //  addSiteView!!.llStatus.setBackgroundResource(R.drawable.asscolor_round)
        //  addSiteView!!.tvStatus1.setTextColor(activity!!.resources.getColor(R.color.textColor))

    }

    public fun changeInActiveStatus(){
        addUserView!!.tvstatus.setText("Inactive")
        addUserView!!.imgStatus.setBackgroundResource(R.drawable.inactive)
        siteStatus="0"
    }
}