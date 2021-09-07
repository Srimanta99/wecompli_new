package com.wecompli.utils.customdialog

import android.app.Dialog
import android.graphics.Typeface
import android.os.Bundle
import android.view.Window
import android.widget.TextView
import com.gsscanner.utils.AppSheardPreference
import com.gsscanner.utils.PreferenceConstant
import com.wecompli.R
import com.wecompli.screens.LoginActivity


class CustomLanguageDialog(val loginActivity: LoginActivity): Dialog(loginActivity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCanceledOnTouchOutside(true)
        setContentView(R.layout.popup_language)
        val typefaceBold = Typeface.createFromAsset(loginActivity.getAssets(), "fonts/Rajdhani-Bold.ttf")
        val typefaceSemiBold = Typeface.createFromAsset(loginActivity.getAssets(), "fonts/Rajdhani-SemiBold.ttf")
        val tvDutch: TextView =findViewById(R.id.tv_douch)
        tvDutch.typeface=typefaceSemiBold
        val  tv_selectlanguage:TextView=findViewById(R.id.tv_selectlanguage)
        tv_selectlanguage.typeface=typefaceBold
        tvDutch.setOnClickListener {
            loginActivity.refreshActivityWIthnewLanguage("nl")
            AppSheardPreference(loginActivity).putValueInPreference(PreferenceConstant.language, "nl")
        }
        val tvGarman:TextView=findViewById(R.id.tv_garman)
        tvGarman.typeface=typefaceSemiBold
        tvGarman.setOnClickListener {
            loginActivity.refreshActivityWIthnewLanguage("de")
            AppSheardPreference(loginActivity).putValueInPreference(PreferenceConstant.language, "de")
        }
        val tvEnglish:TextView=findViewById(R.id.tv_english)
        tvEnglish.typeface=typefaceSemiBold
        tvEnglish.setOnClickListener {
            loginActivity.refreshActivityWIthnewLanguage("en")
            AppSheardPreference(loginActivity).putValueInPreference(PreferenceConstant.language, "en")
        }
        val tvFrench:TextView=findViewById(R.id.tv_french)
        tvFrench.typeface=typefaceSemiBold
        tvFrench.setOnClickListener {
            loginActivity.refreshActivityWIthnewLanguage("fr")
            AppSheardPreference(loginActivity).putValueInPreference(PreferenceConstant.language, "fr")
        }
    }
}