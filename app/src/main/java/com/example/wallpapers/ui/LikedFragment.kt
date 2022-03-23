package com.example.wallpapers.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wallpapers.MainActivity3
import com.example.wallpapers.adapters.MyRVAdapter
import com.example.wallpapers.adapters.SetOnItemClickListener
import com.example.wallpapers.databinding.FragmentLikedBinding
import com.example.wallpapers.models.Photo
import com.example.wallpapers.models.saved.MyDataBase
import com.example.wallpapers.models.saved.SavedImg
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class LikedFragment : Fragment() {
    lateinit var binding : FragmentLikedBinding
    @SuppressLint("CheckResult")
    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View {
        binding = FragmentLikedBinding.inflate(layoutInflater)


            MyDataBase.getInstants(binding.root.context).Dao().getLiked()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    binding.rv.adapter = MyRVAdapter(it,listener)
                }){}



        return binding.root
    }

    var listener = object : SetOnItemClickListener {
        override fun onItemClick(photo : Photo) {
        }

        override fun onItemClick(savedImg : SavedImg) {
            val intent=Intent(binding.root.context,MainActivity3::class.java)
            intent.putExtra("img",savedImg)
            startActivity(intent)
        }
    }

}
