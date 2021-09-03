package com.wecompli.utils.customfont

import android.graphics.Typeface
import com.wecompli.utils.CustomApplication


class CustomFontFamily {
companion object{
    var customFontFamily:CustomFontFamily?=null
    var fontMap: HashMap<String, String> = HashMap()
    var fontCache: HashMap<String, Typeface> = HashMap()
    fun getInstance(): CustomFontFamily? {
        if (customFontFamily == null) customFontFamily = CustomFontFamily()
        return customFontFamily
    }

}
    fun addFont(alias: String?, fontName: String?) {
        fontMap.put(alias!!, fontName!!)
    }

    fun getFont(alias: String): Typeface? {
        val fontFilename = fontMap[alias]
        if (fontFilename == null) {
            return null
        }
        return if (fontCache.containsKey(alias)) fontCache[alias] else {
            val typeface = Typeface.createFromAsset(CustomApplication.context!!.assets, "fonts/$fontFilename")
            fontCache[fontFilename] = typeface
            typeface
        }
    }

}