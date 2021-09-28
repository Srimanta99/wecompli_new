package com.wecompli.model

import android.os.Parcel
import android.os.Parcelable

data class SiteListResponseModel(val process:Boolean, val rows:ArrayList<SiteDetails>,val message:String) {
    data class  SiteDetails(val id:Int,val company_id:Int,val site_name:String,val status_id:Int, val created_at:String,val users_count:Int,
    val documents_count:Int,val incidents_count:Int,val checkslist_count:Int,val checkslistchecks_count:Int,val faults:Int,var isselect:Boolean ) :
        Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString()!!,
            parcel.readInt(),
            parcel.readString()!!,
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readByte() != 0.toByte()
        ) {
        }

        override fun describeContents(): Int {
            TODO("Not yet implemented")
        }

        override fun writeToParcel(p0: Parcel?, p1: Int) {
            TODO("Not yet implemented")
        }

        companion object CREATOR : Parcelable.Creator<SiteDetails> {
            override fun createFromParcel(parcel: Parcel): SiteDetails {
                return SiteDetails(parcel)
            }

            override fun newArray(size: Int): Array<SiteDetails?> {
                return arrayOfNulls(size)
            }
        }

    }
}