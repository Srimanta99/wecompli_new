package com.wecompli.screens.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.wecompli.R
import com.wecompli.databinding.FragmentEditSiteBinding

import com.wecompli.handler.EditSiteHandler
import com.wecompli.model.SiteListResponseModel
import com.wecompli.screens.MainActivity
import com.wecompli.utils.alert.CustomAlert
import com.wecompli.utils.customdialog.CustomStatusSelectionAddSite
import com.wecompli.utils.customdialog.CustomStatusSelectionEditSite
import com.wecompli.viewmodel.EditSiteViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class EditSiteFragment : Fragment(), EditSiteHandler {
    private var param1: String? = null
    private var param2: String? = null
    var siteStatus:String?=null
    var sitedetails: SiteListResponseModel.SiteDetails?=null
    var editSiteViewModel:EditSiteViewModel?=null
    var editSiteBinding: FragmentEditSiteBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            sitedetails = it.getParcelable("siteinfo")!! // Key
        }
        /*val bundle = this.arguments
        if (bundle != null) {
             sitedetails = bundle.getParcelable("siteinfo")!! // Key
        }*/
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        editSiteBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_edit_site,container,false)
        editSiteViewModel=ViewModelProviders.of(this).get(EditSiteViewModel::class.java)
        editSiteBinding!!.editSite=editSiteViewModel
        editSiteViewModel!!.editSiteHandler=this
        editSiteBinding!!.etSitename.setText(sitedetails!!.site_name)
        if (sitedetails!!.status_id==1){
            editSiteBinding!!.imgStatus.setBackgroundResource(R.drawable.active)
            editSiteBinding!!.tvsitestatus.setText((activity as MainActivity).resources.getString(R.string.active))
            siteStatus="1"
        }else {
            editSiteBinding!!.imgStatus.setBackgroundResource(R.drawable.inactive)
            editSiteBinding!!.tvsitestatus.setText((activity as MainActivity).resources.getString(R.string.inactive_site))
            siteStatus="0"

        }
        return editSiteBinding!!.root

    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditSiteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun addSiteSubmit() {
        if (!editSiteBinding!!.etSitename.text.toString().equals("")){
            callApiforAddSite()
        }else{
            CustomAlert.showalert(activity as MainActivity,"Please enter site name")
        }
    }

    private fun callApiforAddSite() {

    }

    override fun openStatusSelection() {
        val customStatusSelectionDialog= CustomStatusSelectionEditSite(activity as MainActivity,this)
        customStatusSelectionDialog.show()

    }

    override fun onResume() {
        super.onResume()
        ( activity as MainActivity).activityMainBinding!!.mainHeader.visibility=View.VISIBLE
        ( activity as MainActivity).activityMainBinding!!.tvHeaderText.setText("EDIT SITE")
    }

    public fun changeActiveStatus(){
        editSiteBinding!!.tvstatus.setText("Active")
        editSiteBinding!!.imgStatus.setBackgroundResource(R.drawable.active)
        siteStatus="1"
        //  addSiteView!!.llStatus.setBackgroundResource(R.drawable.asscolor_round)
        //  addSiteView!!.tvStatus1.setTextColor(activity!!.resources.getColor(R.color.textColor))

    }

    public fun changeInActiveStatus(){
        editSiteBinding!!.tvstatus.setText("Inactive")
        editSiteBinding!!.imgStatus.setBackgroundResource(R.drawable.inactive)
        siteStatus="0"
    }


}