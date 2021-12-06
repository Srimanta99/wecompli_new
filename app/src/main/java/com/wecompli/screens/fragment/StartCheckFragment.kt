package com.wecompli.screens.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.wecompli.R
import com.wecompli.databinding.FragmentStartCheckBinding
import com.wecompli.handler.StartCheckHandler
import com.wecompli.screens.MainActivity
import com.wecompli.viewmodel.StartChechViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StartCheckFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StartCheckFragment : Fragment(),StartCheckHandler {

    private var param1: String? = null
    private var param2: String? = null
    var viewStartCheck:FragmentStartCheckBinding?=null
    var startCheckViewModel:StartChechViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewStartCheck=DataBindingUtil.inflate(inflater,R.layout.fragment_start_check,container,false)
        startCheckViewModel=ViewModelProviders.of(this).get(StartChechViewModel::class.java)
        viewStartCheck!!.startCheck=startCheckViewModel
        startCheckViewModel!!.startCheckHandler=this
        return viewStartCheck!!.root
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
        TODO("Not yet implemented")
    }

    override fun selectDate() {
        TODO("Not yet implemented")
    }

    override fun Search() {
        TODO("Not yet implemented")
    }
}