package com.wecompli.viewmodel

import ApiInterface
import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.gsscanner.utils.AppSheardPreference
import com.gsscanner.utils.PreferenceConstant
import com.sculptee.utils.customprogress.CustomProgressDialog
import com.wecompli.handler.LoginHandler
import com.wecompli.model.LoginResponseModel
import com.wecompli.network.Retrofit
import com.wecompli.screens.LoginActivity
import com.wecompli.screens.MainActivity
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel:ViewModel() {
    var loginHandler:LoginHandler?=null

    fun onLoginSubmit(view: View){
     var isBlabk:Boolean=loginHandler!!.checkblankValidation()
        if (isBlabk){
            loginHandler!!.onLoginClick()
        }
    }

    fun togglePassword(view: View){
        loginHandler!!.showHidePassword()
    }

    fun checkRememberme(view: View){
        loginHandler!!.rememberme()
    }

    fun openForgotpassword(view: View){
        loginHandler!!.openForgotpassword()
    }

    fun languagepopupShow(view: View){
        loginHandler!!.displayPopup()
    }

  public fun callLoginApi(email: String, pass: String, loginActivity: LoginActivity) {
      val  customProgress: CustomProgressDialog = CustomProgressDialog().getInstance()
      customProgress.showProgress(loginActivity, "Please Wait..", false)
      val apiInterface= Retrofit.retrofitInstance?.create(ApiInterface::class.java)
      try {
          val paramObject = JSONObject()
          paramObject.put("user_email_ID", email)
          paramObject.put("password", pass)
          var obj: JSONObject = paramObject
          var jsonParser: JsonParser = JsonParser()
          var gsonObject: JsonObject = jsonParser.parse(obj.toString()) as JsonObject;
          val loginApiCall = apiInterface.callLoginApi(gsonObject)
          loginApiCall.enqueue(object : Callback<LoginResponseModel> {
              override fun onResponse(
                  call: Call<LoginResponseModel>,
                  response: Response<LoginResponseModel>
              ) {
                  customProgress.hideProgress()
                  if (response.isSuccessful) {
                      if (response.body()!!.process) {
                          //AppSheardPreference(loginActivity).putBooleanInPreference()
                          AppSheardPreference(loginActivity).setvalueforUser(PreferenceConstant.userData,response!!.body()!!)
                          loginActivity.startActivity(Intent(loginActivity, MainActivity::class.java))
                          loginActivity.finish()
                      }
                  }
              }

              override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
                  customProgress.hideProgress()
              }
          })

      }catch (e: Exception)
      {
          e.printStackTrace()
      }

    }
}