package com.wecompli.model

data class CheckListResponseModel(val process:Boolean,val rows:ArrayList<RowDetails>) {
    data class RowDetails(val id:Int, val category_name:String,val category_note:String,val category_purpose:String,val status_id:Int,
    val checkslistchecks_count:Int,val checklist_site_map:ArrayList<CheckListSiteMap>, val checklistseasonmap:ArrayList<CheckListSeasonMap>)
    data class CheckListSiteMap(val id:Int,val site_name:String,val pivot:Privot)
    data class CheckListSeasonMap(val id:Int,val season_name:String,val check_date:String, val pivot:Privot)
    data class  Privot(val category_id:Int,val season_id:Int)
}