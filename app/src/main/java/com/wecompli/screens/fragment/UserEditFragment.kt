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
import com.wecompli.databinding.FragmentUserEditBinding
import com.wecompli.handler.AddUserHandler
import com.wecompli.model.RoleListResponseModel
import com.wecompli.model.SiteListResponseModel
import com.wecompli.model.UserListResponseModel
import com.wecompli.network.Retrofit
import com.wecompli.screens.MainActivity
import com.wecompli.utils.alert.CustomAlert
import com.wecompli.utils.customdialog.*
import com.wecompli.viewmodel.EditUserViewModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserEditFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserEditFragment : Fragment(), AddUserHandler {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var userDetail: UserListResponseModel.UserDetails?=null
    var userEditBinding: FragmentUserEditBinding?=null
    var editUserViewModel:EditUserViewModel?=null
    var siteStatus:String=""
    var roleList:ArrayList<RoleListResponseModel.RoleDetails>?= ArrayList()
    var siteListRow:ArrayList<SiteListResponseModel.SiteDetails>?=null
    var roleId:String?=null
    var siteids:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            userDetail=it.getParcelable("userinfo")
        }
        val bundle = this.arguments
        if (bundle != null) {
            userDetail = bundle.getParcelable("userinfo")!! // Key
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        userEditBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_user_edit,container,false)
        editUserViewModel=ViewModelProviders.of(this).get(EditUserViewModel::class.java)
        userEditBinding!!.editUser=editUserViewModel
        editUserViewModel!!.addUserHandler=this
        callApiForSiteList()
        callApiforRolesList()
        setUserValue()
        return userEditBinding!!.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserEditFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    private fun setUserValue() {
        userEditBinding!!.etUserName.setText(userDetail!!.user_first_name)
        userEditBinding!!.etUserEmail.setText(userDetail!!.user_email_ID)
        userEditBinding!!.tvRole.setText(userDetail!!.roles.get(0).role_name)
        if (userDetail!!.status_id==1){
            userEditBinding!!.imgStatus.setBackgroundResource(R.drawable.active)
            userEditBinding!!.tvsitestatus.setText((activity as MainActivity).resources.getString(R.string.active))
        }else {
            userEditBinding!!.imgStatus.setBackgroundResource(R.drawable.inactive)
            userEditBinding!!.tvsitestatus.setText((activity as MainActivity).resources.getString(R.string.inactive_site))

        }

          for (i in 0 until userDetail!!.sites.size){

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
                  val tvname: TextView = linearLayout!!.findViewById(R.id.tvname)
                  val cross: ImageView = linearLayout!!.findViewById(R.id.crossview)
                  tvname.setText(userDetail!!.sites.get(i).site_name)
                    userDetail
                  cross.setOnClickListener {
                      userEditBinding!!.flexboxlayout.removeView(linearLayout)
                      //userDetail!!.sites!!.get(i).isselect=false
                  }
                  userEditBinding!!.flexboxlayout!!.addView(linearLayout)

          }

    }

    override fun onResume() {
        super.onResume()
        ( activity as MainActivity).activityMainBinding!!.mainHeader.visibility=View.VISIBLE
        ( activity as MainActivity).activityMainBinding!!.tvHeaderText.setText("EDIT USER")
    }

    public fun changeActiveStatus(){
        userEditBinding!!.tvstatus.setText("Active")
        userEditBinding!!.imgStatus.setBackgroundResource(R.drawable.active)
        siteStatus="1"
        //  addSiteView!!.llStatus.setBackgroundResource(R.drawable.asscolor_round)
        //  addSiteView!!.tvStatus1.setTextColor(activity!!.resources.getColor(R.color.textColor))

    }

    public fun changeInActiveStatus(){
        userEditBinding!!.tvstatus.setText("Inactive")
        userEditBinding!!.imgStatus.setBackgroundResource(R.drawable.inactive)
        siteStatus="0"
    }

    override fun onsubmit() {

    }

    override fun onstatusSelect() {
        val customStatusSelectionDialog= CustomStatusSelectionEditUser(activity as MainActivity,this)
        customStatusSelectionDialog.show()
    }

    override fun siteselection() {
        val customstatustDialog= CustomSiteSelectionDialogAUserEdit(activity as MainActivity, siteListRow!!, this,userDetail!!.sites)
        customstatustDialog.show()
    }

    override fun roleselection() {
        val customSIteListDialog= CustomRoleSelectionDialogUserEdit(activity as MainActivity, roleList!!, this)
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
        userEditBinding!!.flexboxlayout.removeAllViews()
        siteids="";
        for (i in 0 until siteListRow!!.size){
            siteids=siteids+","+siteListRow!!.get(i).id.toString()
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
                    userEditBinding!!.flexboxlayout.removeView(linearLayout)
                    siteListRow!!.get(i).isselect=false
                }
                userEditBinding!!.flexboxlayout!!.addView(linearLayout)
            }
        }
    }
}