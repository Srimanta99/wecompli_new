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
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.gsscanner.utils.AppSheardPreference
import com.gsscanner.utils.PreferenceConstant
import com.wecompli.R
import com.wecompli.databinding.ActivityMainBinding

import com.wecompli.handler.MainHandler
import com.wecompli.network.NetworkUtility
import com.wecompli.screens.fragment.*
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
      //  opendahBoardFragment(FristTimeDashBoardFragment())

        opendahBoardFragment(DashBoardFragment())
         val userdata=AppSheardPreference(this).getUser(PreferenceConstant.userData)
        activityMainBinding!!.tvUsername.setText(userdata.full_name)
        activityMainBinding!!.tvCompanyname.setText(userdata.company_name)
        setSideMenuIcon()
       // getCurrentVersion()
    }

    private fun setSideMenuIcon() {
        Glide.with(this)
            .load(NetworkUtility.DASHBOARD)
            .into(activityMainBinding!!.imgDashboard)

        Glide.with(this)
            .load(NetworkUtility.SITES)
            .into(activityMainBinding!!.imgSites)

        Glide.with(this)
            .load(NetworkUtility.USERS)
            .into(activityMainBinding!!.imgUsers)

        Glide.with(this)
            .load(NetworkUtility.USERS)
            .into(activityMainBinding!!.imgUsers)


        Glide.with(this)
            .load(NetworkUtility.CHECKLISTIMAGE)
            .into(activityMainBinding!!.imgChecklist)

        Glide.with(this)
            .load(NetworkUtility.FAULTS)
            .into(activityMainBinding!!.imgFault)



        Glide.with(this)
            .load(NetworkUtility.INCIDENTS)
            .into(activityMainBinding!!.imgIncident)


        Glide.with(this)
            .load(NetworkUtility.DOCUMENTS)
            .into(activityMainBinding!!.imgDocument)

        Glide.with(this)
            .load(NetworkUtility.EMAIL)
            .into(activityMainBinding!!.imgEmail)

    }

    public fun opendahBoardFragment(fragment: Fragment) {
        activityMainBinding!!.drawerlayout!!.closeDrawer(Gravity.LEFT)
        val transaction =supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content_frame, fragment)
        transaction.commit()

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
       openFragment(ChecksListFragment())
    }

    override fun openFaults() {
        openFragment(FaultListFragment())
    }

    override fun incidents() {
        openFragment(IncidentsFragment())
    }

    override fun documents() {
        openFragment(DocumentAddFragment())
    }

    override fun email() {
        openFragment(EmailListFragment())

    }

    override fun subcription() {
        openFragment(MySubcriptionFragment())
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