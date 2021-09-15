package com.wecompli.model

data class UserListResponseModel(val process:Boolean,val message:String,val rows:ArrayList<UserDetails>) {
    data class UserDetails(val id:Int,val user_first_name:String,val user_email_ID:String,val status_id:Int,val created_at:String,
    val sites:ArrayList<Sites>,val roles:ArrayList<Role>)
    data class Sites(val site_name:String,val pivot:pivot)
    data class pivot(val user_id:Int,val site_id:Int)
    data class Role(val role_name:String,val pivot:pivot)
}