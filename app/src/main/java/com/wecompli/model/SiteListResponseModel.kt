package com.wecompli.model

data class SiteListResponseModel(val process:Boolean, val rows:ArrayList<SiteDetails>,val message:String) {
    data class  SiteDetails(val id:Int,val company_id:Int,val site_name:String,val status_id:Int, val created_at:String){

    }
}