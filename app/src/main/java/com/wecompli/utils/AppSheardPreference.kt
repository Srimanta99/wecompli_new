package com.gsscanner.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson
import com.wecompli.model.LoginResponseModel

class AppSheardPreference(context: Context) {
    var sharedPreferences: SharedPreferences?=null
    var myEdit:SharedPreferences.Editor?=null

    init {
        if (sharedPreferences==null){
            sharedPreferences=context.getSharedPreferences("Gsscanner",MODE_PRIVATE)
        }
        myEdit=sharedPreferences!!.edit()
    }

    fun putBooleanInPreference(key:String,value:Boolean){
        myEdit!!.putBoolean(key,value)
        myEdit!!.commit()
        //myEdit.apply()

    }
    fun putValueInPreference(key:String,value:String){
        myEdit!!.putString(key,value)
        myEdit!!.commit()
        //myEdit.apply()

    }


    fun  getValueFromPreference(key:String): String?  {
        var str=sharedPreferences!!.getString(key,"")
        return str
    }

    fun  getBoolValueFromPreference(key:String): Boolean?  {
        var bool=sharedPreferences!!.getBoolean(key,false)
        return bool
    }

    fun setvalueforUser(key:String,userdata :LoginResponseModel) {
        myEdit!!.putString(key, Gson().toJson(userdata))
        myEdit!!.apply()
    }

    fun getUser(key: String):LoginResponseModel{
        var sata = sharedPreferences!!.getString(key, "")
        return Gson().fromJson(sata,LoginResponseModel::class.java)
    }


}