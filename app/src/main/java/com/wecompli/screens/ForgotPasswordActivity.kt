package com.wecompli.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.wecompli.R
import com.wecompli.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {
    var forgotpasswordBinding: ActivityForgotPasswordBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        forgotpasswordBinding=DataBindingUtil.setContentView(this,R.layout.activity_forgot_password)

    }
}