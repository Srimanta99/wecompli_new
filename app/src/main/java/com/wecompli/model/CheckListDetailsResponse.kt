package com.wecompli.model

data class CheckListDetailsResponse(
    val error: Error,
    val message: String,
    val process: Boolean,
    val rows: ArrayList<Row>

){
    data class Error(
        val category_id: String,
        val check_date: String,
        val company_id: String,
        val site_id: String
    )
    data class Row(
        val check_name: String,
        val check_note: String,
        val check_type_id: Int,
        val id: Int,
        val season_id: Int,
        val season_name: String,
        val status_id: Int
    )
}