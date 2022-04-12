package com.example.mytesting.model

import android.graphics.Picture
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "item_table")
data class Item (
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @SerializedName("Id")
    @Expose
    var id: Int,

    @SerializedName("CategoryId")
    @Expose
    var categoryId: Int,

    @SerializedName("Name")
    @Expose
    var name: String?,

    @SerializedName("CategoryName")
    @Expose
    var categoryName: String? ,

    @SerializedName("Type")
    @Expose
    var type: Int? ,

    @SerializedName("Date")
    @Expose
    var date: String?

):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }
    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("Not yet implemented")
    }



}