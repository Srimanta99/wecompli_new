package com.wecompli.model

data class RoleListResponseModel(val process:Boolean,val rows:ArrayList<RoleDetails>) {
    data class RoleDetails(val id:Int, val company_id:Int,val role_name:String,val status_id:Int,val role_list:ArrayList<RoleItems>)
    data class RoleItems(val function_display_name:String,val function_icon:String)
}