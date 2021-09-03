package com.wecompli.screens

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.gsscanner.utils.AppSheardPreference
import com.gsscanner.utils.PreferenceConstant
import com.wecompli.R

class SplashActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(Runnable {
            if(AppSheardPreference(this).getBoolValueFromPreference(PreferenceConstant.rememberMe)==true){
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        },3000)
    }
}