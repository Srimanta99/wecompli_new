package com.wecompli.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wecompli.databinding.ItemSelectRoleBinding
import com.wecompli.model.RoleListResponseModel
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.AddUserFragment
import com.wecompli.utils.customdialog.CustomRoleSelectionDialogAddUser
import com.wecompli.utils.customfont.CustomTypeface

class RoleSelectionListAdapterAddUser(
    val activity: MainActivity,
    val rolelist: ArrayList<RoleListResponseModel.RoleDetails>,
    val adduserfragment: AddUserFragment,
    val customRoleSelectionDialogAddUser: CustomRoleSelectionDialogAddUser
)
    : RecyclerView.Adapter<RoleSelectionListAdapterAddUser.ViewHolder>(){
       var itemView:ItemSelectRoleBinding?=null
    class ViewHolder(val itemSite: ItemSelectRoleBinding):RecyclerView.ViewHolder(itemSite.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSelectRoleBinding.inflate(inflater)
        itemView=binding
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        itemView!!.tvSitename.setText(rolelist.get(position).role_name)
        itemView!!.tvSitename.typeface=CustomTypeface.getRajdhaniMedium(activity)
        itemView!!.rrSelectrole.setOnClickListener {
            adduserfragment.addUserView!!.tvRole.setText(rolelist.get(position).role_name)
            adduserfragment.roleId=rolelist.get(position).id.toString()
            customRoleSelectionDialogAddUser.dismiss()


        }
       /* if ( adduserfragment.roleList!!.get(position).isselect) {
            itemView!!.chkSelect.isChecked=true
        }
        itemView!!.chkSelect.setOnCheckedChangeListener { compoundButton, b ->
            if (b){
                adduserfragment.siteListRow!!.get(position).isselect=true
            }else
                adduserfragment.siteListRow!!.get(position).isselect=false
        }*/

    }

    override fun getItemCount(): Int {
       return rolelist.size
    }
}