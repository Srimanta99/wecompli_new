package com.wecompli.utils.customdialog

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wecompli.R
import com.wecompli.adapter.RoleSelectionListAdapterAddUser
import com.wecompli.adapter.RoleSelectionListAdapterUserEdit
import com.wecompli.model.RoleListResponseModel
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.AddUserFragment
import com.wecompli.screens.fragment.UserEditFragment
import com.wecompli.utils.customfont.CustomTypeface
import kotlin.collections.ArrayList

class CustomRoleSelectionDialogUserEdit(
    val mainActivity: MainActivity,
    val roleList: ArrayList<RoleListResponseModel.RoleDetails>,
    val UsereditFragment: UserEditFragment):Dialog(mainActivity) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCanceledOnTouchOutside(true)
        setContentView(R.layout.custom_role_selection_dialog_layout)
        val reclyerview:RecyclerView=findViewById(R.id.rec_sitelist)
        val tv_ok:TextView=findViewById(R.id.tv_ok)
        val cancel:TextView=findViewById(R.id.tv_no)
        val tv_selectsite:TextView=findViewById(R.id.tv_selectsite)
        tv_selectsite.typeface=CustomTypeface.getRajdhaniBold(mainActivity)
        tv_ok.typeface=CustomTypeface.getRajdhaniMedium(mainActivity)
        cancel.typeface=CustomTypeface.getRajdhaniMedium(mainActivity)
        tv_ok.setOnClickListener {
           // addUserFragment.setselection()
            dismiss()
        }
        cancel.setOnClickListener {
            dismiss()
        }
        var siteSelectionListAdapter= RoleSelectionListAdapterUserEdit(mainActivity,roleList,UsereditFragment,this)
        reclyerview.adapter=siteSelectionListAdapter

    }
}