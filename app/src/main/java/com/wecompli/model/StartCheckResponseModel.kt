package com.wecompli.model

data class StartCheckResponseModel(
    val error: Error,
    val message: String,
    val process: Boolean,
    val rows: ArrayList<Row>,
    val todaysFaultCount: Int
){
    data class Row(
        val category_name: String,
        val category_note: String,
        val category_purpose: String,
        val check_date: String,
        val checks_count: Int,
        val id: Int,
        val progress_percentage: Int,
        val season_id: Int,
        val season_name: String,
        val status_id: Int,
        val total_checks_count: Int
    )


    data class Error(
        val check_date: String,
        val company_id: String,
        val site_id: String
    )
}