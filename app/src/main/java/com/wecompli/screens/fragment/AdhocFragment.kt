package com.wecompli.screens.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.wecompli.R
import com.wecompli.databinding.FragmentAdhocBinding
import com.wecompli.handler.AdhocHandler
import com.wecompli.screens.MainActivity
import com.wecompli.viewmodel.AdHocViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AdhocFragment : Fragment(),AdhocHandler {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var viewmodel:AdHocViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        (activity as MainActivity).activityMainBinding!!.tvHeaderText.setText(resources.getString(R.string.adhoc_fault))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      //  return inflater.inflate(R.layout.fragment_adhoc, container, false)
        var view:FragmentAdhocBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_adhoc,container,false)
        viewmodel=ViewModelProviders.of(this).get(AdHocViewModel::class.java)
        view.adhocModel=viewmodel
        viewmodel!!.adhocHandler=this
        return  view.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                AdhocFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
    override fun onResume() {
        super.onResume()
        (activity as MainActivity).activityMainBinding!!.mainHeader.visibility=View.VISIBLE
    }
}