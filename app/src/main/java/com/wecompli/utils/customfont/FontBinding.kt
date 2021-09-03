package com.wecompli.utils.customfont

import android.widget.TextView

import androidx.databinding.BindingAdapter


class FontBinding {
    companion object {
        @JvmStatic
        @BindingAdapter("bind:font")
        fun setFont(textView: TextView, fontName: String?) {
            textView.typeface = CustomFontFamily.getInstance()!!.getFont(fontName!!)
        }
    }


}