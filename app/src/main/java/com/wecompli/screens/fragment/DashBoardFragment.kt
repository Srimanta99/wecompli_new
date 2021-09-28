package com.wecompli.screens.fragment

import ApiInterface
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.gsscanner.utils.AppSheardPreference
import com.gsscanner.utils.PreferenceConstant
import com.sculptee.utils.customprogress.CustomProgressDialog
import com.wecompli.R
import com.wecompli.databinding.FragmentDashBoardBinding
import com.wecompli.handler.DashBoardHandler
import com.wecompli.model.SiteListResponseModel
import com.wecompli.network.Retrofit
import com.wecompli.screens.MainActivity
import com.wecompli.utils.alert.CustomAlert
import com.wecompli.utils.customdialog.CustomSiteSelectionDialog
import com.wecompli.viewmodel.DashBoardViewModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class DashBoardFragment : Fragment(),DashBoardHandler {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var viewmodel:DashBoardViewModel?=null
    var siteListRow:ArrayList<SiteListResponseModel.SiteDetails>?=null
    var viewDashBoard: FragmentDashBoardBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDashBoard=DataBindingUtil.inflate(inflater, R.layout.fragment_dash_board, container, false)

      //  return inflater.inflate(R.layout.fragment_dash_board, container, false)
        viewmodel=ViewModelProviders.of(this).get(DashBoardViewModel::class.java)
        viewDashBoard!!.dashViewModel=viewmodel
        viewmodel!!.dashBoardHandler=this
        if (CustomAlert.isNetworkAvailable(activity as MainActivity))
        callApiForSiteList()
        else
            CustomAlert.showaInternetlert(activity as MainActivity," No Internet Connection")
        return  viewDashBoard!!.root
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
                    response: Response<SiteListResponseModel>) {
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

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                DashBoardFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    override fun openAdhocfault() {
        (activity as MainActivity).openFragment(AdhocFragment())
    }

    override fun showSiteList() {
       val customSIteListDialog=CustomSiteSelectionDialog(activity as MainActivity, siteListRow!!, this)
        customSIteListDialog.show()
    }

    override fun showSearch() {
        TODO("Not yet implemented")
    }

    override fun openDownload() {
        TODO("Not yet implemented")
    }

    override fun startcheck() {
        TODO("Not yet implemented")
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).activityMainBinding!!.mainHeader.visibility=View.VISIBLE
        (activity as MainActivity).activityMainBinding!!.tvHeaderText.setText(resources.getString(R.string.dashboard))
    }

    public fun setselection(){
        viewDashBoard!!.flexboxlayout.removeAllViews()
        for (i in 0 until siteListRow!!.size){
            if (siteListRow!!.get(i).isselect) {
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
                    viewDashBoard!!.flexboxlayout.removeView(linearLayout)
                    siteListRow!!.get(i).isselect=false
                }
                viewDashBoard!!.flexboxlayout!!.addView(linearLayout)
            }
        }
    }

}