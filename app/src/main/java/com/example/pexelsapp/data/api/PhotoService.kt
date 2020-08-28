package com.example.pexelsapp.data.api

import com.example.pexelsapp.BuildConfig.API_KEY
import com.example.pexelsapp.data.model.Photo
import com.example.pexelsapp.data.model.PhotoSearchResponse
import retrofit2.Response
import retrofit2.http.*

interface PhotoService {
//    @Headers(HEADER_AUTHORIZATION)

    @GET("search")
    suspend fun getPhotos(
        @Query("query") search: String,
        @Query("per_page") per_page: Int,
        @Query("page") page: Int,
        @Header("Authorization") api_key: String
    ): Response<PhotoSearchResponse>

    @GET("photos/{id}")
    suspend fun getPhoto(
        @Path("id") id: Int,
        @Header("Authorization") api_key: String
    ): Response<Photo>

    @GET("search")
    suspend fun photoSearch(
        @Query("query") search: String,
        @Query("per_page") per_page: Int,
        @Query("page") page: Int,
        @Header("Authorization") api_key: String
    ) : PhotoSearchResponse

//    companion object{
//        const val HEADER_AUTHORIZATION = "Authorization:$API_KEY"
//    }
}