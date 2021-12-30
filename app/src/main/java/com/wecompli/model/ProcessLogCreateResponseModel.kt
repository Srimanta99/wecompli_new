package com.wecompli.model

data class ProcessLogCreateResponseModel(
    val error: Error,
    val message: String,
    val process: Boolean
){
    data class Error(
        val check_id: String,
        val check_process_type: String,
        val check_type_id: String,
        val check_type_values_id: String,
        val checks_process_log_entry_date: String,
        val company_id: String,
        val process_status: String,
        val season_id: String,
        val site_id: String
    )
}