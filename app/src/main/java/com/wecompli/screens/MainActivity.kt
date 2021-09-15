package com.wecompli.screens

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.gsscanner.utils.AppSheardPreference
import com.gsscanner.utils.PreferenceConstant
import com.wecompli.R
import com.wecompli.databinding.ActivityMainBinding

import com.wecompli.handler.MainHandler
import com.wecompli.screens.fragment.DashBoardFragment
import com.wecompli.screens.fragment.RolesListFragment
import com.wecompli.screens.fragment.SiteListFragment
import com.wecompli.screens.fragment.UserListFragment
import com.wecompli.utils.alert.CustomAlert
import com.wecompli.viewmodel.MainViewModel
import org.jsoup.Jsoup
import java.io.IOException

class MainActivity : AppCompatActivity(),MainHandler {
    public var activityMainBinding: ActivityMainBinding?=null
    var viewmodel:MainViewModel?=null
    internal var currentVersion: String?=""
    internal var dialog: Dialog?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewmodel=ViewModelProviders.of(this).get(MainViewModel::class.java)
        activityMainBinding!!.mainmodel=viewmodel
        viewmodel!!.mainHandler=this

        activityMainBinding!!.drawerlayout!!.setScrimColor(Color.TRANSPARENT)
        var actionBarDrawerToggle: ActionBarDrawerToggle =
                object : ActionBarDrawerToggle(this, activityMainBinding!!.drawerlayout, R.string.start, R.string.cancel) {
                    private val scaleFactor = 4f
                    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                        super.onDrawerSlide(drawerView, slideOffset)
                        val slideX = drawerView.width * slideOffset
                        activityMainBinding!!.mainview!!.setTranslationX(slideX)
                        activityMainBinding!!.mainview!!.setScaleX(1 - slideOffset / scaleFactor)
                        activityMainBinding!!.mainview!!.setScaleY(1 - slideOffset / scaleFactor)
                        //  drawerView!!.setBackgroundColor(resources.getColor(R.color.login_bg_header))
                        // homeViewBind!!.mainView!!.setBackgroundColor(resources.getColor(R.color.login_bg_header))
                    }

                }
        activityMainBinding!!.drawerlayout!!.addDrawerListener(actionBarDrawerToggle)
        openFragment(DashBoardFragment())
         val userdata=AppSheardPreference(this).getUser(PreferenceConstant.userData)
        activityMainBinding!!.tvUsername.setText(userdata.full_name)
        activityMainBinding!!.tvCompanyname.setText(userdata.company_name)

       // getCurrentVersion()
    }

    public fun openFragment(fragment: Fragment) {
        activityMainBinding!!.drawerlayout!!.closeDrawer(Gravity.LEFT)
        val transaction =supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content_frame, fragment)
        transaction.addToBackStack("")
        transaction.commit()

    }

    override fun opendrawer() {
       // toast("gefkg")
        activityMainBinding!!.drawerlayout!!.openDrawer(Gravity.LEFT)
    }


    override fun logout() {
        CustomAlert.showYesNoAlert(this,"Are you want to Logout?")

    }
    override fun opdashboard() {
     openFragment(DashBoardFragment())
    }

    override fun openSitelist() {
        openFragment(SiteListFragment())
    }

    override fun openUserList() {
        openFragment(UserListFragment())
    }

    override fun openCheckLists() {
        TODO("Not yet implemented")
    }

    override fun openFaults() {
        TODO("Not yet implemented")
    }

    override fun incidents() {
        TODO("Not yet implemented")
    }

    override fun documents() {
        TODO("Not yet implemented")
    }

    override fun email() {
        TODO("Not yet implemented")
    }

    private fun getCurrentVersion() {
        val pm = this.packageManager
        var pInfo: PackageInfo? = null

        try {
            pInfo = pm.getPackageInfo(this.packageName, 0)
        } catch (e1: PackageManager.NameNotFoundException) {
            // TODO Auto-generated catch block
            e1.printStackTrace()
        }

        currentVersion = pInfo!!.versionName
        GetVersionCode().execute()

    }

    internal inner class GetVersionCode : AsyncTask<Void, String, String>() {

        override fun doInBackground(vararg voids: Void): String? {

            var newVersion: String? = null

            try {
                val document =
                        Jsoup.connect("https://play.google.com/store/apps/details?id=" + this@MainActivity.packageName + "&hl=en")
                                //  Document document = Jsoup.connect("https://play.google.com/store/apps/details?id=" + "com.app.astrolab"  + "&hl=en")
                                .timeout(30000)
                                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                                .referrer("http://www.google.com")
                                .get()
                if (document != null) {
                    val element = document!!.getElementsContainingOwnText("Current Version")
                    for (ele in element) {
                        if (ele.siblingElements() != null) {
                            val sibElemets = ele.siblingElements()
                            for (sibElemet in sibElemets) {
                                newVersion = sibElemet.text()
                            }
                        }
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return newVersion

        }


        override fun onPostExecute(onlineVersion: String?) {

            super.onPostExecute(onlineVersion)

            if (onlineVersion != null && !onlineVersion.isEmpty()) {

                if (java.lang.Float.valueOf(currentVersion!!) < java.lang.Float.valueOf(onlineVersion)) {
                    //show anything
                    showUpdateDialog()
                }

            }

            // Log.d("update", "Current version " + currentVersion + "playstore version " + onlineVersion);

        }
    }
    private fun showUpdateDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(resources.getString(R.string.new_version_avalible))
        builder.setPositiveButton(resources.getText(R.string.update)) { dialog, which ->
            /* startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse
                            ("market://details?id=com.wecompli")));*/
            val appPackageName = packageName // getPackageName() from Context or Activity object
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
            } catch (anfe: android.content.ActivityNotFoundException) {
                startActivity(
                        Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                        )
                )
            }

            dialog.dismiss()
        }

        builder.setNegativeButton(
                resources.getString(R.string.cancel),
                object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, which: Int) {
                        dialog.dismiss()
                    }
                })

        builder.setCancelable(true)
        dialog = builder.show()
        dialog!!.setCancelable(false)
    }
}