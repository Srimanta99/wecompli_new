package com.wecompli.model

data class LoginResponseModel(val user_id:Int,val full_name:String,val email:String,val company_id:String,
val company_name:String,val company_logo:String,val user_type:String,val user_role:String,val user_profile_picture_path:String,val package_name:String,
val payment_start_datetime:String,val payment_end_datetime:String,val payment_status:String,val created_at:String,val token:String,val process:Boolean) {
}