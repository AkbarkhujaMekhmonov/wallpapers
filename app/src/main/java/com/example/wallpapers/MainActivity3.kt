package com.example.wallpapers

import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.wallpapers.adapters.MyRVAdapter
import com.example.wallpapers.blur.BlurTransformation
import com.example.wallpapers.databinding.ActivityMain3Binding
import com.example.wallpapers.models.saved.MyDataBase
import com.example.wallpapers.models.saved.SavedImg
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.lang.Exception

class MainActivity3 : AppCompatActivity() {
    lateinit var binding : ActivityMain3Binding
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val w : Window = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        binding.fon.isVisible = false
        binding.like.isVisible=false
        var islike=false
        val img = intent?.getSerializableExtra("img") as SavedImg
        Picasso.get().load(img.regular).transform(BlurTransformation(this)).into(binding.ivActv,object :
            Callback {
            @SuppressLint("CheckResult")
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onSuccess() {
                binding.progressbar.isGone
                binding.fon.isVisible=true
                binding.like.isVisible=true
                binding.fon.setOnClickListener {
                    setWallpaper()

                }
                if (img.islike){
                    binding.like.setImageResource(R.drawable.ic_baseline_favorite_24)
                    islike=true
                }
                binding.like.setOnClickListener {
                    if (!islike){
                        binding.like.setImageResource(R.drawable.ic_baseline_favorite_24)
                        img.islike=true
                        MyDataBase.getInstants(this@MainActivity3).Dao().updateImg(img)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({
                            }){}
                        islike=!islike
                    }else{
                        binding.like.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                        img.islike=false
                        MyDataBase.getInstants(this@MainActivity3).Dao().updateImg(img)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({
                            }){}
                        islike=!islike
                    }
                }
            }

            override fun onError(e : Exception?) {
            }
        })

    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun setWallpaper() {
        binding.fon.setOnClickListener {
            Toast.makeText(this, "Image set home screen!", Toast.LENGTH_SHORT).show()
            try {
                Toast.makeText(this@MainActivity3, "Wallpaper is set", Toast.LENGTH_SHORT)
                    .show()
                try {
                    val bitmap = binding.ivActv.drawable.toBitmap()
                    val wallpaperManager = WallpaperManager.getInstance(applicationContext)
                    wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_SYSTEM)
                } catch (e : Exception) {
                }
            } catch (e : IOException) {
                e.printStackTrace()
            }
        }

    }
}