package com.example.pexelsapp.data.model

import android.os.Parcelable
import androidx.room.Entity
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
@Entity(tableName = "sizes")
data class Size(
    val landscape: String? = null,
    val large: String? = null,
    val large2x: String? = null,
    val medium: String? = null,
    val original: String? = null,
    val portrait: String? = null,
    val small: String? = null,
    val tiny: String? = null

) : Parcelable