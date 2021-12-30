package com.wecompli.utils.alert

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import com.gsscanner.utils.AppSheardPreference
import com.gsscanner.utils.PreferenceConstant
import com.wecompli.R
import com.wecompli.screens.LoginActivity
import com.wecompli.screens.MainActivity
import com.wecompli.screens.fragment.CheckSubmitFragment
import com.wecompli.utils.customfont.CustomTypeface


class CustomAlert {
    companion object{
        fun showYesNoAlert(activity: MainActivity, message: String) {
            // var deviceResolution:DeviceResolution?=null
            val alertDialog = Dialog(activity, R.style.Transparent)
            /*alertDialog.setTitle(activity.resources.getString(R.string.app_name))
            alertDialog.setMessage(message)*/
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            val view: View = LayoutInflater.from(activity).inflate(
                R.layout.alert_layout_yesno,
                null
            )
            alertDialog.setContentView(view)
            alertDialog.setCancelable(false)
            val tv_message: TextView = view.findViewById(R.id.tv_message)
            val tv_ok: TextView = view.findViewById(R.id.tv_ok)
            val tv_no: TextView = view.findViewById(R.id.tv_no)
            tv_ok.typeface = CustomTypeface.getRajdhaniMedium(activity)
            tv_no.typeface = CustomTypeface.getRajdhaniMedium(activity)
            tv_message.typeface = CustomTypeface.getRajdhaniRegular(activity)
            tv_ok.setOnClickListener {
                alertDialog.dismiss()
                AppSheardPreference(activity).putBooleanInPreference(
                    PreferenceConstant.rememberMe,
                    false
                )
                val i = Intent(activity, LoginActivity::class.java)
// set the new task and clear flags
                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                activity.startActivity(i)
                //  activity.homeOnClick!!.logoutyes()
                // activity.alertyesfuncation();
                // activity.calllogoutdeleteusertoken()
            }
            tv_no.setOnClickListener {
                alertDialog.dismiss()

            }
            tv_message.setText(message)
            alertDialog.show()
            /*alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
            alertDialog.show()*/
        }

        fun showalert(activity: Activity, message: String) {
            //  var deviceResolution:DeviceResolution?=null
            val alertDialog = Dialog(activity, R.style.Transparent)
           // */alertDialog.setTitle(activity.resources.getString(R.string.app_name))
          //  alertDialog.setMessage(message)*//*
                    alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            val view: View = LayoutInflater.from(activity).inflate(R.layout.alert_layout, null)
            alertDialog.setContentView(view)
            alertDialog.setCancelable(false)
            val tv_message: TextView = view.findViewById(R.id.tv_message)
            val btn_ok: TextView = view.findViewById(R.id.btn_ok)
            btn_ok.typeface = CustomTypeface.getRajdhaniMedium(activity)
            //tv_message.typeface = CustomTypeface.getWhitniBold(activity)
            btn_ok.setOnClickListener {
                alertDialog.dismiss()
            }
            tv_message.setText(message)
            alertDialog.show()
            //*//*alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
            //DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
            //alertDialog.show()*//*
        }
        fun showaInternetlert(activity: Activity, message: String) {
            //  var deviceResolution:DeviceResolution?=null
            val alertDialog = Dialog(activity, R.style.Transparent)
            // */alertDialog.setTitle(activity.resources.getString(R.string.app_name))
            //  alertDialog.setMessage(message)*//*
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            val view: View = LayoutInflater.from(activity).inflate(R.layout.alert_layout, null)
            alertDialog.setContentView(view)
            alertDialog.setCancelable(false)
            val tv_message: TextView = view.findViewById(R.id.tv_message)
            val btn_ok: TextView = view.findViewById(R.id.btn_ok)
            btn_ok.typeface = CustomTypeface.getRajdhaniMedium(activity)
            //tv_message.typeface = CustomTypeface.getWhitniBold(activity)
            btn_ok.setOnClickListener {
                alertDialog.dismiss()
                activity.finish()
            }
            tv_message.setText(message)
            alertDialog.show()
            //*//*alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
            //DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
            //alertDialog.show()*//*
        }
         fun isNetworkAvailable(activity:Activity): Boolean {
            val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

        fun showalertforImageSelectionLogSubmitt(activity: MainActivity, checkSubmitFragment: CheckSubmitFragment) {
            val alertDialog = Dialog(activity, R.style.Transparent)
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            val view: View = LayoutInflater.from(activity).inflate(R.layout.alert_custom_imageselection, null)
            alertDialog.setContentView(view)
            alertDialog.setCancelable(false)
            val tv_message: TextView = view.findViewById(R.id.tv_message)
            val btn_gallery: TextView = view.findViewById(R.id.tv_gallery)
            val btn_camera: TextView =view.findViewById(R.id.tv_camera)
            val btn_cancel: TextView =view.findViewById(R.id.tv_cancel)
            btn_camera.typeface=CustomTypeface.getRajdhaniBold(activity)
            btn_cancel.typeface=CustomTypeface.getRajdhaniBold(activity)
            btn_gallery.typeface = CustomTypeface.getRajdhaniBold(activity)
            tv_message.typeface =CustomTypeface.getRajdhaniBold(activity)
            btn_gallery.setOnClickListener {
                alertDialog.dismiss()
                checkSubmitFragment.chooseFromgallery()
            }
            btn_camera.setOnClickListener {
                alertDialog.dismiss()
                checkSubmitFragment.chooseimagrfromcamera()
            }
            btn_cancel.setOnClickListener {
                alertDialog.dismiss()
            }
            alertDialog.show()

        }
    }


   /* companion object{

        fun showalert(activity: Activity, message: String) {
            //  var deviceResolution:DeviceResolution?=null
            val alertDialog = Dialog(activity, R.style.Transparent)
            *//*alertDialog.setTitle(activity.resources.getString(R.string.app_name))
            alertDialog.setMessage(message)*//*
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            val view: View = LayoutInflater.from(activity).inflate(R.layout.alert_layout, null)
            alertDialog.setContentView(view)
            alertDialog.setCancelable(false)
            val tv_message: TextView = view.findViewById(R.id.tv_message)
            val btn_ok: TextView = view.findViewById(R.id.btn_ok)
            btn_ok.typeface = CustomTypeface.getRajdhaniMedium(activity)
            tv_message.typeface = CustomTypeface.getWhitniBold(activity)
            btn_ok.setOnClickListener {
                alertDialog.dismiss()
            }
            tv_message.setText(message)
            alertDialog.show()
            *//*alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
            alertDialog.show()*//*
        }

        fun showalerttoHome(activity: Activity, message: String) {
            //  var deviceResolution:DeviceResolution?=null
            val alertDialog = Dialog(activity, R.style.Transparent)
            *//*alertDialog.setTitle(activity.resources.getString(R.string.app_name))
            alertDialog.setMessage(message)*//*
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            val view: View = LayoutInflater.from(activity).inflate(R.layout.alert_layout, null)
            alertDialog.setContentView(view)
            alertDialog.setCancelable(false)
            val tv_message: TextView = view.findViewById(R.id.tv_message)
            val btn_ok: TextView = view.findViewById(R.id.btn_ok)
            btn_ok.typeface = CustomTypeface.getRajdhaniMedium(activity)
            tv_message.typeface = CustomTypeface.getWhitniBold(activity)
            btn_ok.setOnClickListener {
                alertDialog.dismiss()

            }
            tv_message.setText(message)
            alertDialog.show()
            *//*alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
            alertDialog.show()*//*
        }

        fun showalertForUnAuthorized(activity: Activity, message: String) {
            //  var deviceResolution:DeviceResolution?=null
            val alertDialog = Dialog(activity, R.style.Transparent)
            *//*alertDialog.setTitle(activity.resources.getString(R.string.app_name))
            alertDialog.setMessage(message)*//*
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            val view: View = LayoutInflater.from(activity).inflate(R.layout.alert_layout, null)
            alertDialog.setContentView(view)
            alertDialog.setCancelable(false)
            val tv_message: TextView = view.findViewById(R.id.tv_message)
            val btn_ok: TextView = view.findViewById(R.id.btn_ok)
            btn_ok.typeface = CustomTypeface.getRajdhaniMedium(activity)
            tv_message.typeface = CustomTypeface.getRajdhaniMedium(activity)
            btn_ok.setOnClickListener {
                alertDialog.dismiss()
                val i = Intent(activity, LoginActivity::class.java)
                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                activity.startActivity(i)

            }
            tv_message.setText(message)
            alertDialog.show()
            *//*alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
            alertDialog.show()*//*
        }
        fun showYesNoAlert( activity: HomeActivity, message: String) {
            // var deviceResolution:DeviceResolution?=null
            val alertDialog = Dialog(activity, R.style.Transparent)
            *//*alertDialog.setTitle(activity.resources.getString(R.string.app_name))
            alertDialog.setMessage(message)*//*
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            val view: View = LayoutInflater.from(activity).inflate(R.layout.alert_layout_yesno, null)
            alertDialog.setContentView(view)
            alertDialog.setCancelable(false)
            val tv_message: TextView = view.findViewById(R.id.tv_message)
            val tv_ok: TextView = view.findViewById(R.id.tv_ok)
            val tv_no: TextView = view.findViewById(R.id.tv_no)
            tv_ok.typeface = CustomTypeface.getRajdhaniMedium(activity)
            tv_no.typeface = CustomTypeface.getRajdhaniMedium(activity)
            tv_message.typeface = CustomTypeface.getRajdhaniRegular(activity)
            tv_ok.setOnClickListener {
                alertDialog.dismiss()
                AppSheardPreference(activity).setvalue_in_preference(PreferenceConstent.iS_LOGIN,"0")
                val i = Intent(activity, LoginActivity::class.java)
// set the new task and clear flags
                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                activity.startActivity(i)
                //  activity.homeOnClick!!.logoutyes()
                // activity.alertyesfuncation();
                // activity.calllogoutdeleteusertoken()
            }
            tv_no.setOnClickListener {
                alertDialog.dismiss()

            }
            tv_message.setText(message)
            alertDialog.show()
            *//*alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
            alertDialog.show()*//*
        }
        fun showalertForRemoveUser(activity: Activity) {
            //  var deviceResolution:DeviceResolution?=null
            val alertDialog = Dialog(activity, R.style.Transparent)
            *//*alertDialog.setTitle(activity.resources.getString(R.string.app_name))
            alertDialog.setMessage(message)*//*
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            val view: View = LayoutInflater.from(activity).inflate(R.layout.alert_layout_remove_user, null)
            alertDialog.setContentView(view)
            alertDialog.setCancelable(false)
            val tv_message: TextView = view.findViewById(R.id.tv_message)
            val btn_ok: TextView = view.findViewById(R.id.btn_ok)
            val tvnote:TextView=view.findViewById(R.id.tv_note)
            val close:TextView=view.findViewById(R.id.close)
            btn_ok.typeface = CustomTypeface.getRajdhaniMedium(activity)
            btn_ok.setText("Continue")
            tv_message.typeface = CustomTypeface.getRajdhaniMedium(activity)
            tvnote.typeface = CustomTypeface.getRajdhaniMedium(activity)
            val textnote="<font color=#FE0100>Note: </font> <font color=#1E3F6C>  In order to downgrade your package, you have to remove sites and users from your list to match with your selected package.</font>";
            tvnote!!.setText(Html.fromHtml(textnote))
            btn_ok.setOnClickListener {
                alertDialog.dismiss()
                val intent = Intent(activity, RemoveSiteUserActivity::class.java)
                activity.startActivity(intent)

            }
            close.setOnClickListener {
                alertDialog.dismiss()
                }
            //  tv_message.setText(message)


            alertDialog.show()
            *//*alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
            alertDialog.show()*//*
        }
        fun showalertForConfirmRemoveUser(activity: RemoveSiteUserActivity) {
            //  var deviceResolution:DeviceResolution?=null
            val alertDialog = Dialog(activity, R.style.Transparent)
            *//*alertDialog.setTitle(activity.resources.getString(R.string.app_name))
            alertDialog.setMessage(message)*//*
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            val view: View = LayoutInflater.from(activity).inflate(R.layout.alert_layout_remove_user, null)
            alertDialog.setContentView(view)
            alertDialog.setCancelable(false)
            val tv_message: TextView = view.findViewById(R.id.tv_message)
            val btn_ok: TextView = view.findViewById(R.id.btn_ok)
            val tvnote:TextView=view.findViewById(R.id.tv_note)
            val close:TextView=view.findViewById(R.id.close)
            btn_ok.typeface = CustomTypeface.getRajdhaniMedium(activity)

            tv_message.typeface = CustomTypeface.getRajdhaniMedium(activity)
            tvnote.typeface = CustomTypeface.getRajdhaniMedium(activity)
            val textnote="<font color=#FE0100>Note: </font> <font color=#1E3F6C>  Data will be deleted permanently.</font>";
            tvnote!!.setText(Html.fromHtml(textnote))
            btn_ok.setOnClickListener {
                alertDialog.dismiss()
                activity.callApiforUserSiteRemove()

            }
            close.setOnClickListener {
                alertDialog.dismiss()
            }
            //  tv_message.setText(message)


            alertDialog.show()
            *//*alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
            alertDialog.show()*//*
        }*/
  //  }







}