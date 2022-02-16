package com.wecompli.model

data class FaultDetailsResponse(
    val error: Error,
    val message: String,
    val process: Boolean,
    val rows: Rows
){
    data class FaultFile(
        val file_name: String
    )


    data class Error(
        val company_id: String,
        val fault_id: String,
        val site_id: String
    )
    data class FaultRepairHistoryLog(
        val created_at: String,
        val remarks: String,
        val status_message: String
    )

    data class Rows(
        val check_name: String,
        val created_at: String,
        val fault_description: String,
        val fault_entry_date: String,
        val fault_file: List<FaultFile>,
        val fault_repair_history_log: List<FaultRepairHistoryLog>,
        val id: Int,
        val status_id: Int
    )
}