package com.wecompli.screens.fragment

import ApiInterface
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.Gravity
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
import com.wecompli.adapter.CheckSummeryListAdapter
import com.wecompli.adapter.UserListAdapter
import com.wecompli.databinding.FragmentStartCheckBinding

import com.wecompli.handler.StartCheckHandler
import com.wecompli.model.SiteListResponseModel
import com.wecompli.model.StartCheckResponseModel
import com.wecompli.model.UserListResponseModel
import com.wecompli.network.Retrofit
import com.wecompli.screens.MainActivity
import com.wecompli.utils.customdialog.CustomSiteSelectionDialogStartCheck
import com.wecompli.viewmodel.StartChechViewModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class StartCheckFragment : Fragment(),StartCheckHandler {
    var siteListRow:ArrayList<SiteListResponseModel.SiteDetails>?=null
    var checksList:ArrayList<StartCheckResponseModel.Row>?=null
    private var param1: String? = null
    private var param2: String? = null
    var viewStartCheck:FragmentStartCheckBinding?=null
    var startCheckViewModel:StartChechViewModel?=null
    var selecteddate=""
    var selectedSideId=""
    var selectedSidname=""
    var mYear=0
    var mMonth=0
    var mDay=0
    var checkSummeryListAdapter: CheckSummeryListAdapter?=null
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
        viewStartCheck=DataBindingUtil.inflate(inflater, R.layout.fragment_start_check, container, false)
        startCheckViewModel=ViewModelProviders.of(this).get(StartChechViewModel::class.java)
        viewStartCheck!!.startCheck=startCheckViewModel
        startCheckViewModel!!.startCheckHandler=this
        callApiForSiteList()
        viewStartCheck!!.drawerLayout.openDrawer(Gravity.RIGHT)
        return viewStartCheck!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH)
        viewStartCheck!!.selectDate.setText(mDay.toString() + "/" + (mMonth + 1) + "/" + mYear)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StartCheckFragment().apply {
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

    override fun selectSite() {
        val customSIteListDialog= CustomSiteSelectionDialogStartCheck(activity as MainActivity, siteListRow!!, this)
        customSIteListDialog.show()
    }

    override fun selectDate() {
        val datePickerDialog = DatePickerDialog(
            (activity as MainActivity),
            OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                viewStartCheck!!.selectDate.setText(dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year)
            }, mYear, mMonth, mDay
        )
        datePickerDialog.getDatePicker().setMaxDate(Date().time)
        datePickerDialog.show()

    }

    override fun search() {
        callApiforChecks()
    }

    public  fun setselection() {
        viewStartCheck!!.selectedsite.text=selectedSidname
        viewStartCheck!!.selectcheckssite.text=selectedSidname
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
            selecteddate=viewStartCheck!!.selectDate.text.toString()
            val paramObject = JSONObject()
            paramObject.put("company_id","9")
            paramObject.put("check_date", "06/09/2020")
            paramObject.put("site_id","12")
            var obj: JSONObject = paramObject
            var jsonParser: JsonParser = JsonParser()
            var gsonObject: JsonObject = jsonParser.parse(obj.toString()) as JsonObject;
            val sitelistapiCall = apiInterface.callforStartCheck("Bearer "+loginUserData.token,gsonObject )
            sitelistapiCall.enqueue(object : Callback<StartCheckResponseModel> {
                override fun onResponse(call: Call<StartCheckResponseModel>, response: Response<StartCheckResponseModel>) {
                    customProgress.hideProgress()
                    if (response.isSuccessful) {
                        if (response.body()!!.process) {
                            // sitelistadapter!!.notifyDataSetChanged()
                            viewStartCheck!!.drawerLayout.closeDrawer(Gravity.RIGHT)
                            checksList=response!!.body()!!.rows
                            if(checksList!!.size>0) {
                                viewStartCheck!!.tvFaultcount.text = siteListRow!!.size.toString()
                                checkSummeryListAdapter = CheckSummeryListAdapter(activity as MainActivity, checksList!!, this@StartCheckFragment)
                                viewStartCheck!!.recChecksummerylistt.adapter=checkSummeryListAdapter

                            }
                        }
                    }

                }

                override fun onFailure(call: Call<StartCheckResponseModel>, t: Throwable) {
                    customProgress.hideProgress()
                }
            })
        }catch (e: Exception){

        }
    }



}