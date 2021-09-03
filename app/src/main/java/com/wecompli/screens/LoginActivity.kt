package com.wecompli.screens


import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.text.method.SingleLineTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.gsscanner.utils.AppSheardPreference
import com.gsscanner.utils.PreferenceConstant
import com.gsscanner.utils.toast
import com.wecompli.R
import com.wecompli.databinding.ActivityLoginBinding
import com.wecompli.handler.LoginHandler
import com.wecompli.utils.ContextUtils
import com.wecompli.utils.customdialog.CustomLanguageDialog
import com.wecompli.viewmodel.LoginViewModel
import java.util.*
class LoginActivity:AppCompatActivity(), LoginHandler {
    var loginBinding: ActivityLoginBinding?=null
    var viewModel:LoginViewModel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding=DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel=ViewModelProviders.of(this).get(LoginViewModel::class.java)
        loginBinding!!.loginModel=viewModel
        viewModel!!.loginHandler=this
        if(AppSheardPreference(this).getValueFromPreference(PreferenceConstant.language).equals("en"))
            loginBinding!!.tvSelectlanguage.setText(resources.getString(R.string.english))
        else if(AppSheardPreference(this).getValueFromPreference(PreferenceConstant.language).equals("fr"))
            loginBinding!!.tvSelectlanguage.setText(resources.getString(R.string.french))
        else if(AppSheardPreference(this).getValueFromPreference(PreferenceConstant.language).equals("nl"))
            loginBinding!!.tvSelectlanguage.setText(resources.getString(R.string.douch))
        else if(AppSheardPreference(this).getValueFromPreference(PreferenceConstant.language).equals("de"))
            loginBinding!!.tvSelectlanguage.setText(resources.getString(R.string.germen))

    }

    override fun onLoginClick() {
     //  toast("login sucess");
        viewModel!!.callLoginApi(loginBinding!!.etEmail.text.toString(), loginBinding!!.etPass.text.toString(), this)

    }

    override fun checkblankValidation(): Boolean {
        if (loginBinding!!.etEmail.text.toString().equals("")){
            toast("enter email")
            return false
        }
        if(loginBinding!!.etPass.text.toString().equals("")){
            toast("enter password")
             return false
        }
        return true
    }

    override fun showHidePassword() {
        if (loginBinding!!.etPass.getTransformationMethod().javaClass.getSimpleName().equals("PasswordTransformationMethod")) {
            loginBinding!!.showPassBtn!!.setImageResource(R.drawable.hide);
            // loginViewBind.et_pass.setCompoundDrawablesWithIntrinsicBounds(R.drawable.password_show_hide, 0, 0, 0);
            loginBinding!!.etPass.setTransformationMethod(SingleLineTransformationMethod())
        } else {
            loginBinding!!.showPassBtn!!.setImageResource(R.drawable.view);
            // loginViewBind.et_pass.setCompoundDrawablesWithIntrinsicBounds(R.drawable.hide, 0, 0, 0);
            loginBinding!!.etPass.setTransformationMethod(PasswordTransformationMethod())
        }
        loginBinding!!.etPass.setSelection(loginBinding!!.etPass.text.length)
    }

    override fun rememberme() {
        if(loginBinding!!.chkRemember.isChecked)
            AppSheardPreference(this).putBooleanInPreference(PreferenceConstant.rememberMe, true)
        else
            AppSheardPreference(this).putBooleanInPreference(PreferenceConstant.rememberMe, false)
    }

    override fun openForgotpassword() {
        startActivity(Intent(this, ForgotPasswordActivity::class.java))
    }

    override fun displayPopup() {
        val customLanguageDialog=CustomLanguageDialog(this)
        customLanguageDialog.show()
       /* var popupWindow:PopupWindow?=null
        var layoutInflater:LayoutInflater= (this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
        val view:View=layoutInflater!!.inflate(R.layout.popup_language, null)
        popupWindow= PopupWindow(view, (resources.getDimension(R.dimen._260sdp)).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT, true)
        popupWindow.showAsDropDown(loginBinding!!.tvSelectlanguage)
        val tvDutch:TextView=view.findViewById(R.id.tv_douch)
        tvDutch.setOnClickListener {
            refreshActivityWIthnewLanguage("nl")
            AppSheardPreference(this).putValueInPreference(PreferenceConstant.language,"nl")
        }
        val tvGarman:TextView=view.findViewById(R.id.tv_garman)
        tvGarman.setOnClickListener {
            refreshActivityWIthnewLanguage("de")
            AppSheardPreference(this).putValueInPreference(PreferenceConstant.language,"de")
        }
        val tvEnglish:TextView=view.findViewById(R.id.tv_english)
        tvEnglish.setOnClickListener {
            refreshActivityWIthnewLanguage("en")
            AppSheardPreference(this).putValueInPreference(PreferenceConstant.language,"en")
        }
        val tvFrench:TextView=view.findViewById(R.id.tv_french)
        tvFrench.setOnClickListener {
            refreshActivityWIthnewLanguage("fr")
            AppSheardPreference(this).putValueInPreference(PreferenceConstant.language,"fr")
        }*/
    }

    public fun refreshActivityWIthnewLanguage(lang: String){
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val resources: Resources = getResources()
        val config: Configuration = resources.getConfiguration()
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.getDisplayMetrics())
      //  ContextUtils.updateLocale(this, "")
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
}