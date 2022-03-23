package com.example.wallpapers.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit.retrofit.Common
import com.example.retrofit.retrofit.RetrofitServises
import com.example.wallpapers.MainActivity2
import com.example.wallpapers.adapters.RVAdapter
import com.example.wallpapers.adapters.SetOnItemClickListener
import com.example.wallpapers.databinding.FragmentGalleryBinding
import com.example.wallpapers.models.Photo
import com.example.wallpapers.models.saved.SavedImg
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularFragment : Fragment() {

    lateinit var binding : FragmentGalleryBinding
    lateinit var retrofitServises : RetrofitServises
    private  val TAG = "PopularFragment"
    override fun onCreateView(
        inflater : LayoutInflater,
        container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View {
        binding = FragmentGalleryBinding.inflate(layoutInflater)
        retrofitServises = Common.retrofitServises
        val key="ZQbvyTPYYlz4M9QvgwJ54YCxrtrbeBvo2ym08AvdMak"
        retrofitServises.getPhotosList(key,"popular").enqueue(object : Callback<List<Photo>> {
            override fun onResponse(
                call : Call<List<Photo>>,
                response : Response<List<Photo>>
            ) {
                if (response.isSuccessful&&response.body()!=null){
                    binding.rv.adapter = RVAdapter(response.body()!!as ArrayList<Photo>,listener)
                }else
                    Log.d(TAG, "onResponse: null")
            }
            override fun onFailure(call : Call<List<Photo>>, t : Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            } })

        return binding.root
    }
    var listener=object : SetOnItemClickListener {
        override fun onItemClick(photo : Photo) {
            var intent= Intent(binding.root.context, MainActivity2::class.java)
            intent.putExtra("photo",photo)
            startActivity(intent)
        }

        override fun onItemClick(savedImg : SavedImg) {
        }
    }

}