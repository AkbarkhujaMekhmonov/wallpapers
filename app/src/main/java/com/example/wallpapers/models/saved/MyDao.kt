package com.example.wallpapers.models.saved

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface MyDao {

    @Insert
    fun addImage(image:SavedImg)

    @Query("select * from savedimg where islike=1")
    fun getLiked():Flowable<List<SavedImg>>

    @Query("select * from savedimg where isview=1")
    fun gethistory():Flowable<List<SavedImg>>

    @Update
    fun updateImg(img:SavedImg):Completable


}
