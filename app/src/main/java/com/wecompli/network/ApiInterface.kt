import com.google.gson.JsonObject
import com.wecompli.model.*
import com.wecompli.network.NetworkUtility
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @POST(NetworkUtility.LOG_IN)
   fun callLoginApi(@Body body: JsonObject) : Call<LoginResponseModel>


    @POST(NetworkUtility.SITE_LIST)
    fun callSiteListApi(@Header("Authorization") token:String,@Body body: JsonObject) : Call<SiteListResponseModel>

    @POST(NetworkUtility.SITE_CREATE)
    fun callSiteCreate(@Header("Authorization") token:String, @Body body: JsonObject) : Call<AddSiteModel>

    @POST(NetworkUtility.ROLESLIST)
    fun callRoeListApi(@Header("Authorization") token:String, @Body body: JsonObject) : Call<RoleListResponseModel>

    @POST(NetworkUtility.USERLIST)
    fun callUserListApi(@Header("Authorization") token:String, @Body body: JsonObject) : Call<UserListResponseModel>

    @POST(NetworkUtility.UserCREATE)
    fun callUserCreate(@Header("Authorization") token:String, @Body body: JsonObject) : Call<AddSiteModel>

    @POST(NetworkUtility.CHECKLIST)
    fun callChecklist(@Header("Authorization") token:String, @Body body: JsonObject) : Call<CheckListResponseModel>

   @POST(NetworkUtility.CHECKLISTCREATE)
    fun callCheckCreate(@Header("Authorization") token:String, @Body body: JsonObject) : Call<AddCheckListResponseModel>

  @POST(NetworkUtility.ADDROLE)
  fun callRoleCreate(@Header("Authorization") token:String, @Body body: JsonObject) : Call<AddRoleResponseModel>

    @POST(NetworkUtility.SITE_LIST_CATEGORY_WISE)
    fun callSiteListCategoryWiseApi(@Header("Authorization") token:String,@Body body: JsonObject) : Call<CategorySiteListResponse>

    @POST(NetworkUtility.CHECKSADD)
    fun callforChecksCreate(@Header("Authorization") token:String,@Body body: JsonObject) : Call<AddChecksModel>


    /*  @POST(NetworkUtility.LOGIN)
      fun callLogInApi(@Body body: JsonObject): Call<LoginResponseModel>

      @POST(NetworkUtility.FORGOTPASSWORD)
      fun callLogForgotPassword(@Body body: JsonObject): Call<AddUserResponse>

      @Headers("Content-Type: application/json")
      @POST(NetworkUtility.SITELIST)
      fun callSiteListApi(@Header("Authorization") token:String,@Body body: JsonObject): Call<SiteListModel>

      @Headers("Content-Type: application/json")
      @POST(NetworkUtility.CREATE_USER)
      fun caallCreateUserApi(@Header("Authorization") token:String, @Body body: JsonObject): Call<AddUserResponse>

      @Headers("Content-Type: application/json")
      @POST(NetworkUtility.SITE_USER_UPDATE)
      fun callSiteUserUpdateApi(@Header("Authorization") token:String, @Body body: JsonObject): Call<AddUserResponse>

      @Headers("Content-Type: application/json")
      @POST(NetworkUtility.SITE_USER_LIST)
      fun callSiteUserListApi(@Header("Authorization") token:String, @Body body: JsonObject): Call<SiteUserListModel>

      @Headers("Content-Type: application/json")
      @POST(NetworkUtility.FAULTLIST)
      fun callFaultApi(@Header("Authorization") token:String, @Body body: JsonObject): Call<FaultListModel>

      @Headers("Content-Type: application/json")
      @POST(NetworkUtility.FAULTREPAIR)
      fun callApiforFaultRepair(@Header("Authorization") token:String, @Body body: JsonObject): Call<AddUserResponse>

      @Headers("Content-Type: application/json")
      @POST(NetworkUtility.DOCUMENT_LIST)
      fun calldocumetList(@Header("Authorization") token:String,@Body body: JsonObject): Call<DocumentListModel>

      @Headers("Content-Type: application/json")
      @POST(NetworkUtility.FAULTCOUNT)
      fun callFaultCountApt(@Header("Authorization") token:String,@Body body: JsonObject): Call<FaultCountModel>

      @Headers("Content-Type: application/json")
      @POST(NetworkUtility.DOCUMENT_REMOVE)
      fun callApifordocumentremove(@Header("Authorization") token:String, @Body body: JsonObject): Call<AddUserResponse>

      @Headers("Content-Type: application/json")
      @POST(NetworkUtility.PASSWORD_UPDATE)
      fun callApifordochangepassword(@Header("Authorization") token:String, @Body body: JsonObject): Call<AddUserResponse>

      @Headers("Content-Type: application/json")
      @POST(NetworkUtility.PROFILE_UPDATE)
      fun callApiforupdateprofile(@Header("Authorization") token:String, @Body body: JsonObject): Call<AddUserResponse>

      @Headers("Content-Type: application/json")
      @POST(NetworkUtility.COUNTRYLIST)
      fun callApiforcountrylist(@Header("Authorization") token:String, @Body body: JsonObject): Call<CountryListModel>


      @Headers("Content-Type: application/json")
      @POST(NetworkUtility.EMAILLIST)
      fun callApiforemaillist(@Header("Authorization") token:String, @Body body: JsonObject): Call<EmailListModel>

      @Headers("Content-Type: application/json")
      @POST(NetworkUtility.EMAILCREATE)
      fun callApiforemailadd(@Header("Authorization") token:String, @Body body: JsonObject): Call<AddUserResponse>

      @Headers("Content-Type: application/json")
      @POST(NetworkUtility.SITE_REMOVE)
      fun callApifordeletesite(@Header("Authorization") token:String, @Body body: JsonObject): Call<AddUserResponse>

      @Headers("Content-Type: application/json")
      @POST(NetworkUtility.User_Remove)
      fun callApifordeleteUser(@Header("Authorization") token:String, @Body body: JsonObject): Call<AddUserResponse>

      @Headers("Content-Type: application/json")
      @POST(NetworkUtility.SUBCRIPTION)
      fun callApiforSubPackage(@Header("Authorization") token:String, @Body body: JsonObject): Call<SubCriptionPackagResponseemodel>

      @Headers("Content-Type: application/json")
      @POST(NetworkUtility.PAYMENT)
      fun callApiforpayment(@Header("Authorization") token:String, @Body body: JsonObject): Call<AddUserResponse>

      @Headers("Content-Type: application/json")
      @POST(NetworkUtility.SUBCRIPTION_DETAILS)
      fun callApiforsubcription(@Header("Authorization") token:String, @Body body: JsonObject): Call<SubcriptionDeatilsResponse>

      @Headers("Content-Type: application/json")
      @POST(NetworkUtility.FREEPACKAGE)
      fun callApiforfreesubcription(@Header("Authorization") token:String, @Body body: JsonObject): Call<AddUserResponse>

      @Headers("Content-Type: application/json")
      @POST(NetworkUtility.CHECKUSERSUBCRIPTION)
      fun callApiforCheckUserubcription(@Header("Authorization") token:String, @Body body: JsonObject): Call<CheckUserSubcriptionResponse>

      @Headers("Content-Type: application/json")
      @POST(NetworkUtility.SiteUserListForRemove)
      fun callApiforsiteandUser(@Header("Authorization") token:String, @Body body: JsonObject): Call<SiteAndUserModel>

      @Headers("Content-Type: application/json")
      @POST(NetworkUtility.REMOVEUSERSITE)
      fun callApiforRemovesiteandUser(@Header("Authorization") token:String, @Body body: JsonObject): Call<SiteAndUserModel>

  */
    // @Headers("Content-Type: application/json")
    /*@POST(NetworkUtility.LOG_IN)
    fun callLogInApi(@Field("user_email") email:String,
                     @Field("password") pass:String,
                     @Field("identification_id_token") identification_id_token:String,
                     @Field("device_model") device_model:String,
                     @Field("device_os") device_os:String): Call<LoginResponse>
*/




}