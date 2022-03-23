package com.example.wallpapers.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.retrofit.retrofit.RetrofitServises
import com.example.wallpapers.adapters.VPHomeAdapter
import com.example.wallpapers.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    lateinit var binding : FragmentHomeBinding
    lateinit var retrofitServises : RetrofitServises
    private  val TAG = "HomeFragment"
    lateinit var adapter: VPHomeAdapter

    override fun onCreateView(
        inflater : LayoutInflater,
        container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View {
        binding = FragmentHomeBinding.inflate(layoutInflater)


        adapter = VPHomeAdapter(childFragmentManager)

        binding.vpHome.adapter = adapter
        binding.tab.setupWithViewPager(binding.vpHome)


        return binding.root
    }
}