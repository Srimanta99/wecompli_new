package com.wecompli.model

data class DashBoardPercentageResponse(
    val error: Error,
    val message: String,
    val process: Boolean,
    val rows: List<Row>,
    val todaysFaultCount: Int,
    val totalCheckedChecks: Int,
    val totalChecks: Int,
    val total_percentage: Int
){
    data class Error(
        val check_date: String,
        val company_id: String,
        val site_id: String
    )

    data class Row(
        val checks_count: Int,
        val site_id: Int,
        val total_checks_count: Int
    )
}