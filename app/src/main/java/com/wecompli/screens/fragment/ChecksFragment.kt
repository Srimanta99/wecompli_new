package com.wecompli.screens.fragment

import ApiInterface
import android.database.DatabaseUtils
import android.os.Bundle
import android.view.Gravity
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
import com.wecompli.adapter.CheckSummeryListAdapter
import com.wecompli.adapter.ChecksListAdapter
import com.wecompli.databinding.FragmentChecksBinding
import com.wecompli.handler.ChecksHandler
import com.wecompli.model.ChecksListModel
import com.wecompli.model.SiteListResponseModel
import com.wecompli.model.StartCheckResponseModel
import com.wecompli.network.Retrofit

import com.wecompli.screens.MainActivity
import com.wecompli.utils.alert.CustomAlert
import com.wecompli.utils.customdialog.CustomSiteSelectionDialogChecks
import com.wecompli.utils.customdialog.CustomSiteSelectionDialogStartCheck
import com.wecompli.viewmodel.ChecksViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ChecksFragment : Fragment(), ChecksHandler{
    var siteListRow:ArrayList<SiteListResponseModel.SiteDetails>?=null
    var checklist:ArrayList<ChecksListModel.Row>?=null
    private var param1: String? = null
    private var param2: String? = null
    var checksViewModel:ChecksViewModel?=null
    var checksView:FragmentChecksBinding?=null
    var checksHandler:ChecksHandler?=null

    var catid:String?=""
    var selectedSideId=""
    var selectedSidname=""
    var catPurpose:String?=""
    var catname:String?=""
    var ChecksListAdapter:ChecksListAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        checksView=DataBindingUtil.inflate(inflater,R.layout.fragment_checks,container,false)
        checksViewModel= ViewModelProviders.of(this).get(ChecksViewModel::class.java)
        checksView!!.checks=checksViewModel
        checksViewModel!!.checksHandler=this
        val bundle=arguments
         catid=bundle!!.getString("category_Id")
         catPurpose=bundle.getString("category_purpose")
         catname=bundle.getString("category_name")
        checksView!!.tvCategoryname.text=catname
        checksView!!.tvCategorytype.text=catPurpose

       /* checksView!!.addcheck.setOnClickListener {
            (activity as MainActivity).drawerlayout!!.closeDrawer(Gravity.LEFT)
            val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction()
            var addchecksFragment=AddChecksFragment()
            val bundle=Bundle()
            bundle.putString("catId",catid)
            bundle.putString("catPurpose",catPurpose)
            bundle.putString("catName",catname)
            addchecksFragment.arguments=bundle
            transaction.replace(R.id.content_frame, addchecksFragment)
            transaction.addToBackStack("")
            transaction.commit()
        }*/
        callApiForSiteList()
                   checksView!!.drawerLayout.openDrawer(Gravity.RIGHT)
        return checksView!!.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChecksFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    /*override fun addChecks() {
        (activity as MainActivity).drawerlayout!!.closeDrawer(Gravity.LEFT)
        val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction()
        var addchecksFragment=AddChecksFragment()
        val bundle=Bundle()
         bundle.putString("catId",catid)
        bundle.putString("catPurpose",catPurpose)
        bundle.putString("catName",catname)
        addchecksFragment.arguments=bundle
        transaction.replace(R.id.content_frame, addchecksFragment)
        transaction.addToBackStack("")
        transaction.commit()
       // (activity as MainActivity).openFragment(AddChecksFragment())
    }*/

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).activityMainBinding!!.mainHeader.visibility=View.GONE
    }

    override fun searchCheck() {
     /*   if (!selectedSideId.equals(""))
            callApiforChecks();
        else
            CustomAlert.showalert((activity as MainActivity),"Select Site")
*/
    }

    override fun openAddCheck() {
        (activity as MainActivity).drawerlayout!!.closeDrawer(Gravity.LEFT)
        val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction()
        var addchecksFragment=AddChecksFragment()
        val bundle=Bundle()
        bundle.putString("catId",catid)
        bundle.putString("catPurpose",catPurpose)
        bundle.putString("catName",catname)
        addchecksFragment.arguments=bundle
        transaction.replace(R.id.content_frame, addchecksFragment)
        transaction.addToBackStack("")
        transaction.commit()
    }
    public  fun setselection() {
        checksView!!.selectedsite.text=selectedSidname

    }

    override fun opensiteSelection() {
        val customSIteListDialog= CustomSiteSelectionDialogChecks(activity as MainActivity, siteListRow!!, this)
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
            paramObject.put("user_id", loginUserData.user_id)
            var obj: JSONObject = paramObject
            var jsonParser: JsonParser = JsonParser()
            var gsonObject: JsonObject = jsonParser.parse(obj.toString()) as JsonObject;
            val sitelistapiCall = apiInterface.callSiteListApi("Bearer " + loginUserData.token, gsonObject)
            sitelistapiCall.enqueue(object : Callback<SiteListResponseModel> {
                override fun onResponse(call: Call<SiteListResponseModel>, response: Response<SiteListResponseModel>) {
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

    private fun callApiforChecks() {
        var loginUserData= AppSheardPreference(activity as MainActivity).getUser(PreferenceConstant.userData)
        val  customProgress: CustomProgressDialog = CustomProgressDialog().getInstance()
        customProgress.showProgress(activity as MainActivity, "Please Wait..", false)
        val apiInterface= Retrofit.retrofitInstance?.create(ApiInterface::class.java)
        try {
            val paramObject = JSONObject()
            paramObject.put("company_id","9")
            paramObject.put("category_id", "238")
            paramObject.put("site_id","12")
            var obj: JSONObject = paramObject
            var jsonParser: JsonParser = JsonParser()
            var gsonObject: JsonObject = jsonParser.parse(obj.toString()) as JsonObject;
            val sitelistapiCall = apiInterface.callforChecks("Bearer "+loginUserData.token,gsonObject )
            sitelistapiCall.enqueue(object : Callback<ChecksListModel> {
                override fun onResponse(call: Call<ChecksListModel>, response: Response<ChecksListModel>) {
                    customProgress.hideProgress()
                    if (response.isSuccessful) {
                        if (response.body()!!.process) {
                            checklist=response!!.body()!!.rows
                            ChecksListAdapter= ChecksListAdapter(activity as MainActivity,checklist!!,this@ChecksFragment)
                           checksView!!.recChecklist.adapter=ChecksListAdapter
                            checksView!!.drawerLayout.closeDrawer(Gravity.RIGHT)
                        }
                    }

                }

                override fun onFailure(call: Call<ChecksListModel>, t: Throwable) {
                    customProgress.hideProgress()
                }
            })
        }catch (e: Exception){

        }
    }

}