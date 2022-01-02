package com.wecompli.utils.alert

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialog
import com.wecompli.R

import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.CheckSubmitFragment
import com.wecompli.utils.customfont.CustomTypeface
import kotlinx.android.synthetic.main.fragment_check_submit.*

import kotlin.math.roundToInt

class TapToSignDialogForLogSubmit(val mainActivity: MainActivity,val checkSubmitFragment: CheckSubmitFragment) :AppCompatDialog(mainActivity), View.OnClickListener {

    var   tv_done: TextView?=null
    var   tv_signhere:TextView?=null
     var tv_cancel:TextView?=null
     var tv_claer:TextView?=null
     var signDrawView: DrawingView?=null
    override fun onClick(p0: View?) {
        when (p0!!.getId()) {
            R.id.tv_done -> {
                //    signDrawView.clear();
                checkSubmitFragment.imagesignAvaliable=true
                val bitmap = signDrawView!!.bitmap
                checkSubmitFragment.imgtaptosign!!.setImageBitmap(bitmap)
                  // checkTapToSignActivity.tapToSignViewBind!!.img_sign!!.setImageBitmap(bitmap)
                    //  if (bitmap!=null || !bitmap.equals("")) {

                dismiss()
            }
            R.id.tv_signhere -> {
            }
            R.id.tv_cancel -> {
                dismiss()
                checkSubmitFragment.imagesignAvaliable=true
                //checkTapToSignActivity.imagesignAvaliable = true
            }
            R.id.tv_claer -> signDrawView!!.clear()
        }//  Bitmap bmp=signDrawView.getBitmap();
        //  Bitmap bmp=signDrawView.getBitmap();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view = mainActivity.getLayoutInflater().inflate(R.layout.tap_to_sign_dialog_layout, null)
        setContentView(view)
        setCanceledOnTouchOutside(true)
        setCancelable(true)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val wmlp = window!!.getAttributes()
        wmlp.width = mainActivity.resources.getDimension(R.dimen._250sdp).roundToInt()
        wmlp.height =  mainActivity.resources.getDimension(R.dimen._220sdp).roundToInt()
        window!!.setAttributes(wmlp)
        initView(view)
    }

    private fun initView(view: View) {
        tv_done = view.findViewById(R.id.tv_done)
        tv_signhere = view.findViewById(R.id.tv_signhere)
        tv_cancel = view.findViewById(R.id.tv_cancel)
        tv_claer = view.findViewById(R.id.tv_claer)
        signDrawView = view.findViewById(R.id.sign)
        tv_done!!.setOnClickListener(this)
        tv_signhere!!.setOnClickListener(this)
        tv_cancel!!.setOnClickListener(this)
        tv_claer!!.setOnClickListener(this)

        //settypeface
        tv_done!!.setTypeface(CustomTypeface.getRajdhaniBold(mainActivity))
        tv_signhere!!.setTypeface(CustomTypeface.getRajdhaniBold(mainActivity))
        tv_cancel!!.setTypeface(CustomTypeface.getRajdhaniBold(mainActivity))
        tv_claer!!.setTypeface(CustomTypeface.getRajdhaniBold(mainActivity))

        //  signDrawView.setImageBitmap();


    }

}