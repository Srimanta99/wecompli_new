package com.wecompli.screens.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.wecompli.R
import com.wecompli.databinding.FragmentDashBoardBinding
import com.wecompli.handler.DashBoardHandler
import com.wecompli.screens.MainActivity
import com.wecompli.viewmodel.DashBoardViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class DashBoardFragment : Fragment(),DashBoardHandler {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var viewmodel:DashBoardViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       var view:FragmentDashBoardBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_dash_board,container,false)
      //  return inflater.inflate(R.layout.fragment_dash_board, container, false)
        viewmodel=ViewModelProviders.of(this).get(DashBoardViewModel::class.java)
        view.dashViewModel=viewmodel
        viewmodel!!.dashBoardHandler=this
        return  view.root
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

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).activityMainBinding!!.tvHeaderText.setText(resources.getString(R.string.dashboard))
    }
}