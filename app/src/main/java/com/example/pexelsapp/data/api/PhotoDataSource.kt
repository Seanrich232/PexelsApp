package com.example.pexelsapp.data.api

import javax.inject.Inject

class PhotoDataSource @Inject constructor(
    private val photoService: PhotoService
) : BaseDataSource() {

    suspend fun getPhotos(search: String, perPage: Int, page: Int, api_key: String) = getResult {
        photoService.getPhotos(
            search,
            perPage,
            page,
            api_key
        )
    }
    suspend fun getPhoto(id: Int, api_key: String) = getResult { photoService.getPhoto(id, api_key) }

}