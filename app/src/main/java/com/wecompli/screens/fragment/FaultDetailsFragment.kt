package com.wecompli.screens.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.wecompli.R
import com.wecompli.databinding.FragmentFaultDetailsBinding
import com.wecompli.screens.MainActivity
import com.wecompli.viewmodel.FaultDetailsViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FaultDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var faultDetailsBinding:FragmentFaultDetailsBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        faultDetailsBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_fault_details,container,false)
        val viewBindingFaultDetails=ViewModelProviders.of(this).get(FaultDetailsViewModel::class.java)
        faultDetailsBinding!!.faultDetailsViewModel=viewBindingFaultDetails
        return faultDetailsBinding!!.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FaultDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).activityMainBinding!!.mainHeader.visibility=View.VISIBLE
    }
}