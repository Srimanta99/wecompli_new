package com.wecompli.utils

import android.app.Application
import android.content.Context
import com.wecompli.utils.customfont.CustomFontFamily

class CustomApplication:Application() {
    companion object{
         public  var context: Context? = null
    }
    var customFontFamily:CustomFontFamily?=null
    override fun onCreate() {
        super.onCreate()
        CustomApplication!!.context=this;
        customFontFamily=CustomFontFamily.getInstance();
        customFontFamily!!.addFont("rajDhaniBold", "Rajdhani-Bold.ttf")
        customFontFamily!!.addFont("rajDhaniLight", "Rajdhani-Light.ttf")
        customFontFamily!!.addFont("rajDhaniMedium", "Rajdhani-Medium.ttf")
        customFontFamily!!.addFont("rajDhaniRegular", "Rajdhani-Regular.ttf")
        customFontFamily!!.addFont("rajDhaniSemibold", "Rajdhani-SemiBold.ttf")
    }
    fun getContext(): Context? {
        return context
    }
}