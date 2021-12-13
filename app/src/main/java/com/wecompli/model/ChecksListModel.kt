package com.wecompli.model

data class ChecksListModel(
    val error: Error,
    val message: String,
    val process: Boolean,
    val rows: ArrayList<Row>
){
    data class Row(
        val check_name: String,
        val check_note: String,
        val check_type_id: Int,
        val check_type_name: String,
        val created_at: String,
        val has_qrcode: String,
        val id: Int,
        val qrcode: String,
        val status_id: Int
    )
    data class Error(
        val category_id: String,
        val company_id: String,
        val site_id: String
    )
}