package com.example.tagyourit.ui.photos

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.tagyourit.data.repo.PhotoRepo
import retrofit2.Response


class PhotoViewModel @ViewModelInject constructor(
    private val repository: PhotoRepo
) : ViewModel() {

    val photos = repository.getPhotos("cats", 10,1)
}