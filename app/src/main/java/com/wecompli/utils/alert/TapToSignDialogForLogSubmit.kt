package com.wecompli.utils.alert

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.view.Window
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialog
import com.wecompli.R

import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.CheckSubmitFragment
import com.wecompli.utils.customfont.CustomTypeface
import kotlinx.android.synthetic.main.fragment_check_submit.*
import java.io.*

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

                try {
                    /*checkSubmitFragment.signimgfile = File(Environment.getExternalStorageDirectory().toString() + File.separator + "sign.jpg")
                    checkSubmitFragment!!.signimgfile!!.createNewFile()

                    //Convert bitmap to byte array
                    val bos = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 0, bos) // YOU can also save it in JPEG
                    val bitmapdata = bos.toByteArray()

                    //write the bytes in file
                    val fos = FileOutputStream(checkSubmitFragment!!.signimgfile!!)
                    fos.write(bitmapdata)
                    fos.flush()
                    fos.close()*/

                    val root = Environment.getExternalStorageDirectory().toString()
                    val myDir = File("$root/wecompli/checksubmit")
                    myDir.mkdirs()
                    /* val generator = Random()
                      var n = 100
                      n = generator.nextInt(n)*/
                    val fname ="sign"+"checksubmitsign.jpg"
                    checkSubmitFragment.signimgfile = File(myDir, fname)
                    val fo: FileOutputStream
                    if (checkSubmitFragment!!.signimgfile!!.exists())
                        checkSubmitFragment!!.signimgfile!!.delete()
                    try {
                        /* destination.createNewFile()
                         fo = FileOutputStream(destination)
                         fo.write(bytes.toByteArray())
                         fo.close()*/
                        val out = FileOutputStream(checkSubmitFragment.signimgfile)
                        bitmap!!.compress(Bitmap.CompressFormat.JPEG, 100, out)
                        out.flush()
                        out.close()


                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }catch (e: Exception)
                {
                    e.printStackTrace()
                }

                dismiss()
            }
            R.id.tv_signhere -> {

            }
            R.id.tv_cancel -> {
                dismiss()

                //checkTapToSignActivity.imagesignAvaliable = true
            }
            R.id.tv_claer ->{
                signDrawView!!.clear()
                checkSubmitFragment.signimgfile=null
            }
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