package com.example.pexelsapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pexelsapp.data.model.Photo

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photos")
    fun getAllPhotos() : LiveData<List<Photo>>

    @Query("SELECT * FROM photos WHERE id = :id")
    fun getPhoto(id: Int): LiveData<Photo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(photos: List<Photo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photo: Photo)
}