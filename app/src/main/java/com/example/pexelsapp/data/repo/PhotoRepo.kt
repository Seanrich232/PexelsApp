package com.example.pexelsapp.data.repo

import com.example.pexelsapp.BuildConfig.API_KEY
import com.example.pexelsapp.data.api.PhotoDataSource
import com.example.pexelsapp.data.local.PhotoDao
import com.example.pexelsapp.utils.performGetOperation
import javax.inject.Inject

class PhotoRepo @Inject constructor(
    private val photoDataSource: PhotoDataSource,
    private val localDataSource: PhotoDao
) {

    fun getPhoto(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getPhoto(id) },
        networkCall = { photoDataSource.getPhoto(id, API_KEY) },
        saveCallResult = { localDataSource.insert(it) }
    )

    fun getPhotos(search: String, perPage: Int, page: Int) = performGetOperation (
        databaseQuery = { localDataSource.getAllPhotos() },
        networkCall = { photoDataSource.getPhotos(search, perPage, page, API_KEY)},
        saveCallResult = { it.photos?.let { items -> localDataSource.insertAll(items) } }
    )
}