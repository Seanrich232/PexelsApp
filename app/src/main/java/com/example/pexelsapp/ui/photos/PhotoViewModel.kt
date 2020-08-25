package com.example.pexelsapp.ui.photos

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.pexelsapp.data.repo.PhotoRepo


class PhotoViewModel @ViewModelInject constructor(
    private val repository: PhotoRepo
) : ViewModel() {

    val photos = repository.getPhotos("cats", 10,1)
}