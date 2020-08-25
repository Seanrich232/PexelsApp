package com.example.pexelsapp.data.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class PhotoSearchResponse(
    val next_page: String? = null,
    val page: Int? = null,
    val per_page: Int? = null,
    val photos: List<Photo>? = null,
    val total_results: Int? = null
) : Parcelable