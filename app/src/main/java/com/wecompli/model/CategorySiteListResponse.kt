package com.wecompli.model

data class CategorySiteListResponse(
    val error: Error,
    val message: String,
    val process: Boolean,
    val rows: ArrayList<Row>){
    data class Row(
        val category_id: Int,
        val created_at: String,
        val id: Int,
        val site_name: String,
        val status_id: Int,
        var isselect: Boolean
    )
    data class Error(val company_id: String)
}
