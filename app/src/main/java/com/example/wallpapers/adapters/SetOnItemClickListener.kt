package com.example.wallpapers.adapters

import com.example.wallpapers.models.Photo
import com.example.wallpapers.models.saved.SavedImg

interface SetOnItemClickListener {
    fun onItemClick(photo : Photo)
    fun onItemClick(savedImg : SavedImg)
}