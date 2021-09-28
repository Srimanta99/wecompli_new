package com.wecompli.model

import android.os.Parcel
import android.os.Parcelable

data class UserListResponseModel(val process:Boolean,val message:String,val rows:ArrayList<UserDetails>) {
    data class UserDetails(val id:Int,val user_first_name:String,val user_email_ID:String,val status_id:Int,val created_at:String,
    val sites:ArrayList<Sites>,val roles:ArrayList<Role>) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readInt(),
            parcel.readString()!!,
            parcel.readArrayList(null)!! as ArrayList<Sites>,
            parcel.readArrayList(null) as ArrayList<Role>
        ) {
        }

        override fun describeContents(): Int {
            TODO("Not yet implemented")
        }

        override fun writeToParcel(p0: Parcel?, p1: Int) {
            TODO("Not yet implemented")
        }

        companion object CREATOR : Parcelable.Creator<UserDetails> {
            override fun createFromParcel(parcel: Parcel): UserDetails {
                return UserDetails(parcel)
            }

            override fun newArray(size: Int): Array<UserDetails?> {
                return arrayOfNulls(size)
            }
        }
    }

    data class Sites(val site_name:String,val pivot:pivot,val isselect:Boolean)
    data class pivot(val user_id:Int,val site_id:Int)
    data class Role(val role_name:String,val pivot:pivot)
}