package com.example.pexelsapp.data.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
@Entity(tableName = "photos")
data class Photo(
    @PrimaryKey val id: Int? = null,
    val width: Int? = null,
    val height: Int? = null,
    val liked: Boolean? = null,
    val photographer: String? = null,
    val photographer_id: Int? = null,
    val photographer_url: String? = null,
    @Embedded val src: Size? = null,
    val url: String? = null

) : Parcelable