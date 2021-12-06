package com.wecompli.model

data class AddChecksModel(
    val error: Error,
    val message: String,
    val process: Boolean){
    data class Error(val check_name:String,val category_id:String,val site_id:String,val check_type_id:String,val has_qrcode:String,val status_id:String)
}