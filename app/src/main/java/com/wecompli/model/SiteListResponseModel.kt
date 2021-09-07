package com.wecompli.model

data class SiteListResponseModel(val process:Boolean, val rows:ArrayList<SiteDetails>,val message:String) {
    data class  SiteDetails(val id:Int,val company_id:Int,val site_name:String,val status_id:Int, val created_at:String,val users_count:Int,
    val documents_count:Int,val incidents_count:Int,val checkslist_count:Int,val checkslistchecks_count:Int,val  faults:Int ){

    }
}