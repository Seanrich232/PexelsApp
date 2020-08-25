package com.example.pexelsapp.utils.extensions

import retrofit2.Response

fun<T> Response<T>.successWithData() = isSuccessful && body() != null && code() == 200



