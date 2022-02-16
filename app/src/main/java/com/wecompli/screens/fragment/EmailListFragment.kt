package com.wecompli.screens.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.wecompli.R
import com.wecompli.databinding.FragmentEmailListBinding
import com.wecompli.handler.EmailListHandler
import com.wecompli.viewmodel.EmailViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class EmailListFragment : Fragment(),EmailListHandler {

    private var param1: String? = null
    private var param2: String? = null

    var emailViewModel:EmailViewModel?=null
    var fragmentEmailListBinding:FragmentEmailListBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentEmailListBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_email_list,container,false)
        val emailViewmodel=ViewModelProviders.of(this).get(EmailViewModel::class.java)
        fragmentEmailListBinding!!.emailviewmodel=emailViewmodel
        emailViewmodel.emailListHandler=this
        return  fragmentEmailListBinding!!.root

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EmailListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EmailListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun addEmail() {

    }
}