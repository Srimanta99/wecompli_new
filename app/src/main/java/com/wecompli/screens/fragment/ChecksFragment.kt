package com.wecompli.screens.fragment

import android.database.DatabaseUtils
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.wecompli.R
import com.wecompli.adapter.CheckSummeryListAdapter
import com.wecompli.databinding.FragmentChecksBinding
import com.wecompli.handler.ChecksHandler

import com.wecompli.screens.MainActivity
import com.wecompli.viewmodel.ChecksViewModel
import kotlinx.android.synthetic.main.activity_main.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ChecksFragment : Fragment(), ChecksHandler{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var checksViewModel:ChecksViewModel?=null
    var checksView:FragmentChecksBinding?=null
    var checksHandler:ChecksHandler?=null

    var catid:String?=""
    var catPurpose:String?=""
    var catname:String?=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        checksView=DataBindingUtil.inflate(inflater,R.layout.fragment_checks,container,false)
        checksViewModel= ViewModelProviders.of(this).get(ChecksViewModel::class.java)
        checksView!!.checks=checksViewModel
        checksViewModel!!.checksHandler=this

        val bundle=arguments
         catid=bundle!!.getString("category_Id")
         catPurpose=bundle.getString("category_purpose")
         catname=bundle.getString("category_name")
        checksView!!.tvCategoryname.text=catname
        checksView!!.tvCategorytype.text=catPurpose

       /* checksView!!.addcheck.setOnClickListener {
            (activity as MainActivity).drawerlayout!!.closeDrawer(Gravity.LEFT)
            val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction()
            var addchecksFragment=AddChecksFragment()
            val bundle=Bundle()
            bundle.putString("catId",catid)
            bundle.putString("catPurpose",catPurpose)
            bundle.putString("catName",catname)
            addchecksFragment.arguments=bundle
            transaction.replace(R.id.content_frame, addchecksFragment)
            transaction.addToBackStack("")
            transaction.commit()
        }*/
        return checksView!!.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChecksFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    /*override fun addChecks() {
        (activity as MainActivity).drawerlayout!!.closeDrawer(Gravity.LEFT)
        val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction()
        var addchecksFragment=AddChecksFragment()
        val bundle=Bundle()
         bundle.putString("catId",catid)
        bundle.putString("catPurpose",catPurpose)
        bundle.putString("catName",catname)
        addchecksFragment.arguments=bundle
        transaction.replace(R.id.content_frame, addchecksFragment)
        transaction.addToBackStack("")
        transaction.commit()
       // (activity as MainActivity).openFragment(AddChecksFragment())
    }*/

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).activityMainBinding!!.mainHeader.visibility=View.GONE
    }

    override fun openAddCheck() {
        (activity as MainActivity).drawerlayout!!.closeDrawer(Gravity.LEFT)
        val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction()
        var addchecksFragment=AddChecksFragment()
        val bundle=Bundle()
        bundle.putString("catId",catid)
        bundle.putString("catPurpose",catPurpose)
        bundle.putString("catName",catname)
        addchecksFragment.arguments=bundle
        transaction.replace(R.id.content_frame, addchecksFragment)
        transaction.addToBackStack("")
        transaction.commit()
    }

}