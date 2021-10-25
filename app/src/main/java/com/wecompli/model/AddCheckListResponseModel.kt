package com.wecompli.model

data  class AddCheckListResponseModel(val process:Boolean,val message:String,val error:Error) {
    data class Error(val company_id:String,val category_name:String,val site_ids:String,val category_purpose:String,val season_id:String,
    val check_date:String,val status_id:String)
}