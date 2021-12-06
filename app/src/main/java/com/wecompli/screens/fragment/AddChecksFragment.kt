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
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.gsscanner.utils.AppSheardPreference
import com.gsscanner.utils.PreferenceConstant
import com.sculptee.utils.customprogress.CustomProgressDialog
import com.wecompli.R
import com.wecompli.databinding.FragmentAddChecksBinding
import com.wecompli.handler.AddCheckHandler

import com.wecompli.handler.AddChecksHandler
import com.wecompli.model.AddChecksModel
import com.wecompli.model.AddSiteModel
import com.wecompli.model.CategorySiteListResponse
import com.wecompli.model.SiteListResponseModel
import com.wecompli.network.Retrofit
import com.wecompli.screens.MainActivity
import com.wecompli.utils.alert.CustomAlert
import com.wecompli.utils.customdialog.*
import com.wecompli.viewmodel.AddChecksViewModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AddChecksFragment : Fragment(), AddChecksHandler {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var addChecksViewModel:AddChecksViewModel?=null
    var  addchecksView: FragmentAddChecksBinding?=null
    var siteListRow:ArrayList<CategorySiteListResponse.Row>?=null
    var catid:String?=""
    var catPurpose:String?=""
    var catname:String?=""
    var siteStatus="1"
    var siteids=""
    var QRCODE="N"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        addchecksView=DataBindingUtil.inflate(inflater,R.layout.fragment_add_checks,container,false)
        addChecksViewModel=ViewModelProviders.of(this).get(AddChecksViewModel::class.java)
        addchecksView!!.addCheckmodel=addChecksViewModel
        addChecksViewModel!!.addchecksHandler=this
        val bundle=arguments
        catid= bundle!!.getString("catId")
        catPurpose=bundle!!.getString("catPurpose")
        catname= bundle.getString("catName")

        addchecksView!!.tvCategoryname.text=catname
        addchecksView!!.tvCategorytype.text=catPurpose
        callApiForSiteList()
        return addchecksView!!.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddChecksFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun callApiForSiteList() {
        var loginUserData= AppSheardPreference(activity as MainActivity).getUser(PreferenceConstant.userData)
        val  customProgress: CustomProgressDialog = CustomProgressDialog().getInstance()
        customProgress.showProgress(activity as MainActivity, "Please Wait..", false)
        val apiInterface= Retrofit.retrofitInstance?.create(ApiInterface::class.java)
        try {
            val paramObject = JSONObject()
            paramObject.put("company_id", loginUserData.company_id)
            paramObject.put("category_id",catid)
            var obj: JSONObject = paramObject
            var jsonParser: JsonParser = JsonParser()
            var gsonObject: JsonObject = jsonParser.parse(obj.toString()) as JsonObject;
            val sitelistapiCall = apiInterface.callSiteListCategoryWiseApi("Bearer " + loginUserData.token, gsonObject)
            sitelistapiCall.enqueue(object : Callback<CategorySiteListResponse> {
                override fun onResponse(call: Call<CategorySiteListResponse>, response: Response<CategorySiteListResponse>) {
                    customProgress.hideProgress()
                    if (response.isSuccessful) {
                        if (response.body()!!.process) {
                            siteListRow = response!!.body()!!.rows
                        }
                    }

                }

                override fun onFailure(call: Call<CategorySiteListResponse>, t: Throwable) {
                    customProgress.hideProgress()
                }
            })
        }catch (e: Exception){

        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).activityMainBinding!!.mainHeader.visibility=View.VISIBLE
        (activity as MainActivity).activityMainBinding!!.tvHeaderText.setText("ADD CHECKS")
    }

    override fun siteDropdoen() {
         if (siteListRow!!.size>0){
             val customSIteListDialog= CustomSiteSelectionDialogChecksAdd(activity as MainActivity, siteListRow!!, this)
             customSIteListDialog.show()
         }else
             CustomAlert.showalert(activity as MainActivity," No site found")
    }

    override fun openStatusSelection() {
        val customStatusSelectionDialog= CustomStatusSelectionChecksAdd(activity as MainActivity,this)
        customStatusSelectionDialog.show()
    }

    override fun submitCheck() {
        if (!addchecksView!!.etChecklistname.text.toString().equals("")){
            if(!addchecksView!!.etNote.text.toString().equals("")){
                if(siteids!!.length>0){
                    callApiForChecksAdd()
                }else
                    CustomAlert.showalert(activity as MainActivity,"Select Site")
            }else
                CustomAlert.showalert(activity as MainActivity,"Enter Checks Note")
        }else
            CustomAlert.showalert(activity as MainActivity,"Enter Checks Name")

    }

    override fun hasqrCode() {
        val customQrSelectionDialog= CustomQRCodeSelectionChecksAdd(activity as MainActivity,this)
        customQrSelectionDialog.show()
    }

    private fun callApiForChecksAdd() {
        var loginUserData=AppSheardPreference(activity as MainActivity).getUser(PreferenceConstant.userData)

        val  customProgress: CustomProgressDialog = CustomProgressDialog().getInstance()
        customProgress.showProgress(activity as MainActivity, "Please Wait..", false)
        val apiInterface= Retrofit.retrofitInstance?.create(ApiInterface::class.java)
        try {
            val paramObject = JSONObject()
            paramObject.put("check_name", addchecksView!!.etChecklistname.text.toString())
            paramObject.put("check_note", addchecksView!!.etNote.text.toString())
            paramObject.put("category_id", "19")
            paramObject.put("site_id", siteids.substring(1))
            paramObject.put("check_type_id", "1")
            paramObject.put("has_qrcode",QRCODE)
            paramObject.put("status_id",siteStatus)
            var obj: JSONObject = paramObject
            var jsonParser: JsonParser = JsonParser()
            var gsonObject: JsonObject = jsonParser.parse(obj.toString()) as JsonObject;
            val loginApiCall = apiInterface.callforChecksCreate("Bearer "+loginUserData.token,gsonObject)
            loginApiCall.enqueue(object : Callback<AddChecksModel> {
                override fun onResponse(call: Call<AddChecksModel>, response: Response<AddChecksModel>) {
                    customProgress.hideProgress()
                    if (response.isSuccessful) {
                        if (response.body()!!.process) {
                            CustomAlert.showalert(activity as MainActivity,response!!.body()!!.message)
                        }
                    }
                }

                override fun onFailure(call: Call<AddChecksModel>, t: Throwable) {
                    customProgress.hideProgress()
                }
            })

        }catch (e: Exception)
        {
            e.printStackTrace()
        }
    }
    /* override fun siteDropdoen() {
         Toast.makeText(activity ,"lkfjl",Toast.LENGTH_LONG).show()
     }*/


   /* fun click(view: View){
        Toast.makeText(activity ,"lkfjl ascasc",Toast.LENGTH_LONG).show()

    }*/

    public fun changeActiveStatus(){
        addchecksView!!.statusValue.setText("Active")
        addchecksView!!.imgStatus.setBackgroundResource(R.drawable.active)
        siteStatus="1"
        //  addSiteView!!.llStatus.setBackgroundResource(R.drawable.asscolor_round)
        //  addSiteView!!.tvStatus1.setTextColor(activity!!.resources.getColor(R.color.textColor))

    }

    public fun changeInActiveStatus(){
        addchecksView!!.statusValue.setText("Inactive")
        addchecksView!!.imgStatus.setBackgroundResource(R.drawable.inactive)
        siteStatus="0"
    }
    public fun QrcodeShow(){
        addchecksView!!.tvqrcode.setText("Show")
        QRCODE="Y"
        //  addSiteView!!.llStatus.setBackgroundResource(R.drawable.asscolor_round)
        //  addSiteView!!.tvStatus1.setTextColor(activity!!.resources.getColor(R.color.textColor))
    }

    public fun QrcodeHide(){
        addchecksView!!.tvqrcode.setText("Hide")
        QRCODE="N"
    }

    public fun setselection(){
        addchecksView!!.flexboxlayout.removeAllViews()
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
                    addchecksView!!.flexboxlayout.removeView(linearLayout)
                    siteListRow!!.get(i).isselect=false
                }
                addchecksView!!.flexboxlayout!!.addView(linearLayout)
            }
        }
    }
}

interface  AddCheckHandler{
    fun onClick()
}