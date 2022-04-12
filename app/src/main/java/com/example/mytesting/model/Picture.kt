package com.example.mytesting.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
@Entity(tableName = "picture")
data class Picture (
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @SerializedName("Id")
    @Expose
    var id: Int? = 0,

    @SerializedName("PageSize")
    @Expose
    var pageSize: Int? = 0,

    @SerializedName("Caption")
    @Expose
    var caption: String? = "",

    @SerializedName("TypeId")
    @Expose
    var typeId: Int? = 0,

    @SerializedName("FileName")
    @Expose
    var fileName: String? = "",

    @SerializedName("YouTubePath")
    @Expose
    var youTubePath: String? = "",

    @SerializedName("FilePath")
    @Expose
    var filePath: String? = "",

    @SerializedName("CroppedImage200x200")
    @Expose
    var croppedImage200x200: String? = "",

    @SerializedName("CroppedImage320x308")
    @Expose
    var croppedImage320x308: String? = ""
)