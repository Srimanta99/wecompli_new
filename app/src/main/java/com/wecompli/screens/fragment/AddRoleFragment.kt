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
}