package com.wecompli.screens.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.wecompli.R
import com.wecompli.databinding.FragmentAddRoleBinding
import com.wecompli.handler.AddRoleHandler
import com.wecompli.screens.MainActivity
import com.wecompli.viewmodel.AddRoleViewModel


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AddRoleFragment : Fragment(),AddRoleHandler {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var viewmodel:AddRoleViewModel?=null
    var  viewaddRoleBinding:FragmentAddRoleBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewaddRoleBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_add_role, container, false)
        viewmodel=ViewModelProviders.of(this).get(AddRoleViewModel::class.java)
        viewaddRoleBinding!!.addRole=viewmodel
        viewmodel!!.addRoleHandler=this
        return viewaddRoleBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ( activity as MainActivity).activityMainBinding!!.mainHeader!!.visibility=View.VISIBLE
        ( activity as MainActivity).activityMainBinding!!.tvHeaderText.setText("ADD ROLE")
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddRoleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun addroleSubmit() {

    }

    override fun dashboardcheckClick() {
        if(viewaddRoleBinding!!.chkDashboard.isChecked){
             viewaddRoleBinding!!.llDashboardAddRole.setBackgroundResource(R.drawable.rectangular_shape_rounded_corner_blue)
             viewaddRoleBinding!!.imgDashboardAddRole.setBackgroundResource(R.drawable.dashboard_white)
             viewaddRoleBinding!!.tvDashbordaddRole.setTextColor(  ( activity as MainActivity).resources.getColor(R.color.white))
        }else{
            viewaddRoleBinding!!.llDashboardAddRole.setBackgroundResource(R.drawable.rectangular_shape_rounded_corner_white)
            viewaddRoleBinding!!.imgDashboardAddRole.setBackgroundResource(R.drawable.dashboard_add_role)
            viewaddRoleBinding!!.tvDashbordaddRole.setTextColor(  ( activity as MainActivity).resources.getColor(R.color.add_role_text_color))

        }
    }

    override fun sitecheckClick() {
        if(viewaddRoleBinding!!.chkSites.isChecked){
            viewaddRoleBinding!!.llSitesAddRole.setBackgroundResource(R.drawable.rectangular_shape_rounded_corner_blue)
            viewaddRoleBinding!!.imgSiteAddRole.setBackgroundResource(R.drawable.sites_white)
            viewaddRoleBinding!!.tvSitesAddRole.setTextColor(  ( activity as MainActivity).resources.getColor(R.color.white))
        }else{
            viewaddRoleBinding!!.llSitesAddRole.setBackgroundResource(R.drawable.rectangular_shape_rounded_corner_white)
            viewaddRoleBinding!!.imgSiteAddRole.setBackgroundResource(R.drawable.sites_add_role)
            viewaddRoleBinding!!.tvSitesAddRole.setTextColor(  ( activity as MainActivity).resources.getColor(R.color.add_role_text_color))

        }
    }

    override fun usercheckClick() {
        if(viewaddRoleBinding!!.chkUser.isChecked){
            viewaddRoleBinding!!.llUsersAddRole.setBackgroundResource(R.drawable.rectangular_shape_rounded_corner_blue)
            viewaddRoleBinding!!.imgUsersAddRole.setBackgroundResource(R.drawable.users_white)
            viewaddRoleBinding!!.tvUsersAddRole.setTextColor(  ( activity as MainActivity).resources.getColor(R.color.white))
        }else{
            viewaddRoleBinding!!.llUsersAddRole.setBackgroundResource(R.drawable.rectangular_shape_rounded_corner_white)
            viewaddRoleBinding!!.imgUsersAddRole.setBackgroundResource(R.drawable.users_add_role)
            viewaddRoleBinding!!.tvUsersAddRole.setTextColor(  ( activity as MainActivity).resources.getColor(R.color.add_role_text_color))

        }
    }

    override fun rolesCheckclick() {
        if(viewaddRoleBinding!!.chkRoles.isChecked){
            viewaddRoleBinding!!.llRoleAddRole.setBackgroundResource(R.drawable.rectangular_shape_rounded_corner_blue)
            viewaddRoleBinding!!.imgAddRole.setBackgroundResource(R.drawable.roles_white)
            viewaddRoleBinding!!.tvRolesAddRole.setTextColor(  ( activity as MainActivity).resources.getColor(R.color.white))
        }else{
            viewaddRoleBinding!!.llRoleAddRole.setBackgroundResource(R.drawable.rectangular_shape_rounded_corner_white)
            viewaddRoleBinding!!.imgAddRole.setBackgroundResource(R.drawable.roles_add_role)
            viewaddRoleBinding!!.tvRolesAddRole.setTextColor(  ( activity as MainActivity).resources.getColor(R.color.add_role_text_color))

        }
    }

    override fun checksClick() {
        if(viewaddRoleBinding!!.chkChecks.isChecked){
            viewaddRoleBinding!!.llChecksAddRole.setBackgroundResource(R.drawable.rectangular_shape_rounded_corner_blue)
            viewaddRoleBinding!!.imgChecksAddRole.setBackgroundResource(R.drawable.checks_white)
            viewaddRoleBinding!!.tvChecksAddRole.setTextColor(  ( activity as MainActivity).resources.getColor(R.color.white))
        }else{
            viewaddRoleBinding!!.llChecksAddRole.setBackgroundResource(R.drawable.rectangular_shape_rounded_corner_white)
            viewaddRoleBinding!!.imgChecksAddRole.setBackgroundResource(R.drawable.checks)
            viewaddRoleBinding!!.tvChecksAddRole.setTextColor(  ( activity as MainActivity).resources.getColor(R.color.add_role_text_color))

        }
    }

    override fun faultsCheckClick() {
        if(viewaddRoleBinding!!.chkFault.isChecked){
            viewaddRoleBinding!!.llFaultAddRole.setBackgroundResource(R.drawable.rectangular_shape_rounded_corner_blue)
            viewaddRoleBinding!!.imgFaultAddRole.setBackgroundResource(R.drawable.faults_white)
            viewaddRoleBinding!!.tvFaultAddRole.setTextColor(  ( activity as MainActivity).resources.getColor(R.color.white))
        }else{
            viewaddRoleBinding!!.llFaultAddRole.setBackgroundResource(R.drawable.rectangular_shape_rounded_corner_white)
            viewaddRoleBinding!!.imgFaultAddRole.setBackgroundResource(R.drawable.faults_add_role)
            viewaddRoleBinding!!.tvFaultAddRole.setTextColor(  ( activity as MainActivity).resources.getColor(R.color.add_role_text_color))

        }
    }

    override fun incidentscheckClick() {
        if(viewaddRoleBinding!!.chkIncidents.isChecked){
            viewaddRoleBinding!!.llIncidentsAddRole.setBackgroundResource(R.drawable.rectangular_shape_rounded_corner_blue)
            viewaddRoleBinding!!.imgIncidentsAddRole.setBackgroundResource(R.drawable.incidents_white)
            viewaddRoleBinding!!.tvIncidentsAddRole.setTextColor(  ( activity as MainActivity).resources.getColor(R.color.white))
        }else{
            viewaddRoleBinding!!.llIncidentsAddRole.setBackgroundResource(R.drawable.rectangular_shape_rounded_corner_white)
            viewaddRoleBinding!!.imgIncidentsAddRole.setBackgroundResource(R.drawable.incidents_add_role)
            viewaddRoleBinding!!.tvIncidentsAddRole.setTextColor(  ( activity as MainActivity).resources.getColor(R.color.add_role_text_color))

        }
    }

    override fun documentCheckClick() {
        if(viewaddRoleBinding!!.chkDocuments.isChecked){
            viewaddRoleBinding!!.llDocumentsAddRole.setBackgroundResource(R.drawable.rectangular_shape_rounded_corner_blue)
            viewaddRoleBinding!!.imgDocumentAddRole.setBackgroundResource(R.drawable.document_white)
            viewaddRoleBinding!!.tvDocumentAddRole.setTextColor(  ( activity as MainActivity).resources.getColor(R.color.white))
        }else{
            viewaddRoleBinding!!.llDocumentsAddRole.setBackgroundResource(R.drawable.rectangular_shape_rounded_corner_white)
            viewaddRoleBinding!!.imgDocumentAddRole.setBackgroundResource(R.drawable.documents_add_role)
            viewaddRoleBinding!!.tvDocumentAddRole.setTextColor(  ( activity as MainActivity).resources.getColor(R.color.add_role_text_color))

        }
    }

    override fun helpChecksClick() {
        if(viewaddRoleBinding!!.chkHelp.isChecked){
            viewaddRoleBinding!!.llChkHelpAddRole.setBackgroundResource(R.drawable.rectangular_shape_rounded_corner_blue)
            viewaddRoleBinding!!.imgHelpAddRole.setBackgroundResource(R.drawable.help_white)
            viewaddRoleBinding!!.tvHelpAddRole.setTextColor(  ( activity as MainActivity).resources.getColor(R.color.white))
        }else{
            viewaddRoleBinding!!.llChkHelpAddRole.setBackgroundResource(R.drawable.rectangular_shape_rounded_corner_white)
            viewaddRoleBinding!!.imgHelpAddRole.setBackgroundResource(R.drawable.help_add_role)
            viewaddRoleBinding!!.tvHelpAddRole.setTextColor(  ( activity as MainActivity).resources.getColor(R.color.add_role_text_color))

        }
    }

    override fun settingChecksClick() {
        if(viewaddRoleBinding!!.chkSettings.isChecked){
            viewaddRoleBinding!!.llSettingsAddRole.setBackgroundResource(R.drawable.rectangular_shape_rounded_corner_blue)
            viewaddRoleBinding!!.imgSettingsAddRole.setBackgroundResource(R.drawable.settings_white)
            viewaddRoleBinding!!.tvSettingsAddRole.setTextColor(  ( activity as MainActivity).resources.getColor(R.color.white))
        }else{
            viewaddRoleBinding!!.llSettingsAddRole.setBackgroundResource(R.drawable.rectangular_shape_rounded_corner_white)
            viewaddRoleBinding!!.imgSettingsAddRole.setBackgroundResource(R.drawable.settings_add_role)
            viewaddRoleBinding!!.tvSettingsAddRole.setTextColor(  ( activity as MainActivity).resources.getColor(R.color.add_role_text_color))

        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).activityMainBinding!!.mainHeader.visibility=View.VISIBLE
    }
}