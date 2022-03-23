package com.example.wallpapers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpapers.blur.BlurTransformation
import com.example.wallpapers.databinding.ItemBinding
import com.example.wallpapers.models.Photo
import com.squareup.picasso.Picasso

class RVAdapter(var list : ArrayList<Photo>,var listener : SetOnItemClickListener) : RecyclerView.Adapter<RVAdapter.MyVH>() {
    inner class MyVH(var itemBinding : ItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun onBind(photo : Photo) {
            Picasso.get().load(photo.urls.small).transform(BlurTransformation(itemBinding.root.context)).into(itemBinding.itemIv)
            itemBinding.root.setOnClickListener {
                listener.onItemClick(photo)
            }
        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : MyVH =
        MyVH(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder : MyVH, position : Int) = holder.onBind(list[position])
    override fun getItemCount() : Int = list.size

}