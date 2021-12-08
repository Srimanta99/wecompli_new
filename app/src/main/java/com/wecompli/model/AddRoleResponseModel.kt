package com.wecompli.model

data class AddRoleResponseModel( val process:Boolean,val message:String,val error:Error) {

    data class Error(
        val company_id: String,
        val role_name: String,
        val module_function_id: String,
        val status_id: String
    )
}