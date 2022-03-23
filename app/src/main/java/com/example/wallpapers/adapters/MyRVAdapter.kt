package com.example.wallpapers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpapers.blur.BlurTransformation
import com.example.wallpapers.databinding.ItemBinding
import com.example.wallpapers.models.saved.SavedImg
import com.squareup.picasso.Picasso

class MyRVAdapter(var list : List<SavedImg>,var listener:SetOnItemClickListener) : RecyclerView.Adapter<MyRVAdapter.MyVH>() {
    inner class MyVH(var itemBinding : ItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun onBind(img : SavedImg) {
            Picasso.get().load(img.small).transform(BlurTransformation(itemBinding.root.context)).into(itemBinding.itemIv)
            itemBinding.root.setOnClickListener {
                listener.onItemClick(img)
            }
        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : MyVH =
        MyVH(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder : MyVH, position : Int) = holder.onBind(list[position])
    override fun getItemCount() : Int = list.size

}