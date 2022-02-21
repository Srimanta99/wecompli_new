package com.wecompli.screens.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.wecompli.R
import com.wecompli.databinding.FragmentFristTimeDashBoardBinding
import com.wecompli.handler.FristTimeDashBoardHandler
import com.wecompli.screens.MainActivity
import com.wecompli.viewmodel.FristDashBoardViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FristTimeDashBoardFragment : Fragment(), FristTimeDashBoardHandler {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var fristTimeDashBoardBinding:FragmentFristTimeDashBoardBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fristTimeDashBoardBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_frist_time_dash_board,container,false)
        val viewmodel=ViewModelProviders.of(this).get(FristDashBoardViewModel::class.java)
        fristTimeDashBoardBinding!!.fristtimeDashboardViewModel=viewmodel
        viewmodel.fristTimeDashBoardHandler=this
        return fristTimeDashBoardBinding!!.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FristTimeDashBoardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun addSite() {
        (activity as MainActivity).openFragment(AddSiteFragment())
    }

    override fun adduser() {
        (activity as MainActivity).openFragment(AddUserFragment())
    }
}