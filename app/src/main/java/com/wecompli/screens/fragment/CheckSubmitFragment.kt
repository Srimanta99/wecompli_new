package com.wecompli.screens.fragment

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.gsscanner.utils.AppSheardPreference
import com.gsscanner.utils.PreferenceConstant
import com.sculptee.utils.customprogress.CustomProgressDialog
import com.wecompli.R
import com.wecompli.databinding.FragmentCheckSubmitBinding
import com.wecompli.handler.CheckSubmitHandler
import com.wecompli.model.ProcessLogCreateResponseModel
import com.wecompli.network.NetworkUtility
import com.wecompli.screens.MainActivity
import com.wecompli.utils.alert.CustomAlert
import com.wecompli.utils.alert.TapToSignDialogForLogSubmit
import com.wecompli.viewmodel.CheckSubmitViewModel
import okhttp3.*
import org.json.JSONObject
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CheckSubmitFragment : Fragment(), CheckSubmitHandler {
    private var param1: String? = null
    private var param2: String? = null
   var date=""
    var siteid=""
    var checkname="";
    var fragmentCheckSubmitBinding:FragmentCheckSubmitBinding?=null
    var checkSubmitViewModel:CheckSubmitViewModel?=null
    var REQUEST_CAMERA = 111
    var SELECT_FILE = 112
    var position:Int = 0
    var image: String?=null
    var imageView: ImageView?=null
    internal var Imagesellposition: Int = 0
    internal var imagearraylist = ArrayList<File>()
    internal var imagearraylistpath = ArrayList<String>()
    var imagesignAvaliable=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentCheckSubmitBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_check_submit, container, false)
        checkSubmitViewModel=ViewModelProviders.of(this).get(CheckSubmitViewModel::class.java)
        fragmentCheckSubmitBinding!!.checksubmit=checkSubmitViewModel
        checkSubmitViewModel!!.checkSubmitHandler=this
        val bundle=arguments
            //catid= bundle!!.getString("category_Id")!!
        date=bundle!!.getString("selecteddate")!!
        siteid=bundle.getString("siteid")!!
        checkname=bundle!!.getString("checkname")!!
        return fragmentCheckSubmitBinding!!.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CheckSubmitFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    fun chooseFromgallery() {
        image = "gallery"
        checkpermession()
    }

    fun chooseimagrfromcamera() {
        image = "camera"
        checkpermession()
    }
    fun checkpermession(){
        if (ContextCompat.checkSelfPermission(activity as MainActivity, Manifest.permission.CAMERA) !== PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                activity as MainActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) !== PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                activity as MainActivity, arrayOf<String>(
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ), 0
            )
        } else {
            if (image == "gallery")
                galleryIntent()
            else if (image == "camera")
                takePhotoFromCamera()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data)
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data!!)
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == 0) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                if (image == "gallery")
                    galleryIntent()
                else if (image == "camera")
                    takePhotoFromCamera()
            }
        }
    }
    private fun galleryIntent() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE)
    }

    private fun takePhotoFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_CAMERA)
    }

    private fun onSelectFromGalleryResult(data: Intent?) {
        var bm: Bitmap? = null
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(
                    (activity as MainActivity).contentResolver,
                    data.data
                )
                /*  val bytes = ByteArrayOutputStream()
                  bm!!.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
                  val destination = File(Environment.getExternalStorageDirectory(), "fault_image"+ ".jpg")*/
                // val destination = File(Environment.getExternalStorageDirectory(), System.currentTimeMillis().toString() + ".jpg")

                val root = Environment.getExternalStorageDirectory().toString()
                val myDir = File("$root/wecompli/adhocfault")
                myDir.mkdirs()
                /* val generator = Random()
                  var n = 100
                  n = generator.nextInt(n)*/
                val fname =Imagesellposition.toString()+"adhoc_image.jpg"
                val file = File(myDir, fname)
                val fo: FileOutputStream
                if (file.exists())
                    file.delete()
                try {
                    /* destination.createNewFile()
                     fo = FileOutputStream(destination)
                     fo.write(bytes.toByteArray())
                     fo.close()*/
                    val out = FileOutputStream(file)
                    bm.compress(Bitmap.CompressFormat.JPEG, 100, out)
                    out.flush()
                    out.close()
                    imagearraylist.add(file)
                    imagearraylistpath.add(file.path)
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            } catch (e: IOException) {
                e.printStackTrace()
            }

        }

        if (Imagesellposition == 1) {
            fragmentCheckSubmitBinding!!.imgCam1!!.setVisibility(View.INVISIBLE)

            fragmentCheckSubmitBinding!!.tvClick1!!.setVisibility(View.INVISIBLE)
            fragmentCheckSubmitBinding!!.img1.setImageBitmap(bm)
        }
        if (Imagesellposition == 2) {
            fragmentCheckSubmitBinding!!.imgCam2!!.setVisibility(View.INVISIBLE)
            fragmentCheckSubmitBinding!!.tvClick2!!.setVisibility(View.INVISIBLE)
            fragmentCheckSubmitBinding!!.img2.setImageBitmap(bm)
        }
        if (Imagesellposition == 3) {
            fragmentCheckSubmitBinding!!.imgCam3!!.setVisibility(View.INVISIBLE)
            fragmentCheckSubmitBinding!!.tvClick3!!.setVisibility(View.INVISIBLE)
            fragmentCheckSubmitBinding!!.img3.setImageBitmap(bm)
        }
        if (Imagesellposition == 4) {
            fragmentCheckSubmitBinding!!.imgCam4!!.setVisibility(View.INVISIBLE)
            fragmentCheckSubmitBinding!!.tvClick4!!.setVisibility(View.INVISIBLE)
            fragmentCheckSubmitBinding!!.img4.setImageBitmap(bm)
        }
        //imageView!!.setImageBitmap(bm)
    }

    private fun onCaptureImageResult(data: Intent) {
        val thumbnail = data.extras!!.get("data") as Bitmap?
        /* val bytes = ByteArrayOutputStream()
         thumbnail!!.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
         val destination = File(Environment.getExternalStorageDirectory(), "fault_image"+ ".jpg")
         val fo: FileOutputStream
 */
        try {
            // thumbnail = MediaStore.Images.Media.getBitmap(applicationContext.contentResolver, data.data)
            /*  val bytes = ByteArrayOutputStream()
              bm!!.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
              val destination = File(Environment.getExternalStorageDirectory(), "fault_image"+ ".jpg")*/
            // val destination = File(Environment.getExternalStorageDirectory(), System.currentTimeMillis().toString() + ".jpg")

            val root = Environment.getExternalStorageDirectory().toString()
            val myDir = File("$root/wecompli/adhocfault")
            myDir.mkdirs()
            /* val generator = Random()
              var n = 100
              n = generator.nextInt(n)*/
            val fname =Imagesellposition.toString()+"adhocfault_image.jpg"
            val file = File(myDir, fname)
            val fo: FileOutputStream
            if (file.exists())
                file.delete()
            try {
                /* destination.createNewFile()
                 fo = FileOutputStream(destination)
                 fo.write(bytes.toByteArray())
                 fo.close()*/
                val out = FileOutputStream(file)
                thumbnail!!.compress(Bitmap.CompressFormat.JPEG, 100, out)
                out.flush()
                out.close()
                imagearraylist.add(file)
                imagearraylistpath.add(file.path)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }

        /* try {
             destination.createNewFile()
             fo = FileOutputStream(destination)
             fo.write(bytes.toByteArray())
             fo.close()
             imagearraylist.add(destination)
             imagearraylistpath.add(destination.path)
         } catch (e: FileNotFoundException) {
             e.printStackTrace()
         } catch (e: IOException) {
             e.printStackTrace()
         }*/

        if (Imagesellposition == 1) {
            fragmentCheckSubmitBinding!!.imgCam1!!.setVisibility(View.INVISIBLE)
            fragmentCheckSubmitBinding!!.tvClick1!!.setVisibility(View.INVISIBLE)
            fragmentCheckSubmitBinding!!.img1.setImageBitmap(thumbnail)
        }
        if (Imagesellposition == 2) {
            fragmentCheckSubmitBinding!!.imgCam2!!.setVisibility(View.INVISIBLE)
            fragmentCheckSubmitBinding!!.tvClick2!!.setVisibility(View.INVISIBLE)
            fragmentCheckSubmitBinding!!.img2.setImageBitmap(thumbnail)
        }
        if (Imagesellposition == 3) {
            fragmentCheckSubmitBinding!!.imgCam3!!.setVisibility(View.INVISIBLE)
            fragmentCheckSubmitBinding!!.tvClick3!!.setVisibility(View.INVISIBLE)
            fragmentCheckSubmitBinding!!.img3.setImageBitmap(thumbnail)
        }
        if (Imagesellposition == 4) {
            fragmentCheckSubmitBinding!!.imgCam4!!.setVisibility(View.INVISIBLE)
            fragmentCheckSubmitBinding!!.tvClick4!!.setVisibility(View.INVISIBLE)
            fragmentCheckSubmitBinding!!.img4.setImageBitmap(thumbnail)
        }

        //  val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), destination)
        // MultipartBody.Part is used to send also the actual file name
        // val body = MultipartBody.Part.createFormData("image", destination.name, requestFile)
       // imageView!!.setImageBitmap(thumbnail)

    }
    override fun onResume() {
        super.onResume()
        (activity as MainActivity).activityMainBinding!!.mainHeader.visibility=View.VISIBLE
        (activity as MainActivity).activityMainBinding!!.tvHeaderText.text=checkname
    }

    override fun selectImg1() {
        Imagesellposition=1
        CustomAlert.showalertforImageSelectionLogSubmitt(activity as MainActivity, this)
    }

    override fun selectImg2() {
        Imagesellposition=2
        CustomAlert.showalertforImageSelectionLogSubmitt(activity as MainActivity, this)

    }

    override fun selectImg3() {
        Imagesellposition=3
        CustomAlert.showalertforImageSelectionLogSubmitt(activity as MainActivity, this)

    }

    override fun selectImg4() {
        Imagesellposition=4
        CustomAlert.showalertforImageSelectionLogSubmitt(activity as MainActivity, this)

    }

    override fun checkSubmit() {
        var datetime=""
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
             datetime =  current.format(formatter)

        } else {
            var date = Date()
            val formatter = SimpleDateFormat("dd/MM/yyyy HH:mma  ")
            datetime = formatter.format(date)
        }
        var loginUserData = AppSheardPreference(activity as MainActivity).getUser(PreferenceConstant.userData)
        val customProgress: CustomProgressDialog = CustomProgressDialog().getInstance()
        customProgress.showProgress(activity as MainActivity, "Please Wait..", false)
        try {
            val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
            builder.addFormDataPart("process_remark",fragmentCheckSubmitBinding!!.etnotsubmit.text.toString())
            builder.addFormDataPart("company_id", "9")
            builder.addFormDataPart("site_id", "12")
            builder.addFormDataPart("check_id", "417")
            builder.addFormDataPart("season_id", "1")
            builder.addFormDataPart("check_type_id", "1")
            builder.addFormDataPart("check_process_type", "checks")
            builder.addFormDataPart("check_type_values_id", "1")
            builder.addFormDataPart("process_status", "y")
            builder.addFormDataPart("checks_process_log_entry_date", datetime)
            if (imagearraylist.size>0){
                for (i in imagearraylist.indices) {
                    builder.addFormDataPart(
                        "processfiles[]", imagearraylist.get(i).name, okhttp3.RequestBody.create(
                            MediaType.parse("image/jpeg"), imagearraylist.get(i)
                        )
                    )
                }
            }

            val requestBody = builder.build()
            var request: Request? = null
            request = Request.Builder()
                .addHeader("Authorization", "Bearer " + loginUserData.token)
                //.addHeader("site_id",AppSheardPreference(activity as MainActivity).getvalue_in_preference(PreferenceConstent.site_id))
                .addHeader("Content-Type", "application/json")
                .url(NetworkUtility.BASE_URL + NetworkUtility.LOGCREATE)
                .post(requestBody)
                .build()

            val client = okhttp3.OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .build()

            val call = client.newCall(request)
            call.enqueue(object : okhttp3.Callback {
                override fun onResponse(call: Call, response: Response) {
                    customProgress.hideProgress()
                    try {
                        if (response.isSuccessful) {
                            val gson = Gson()
                            var resStr: String = response.body()!!.string()
                            //var response_obj = JSONObject(resStr)
                            val processLogCreateResponseModel:ProcessLogCreateResponseModel=gson.fromJson(resStr,ProcessLogCreateResponseModel::class.java)
                            //val response_obj = JSONObject(response.body()!!.string())
                            if (processLogCreateResponseModel.process) {
                               // Toast.makeText(activity as MainActivity, "sucess", Toast.LENGTH_LONG).show()
                                // val check_process_log_id:String=response_obj.getInt("check_process_log_id").toString()
                                //callApiforfaultcreate(check_process_log_id);
                                (activity as MainActivity).runOnUiThread {
                                    CustomAlert.showalert(activity as MainActivity, processLogCreateResponseModel.message)
                                }

                            } else {
                                (activity as MainActivity).runOnUiThread {
                                    CustomAlert.showalert(activity as MainActivity, processLogCreateResponseModel.message)
                                }
                                //Toast.makeText(activity as MainActivity, processLogCreateResponseModel.message, Toast.LENGTH_LONG).show()

                            }
                        }
                    } catch (e: java.lang.Exception) {
                        customProgress.hideProgress()
                        e.printStackTrace()
                        (activity as MainActivity).runOnUiThread {
                            Toast.makeText(activity as MainActivity, "Try later. Something Wrong.", Toast.LENGTH_LONG).show()
                        }

                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    customProgress.hideProgress()
                    (activity as MainActivity).runOnUiThread {
                        Toast.makeText(activity as MainActivity, "Faliure", Toast.LENGTH_LONG).show()
                    }

                }
            })

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun taptosign() {
        if(fragmentCheckSubmitBinding!!.chktaptosign.isChecked){
            TapToSignDialogForLogSubmit(activity as MainActivity,this).show()
        }/*else{
            fragmentCheckSubmitBinding!!.ettemp.isEnabled=false
        }*/

    }

    override fun settemp() {
      if(fragmentCheckSubmitBinding!!.chktemparuture.isChecked){
          fragmentCheckSubmitBinding!!.ettemp.isEnabled=true
      }else{
          fragmentCheckSubmitBinding!!.ettemp.isEnabled=false
      }
    }
}