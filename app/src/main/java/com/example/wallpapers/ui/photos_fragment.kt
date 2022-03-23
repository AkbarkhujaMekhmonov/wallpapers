package com.example.wallpapers.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.retrofit.retrofit.Common
import com.example.retrofit.retrofit.RetrofitServises
import com.example.wallpapers.MainActivity2
import com.example.wallpapers.adapters.RVAdapter
import com.example.wallpapers.adapters.SetOnItemClickListener
import com.example.wallpapers.databinding.FragmentPhotosFragmentBinding
import com.example.wallpapers.models.Photo
import com.example.wallpapers.models.saved.SavedImg
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class photos_fragment(var pos : Int) : Fragment() {
    lateinit var binding : FragmentPhotosFragmentBinding
    lateinit var retrofitServises : RetrofitServises
    private val TAG = "photos_fragment"
    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View {
        binding = FragmentPhotosFragmentBinding.inflate(layoutInflater)
        retrofitServises = Common.retrofitServises
        val key="ZQbvyTPYYlz4M9QvgwJ54YCxrtrbeBvo2ym08AvdMak"
        when (pos) {
            0 -> {
                retrofitServises.getPhotosList(key,"all").enqueue(object :Callback<List<Photo>>{
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
            }
            1->{
                retrofitServises.getPhotosList(key,"new").enqueue(object :Callback<List<Photo>>{
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
            }
            2->{
                retrofitServises.getPhotosList(key,"animal").enqueue(object :Callback<List<Photo>>{
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
            }
            3->{
                retrofitServises.getPhotosList(key,"technology").enqueue(object :Callback<List<Photo>>{
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
            }
            4->{
                retrofitServises.getPhotosList(key,"islamic").enqueue(object :Callback<List<Photo>>{
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
            }
            5->{
                retrofitServises.getPhotosList(key,"nature").enqueue(object :Callback<List<Photo>>{
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
            }

        }
        return binding.root
    }
    var listener=object :SetOnItemClickListener{
        override fun onItemClick(photo : Photo) {
            val intent= Intent(binding.root.context,MainActivity2::class.java)
            intent.putExtra("photo",photo)
            startActivity(intent)
        }

        override fun onItemClick(savedImg : SavedImg) {

        }
    }


}