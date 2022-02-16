package com.wecompli.screens.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.wecompli.R
import com.wecompli.databinding.FragmentDocumentAddBinding
import com.wecompli.screens.MainActivity
import com.wecompli.viewmodel.DocAddModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DocumentAddFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
   var documentAddBinding:FragmentDocumentAddBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        documentAddBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_document_add,container,false)
        val documentAddViewModel=ViewModelProviders.of(this).get(DocAddModel::class.java)
        documentAddBinding!!.docaddviewmodel=documentAddViewModel
        return documentAddBinding!!.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DocumentAddFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).activityMainBinding!!.mainHeader.visibility=View.VISIBLE
        (activity as MainActivity).activityMainBinding!!.tvHeaderText.setText( (activity as MainActivity).resources.getString(R.string.menu_documents))
    }
}