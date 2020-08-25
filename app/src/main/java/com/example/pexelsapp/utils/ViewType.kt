package com.example.pexelsapp.utils

interface ViewType {
    fun getViewType(): TYPE
}

enum class TYPE {
    LOADER,
    ITEM
}