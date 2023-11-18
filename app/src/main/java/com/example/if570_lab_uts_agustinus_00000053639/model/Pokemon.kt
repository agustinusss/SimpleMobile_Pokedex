package com.example.if570_lab_uts_agustinus_00000053639.model

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json


data class Pokemon (

  @field:Json(name ="id"             ) var id            : Int?                     = null,
  @field:Json(name ="num"            ) var num           : String?                  = null,
  @field:Json(name ="name"           ) var name          : String?                  = null,
  @field:Json(name ="img"            ) var img           : String?                  = null,
  @field:Json(name ="type"           ) var type          : List<String>             = arrayListOf(),
  @field:Json(name ="height"         ) var height        : String?                  = null,
  @field:Json(name ="weight"         ) var weight        : String?                  = null,
  @field:Json(name ="weaknesses"     ) var weaknesses    : List<String>             = arrayListOf(),
): Parcelable {
  constructor(parcel: Parcel) : this(
  parcel.readValue(Int::class.java.classLoader) as? Int,
  parcel.readString(),
  parcel.readString(),
  parcel.readString(),
  parcel.createStringArrayList() ?: arrayListOf(),
  parcel.readString(),
  parcel.readString(),
  parcel.createStringArrayList() ?: arrayListOf()
  )

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeValue(id)
    parcel.writeString(num)
    parcel.writeString(name)
    parcel.writeString(img)
    parcel.writeStringList(type)
    parcel.writeString(height)
    parcel.writeString(weight)
    parcel.writeStringList(weaknesses)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<Pokemon> {
    override fun createFromParcel(parcel: Parcel): Pokemon {
      return Pokemon(parcel)
    }

    override fun newArray(size: Int): Array<Pokemon?> {
      return arrayOfNulls(size)
    }
  }
}