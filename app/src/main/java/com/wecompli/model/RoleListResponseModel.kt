package com.wecompli.model

data class RoleListResponseModel(val process:Boolean,val rows:ArrayList<RoleDetails>) {
    data class RoleDetails(val id:Int, val company_id:Int,val role_name:String,val status_id:Int,val role_list:ArrayList<RoleItems>)
    data class RoleItems(val id:Int,val module_id:Int,val function_name:String,val function_display_name:String,val function_description:Any,
    val is_display_in_menu:Int,val is_access_rights:Int,val sort_order:Int,val status_id:Int)
}