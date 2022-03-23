package com.example.wallpapers.models.saved

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class SavedImg :Serializable{
    @PrimaryKey
    var id:String=""

    @ColumnInfo
    var full:String=""

    @ColumnInfo
    var regular:String=""

    @ColumnInfo
    var small:String=""

    @ColumnInfo
    var raw:String=""

    @ColumnInfo
    var islike:Boolean=false

    @ColumnInfo
    var isview:Boolean=false


}