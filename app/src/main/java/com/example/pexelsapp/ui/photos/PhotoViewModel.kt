package com.example.pexelsapp.ui.photos

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pexelsapp.BuildConfig.API_KEY
import com.example.pexelsapp.data.api.PhotoService
import com.example.pexelsapp.data.model.PhotoSearchResponse
import com.example.pexelsapp.data.repo.PhotoRepo
import kotlinx.coroutines.launch
import kotlin.properties.Delegates
import kotlin.properties.ObservableProperty


class PhotoViewModel @ViewModelInject constructor(
    val repository: PhotoRepo,
    private val photoService: PhotoService
) : ViewModel() {

    val photos = repository.getPhotos("",10,1)

    var search = MutableLiveData("")

    private val _photoSearchResponse = MutableLiveData<PhotoSearchResponse>()
    val photoSearchResponse: LiveData<PhotoSearchResponse>
        get() = _photoSearchResponse

    fun fetchPhoto(query: String){
        viewModelScope.launch {
//            val response = photoService.photoSearch(query,10,1, API_KEY)
//            _photoSearchResponse.postValue(response)
            val photos = photoService.photoSearch(query,10,1, API_KEY)
            _photoSearchResponse.postValue(photos)

        }
    }


}