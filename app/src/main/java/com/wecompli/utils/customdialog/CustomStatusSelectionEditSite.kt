package com.wecompli.utils.customdialog

import android.app.Dialog
import android.graphics.Typeface
import android.os.Bundle
import android.view.Window
import android.widget.LinearLayout
import android.widget.TextView
import com.gsscanner.utils.AppSheardPreference
import com.gsscanner.utils.PreferenceConstant
import com.wecompli.R
import com.wecompli.screens.LoginActivity
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.AddSiteFragment
import com.wecompli.screens.fragment.AddUserFragment
import com.wecompli.screens.fragment.EditSiteFragment
import com.wecompli.screens.fragment.UserEditFragment
import com.wecompli.utils.customfont.CustomTypeface
import kotlinx.android.synthetic.main.activity_main.*


class CustomStatusSelectionEditSite(val mainActivity: MainActivity, val userEditfragment: EditSiteFragment): Dialog(mainActivity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCanceledOnTouchOutside(true)
        setContentView(R.layout.status_popup_layout)
         val typefaceBold = Typeface.createFromAsset(mainActivity.getAssets(), "fonts/Rajdhani-Bold.ttf")
         val typefaceSemiBold = Typeface.createFromAsset(mainActivity.getAssets(), "fonts/Rajdhani-SemiBold.ttf")
         val tvactive: TextView =findViewById(R.id.tvactive)
        val tvinactive: TextView =findViewById(R.id.tvinactive)
        val tv_selectstatus:TextView=findViewById(R.id.tv_selectstatus)
        tv_selectstatus.typeface= CustomTypeface.getRajdhaniBold(mainActivity)
         val llactive:LinearLayout=findViewById(R.id.llactive)
         val ll_inactive:LinearLayout=findViewById(R.id.ll_inactive)
        tvactive.typeface= CustomTypeface.getRajdhaniRegular(mainActivity)
        tvinactive.typeface= CustomTypeface.getRajdhaniRegular(mainActivity)
        llactive.setOnClickListener {
            dismiss()
            userEditfragment.changeActiveStatus()
        }
        ll_inactive.setOnClickListener {
            dismiss()
            userEditfragment.changeInActiveStatus()
        }

    }
}