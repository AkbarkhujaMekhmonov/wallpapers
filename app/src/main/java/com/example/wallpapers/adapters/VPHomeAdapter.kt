package com.example.wallpapers.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.wallpapers.ui.photos_fragment

class VPHomeAdapter (var fm: FragmentManager) : FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
){
    override fun getCount(): Int {
        return 6
    }

    override fun getItem(position: Int): Fragment {
        return photos_fragment(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0->"ALL"
            1->"NEW"
            2->"ANIMALS"
            3->"TECHNOLOGY"
            4->"ISLAMIC"
            else->"NATURE"
        }
    }
}