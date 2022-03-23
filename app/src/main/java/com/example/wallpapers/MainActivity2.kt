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
import com.example.wallpapers.blur.BlurTransformation
import com.example.wallpapers.databinding.ActivityMain2Binding
import com.example.wallpapers.models.Photo
import com.example.wallpapers.models.saved.MyDataBase
import com.example.wallpapers.models.saved.SavedImg
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.lang.Exception

class MainActivity2 : AppCompatActivity() {
    lateinit var binding : ActivityMain2Binding

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val w : Window = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        binding.fon.isVisible = false
        binding.like.isVisible = false
        val photo = intent?.getSerializableExtra("photo") as Photo
        val mydatbase = MyDataBase.getInstants(this).Dao()
        var islike = false

        mydatbase.gethistory().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                try {
                    if (it.size != 0) {
                        it.forEach {
                            if (photo.id != it.id) {
                                Picasso.get().load(photo.urls.regular)
                                    .transform(BlurTransformation(this))
                                    .into(binding.ivActv, object : Callback {
                                        @SuppressLint("CheckResult")
                                        @RequiresApi(Build.VERSION_CODES.N)
                                        override fun onSuccess() {
                                            binding.progressbar.isGone
                                            binding.fon.isVisible = true
                                            binding.like.isVisible = true
                                            val image = SavedImg()
                                            image.id = photo.id
                                            image.regular = photo.urls.regular
                                            image.full = photo.urls.full
                                            image.small = photo.urls.small
                                            image.raw = photo.urls.raw
                                            image.islike = false
                                            image.isview = true
                                            mydatbase.addImage(image)


                                            binding.fon.setOnClickListener {
                                                setWallpaper()

                                            }

                                            binding.like.setOnClickListener {
                                                if (!islike) {
                                                    islike = !islike
                                                    image.islike = true
                                                    mydatbase.updateImg(image)
                                                        .subscribeOn(Schedulers.io())
                                                        .observeOn(AndroidSchedulers.mainThread())
                                                        .subscribe({
                                                        }) {}
                                                    binding.like.setImageResource(R.drawable.ic_baseline_favorite_24)
                                                } else {
                                                    islike = !islike
                                                    image.islike = false
                                                    mydatbase.updateImg(image)
                                                        .subscribeOn(Schedulers.io())
                                                        .observeOn(AndroidSchedulers.mainThread())
                                                        .subscribe({
                                                        }) {}
                                                    binding.like.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                                                }
                                            }
                                        }

                                        override fun onError(e : Exception?) {

                                        }
                                    })
                            } else {
                                Picasso.get().load(it.regular).transform(BlurTransformation(this))
                                    .into(binding.ivActv, object : Callback {
                                        @SuppressLint("CheckResult")
                                        @RequiresApi(Build.VERSION_CODES.N)
                                        override fun onSuccess() {
                                            binding.progressbar.isGone
                                            binding.fon.isVisible = true
                                            binding.like.isVisible = true
                                            val img = it
                                            if (img.islike) {
                                                binding.like.setImageResource(R.drawable.ic_baseline_favorite_24)
                                                islike = true
                                            }
                                            binding.like.setOnClickListener {
                                                if (islike) {
                                                    binding.like.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                                                    img.islike = false
                                                    mydatbase.updateImg(img)
                                                        .subscribeOn(Schedulers.io())
                                                        .observeOn(AndroidSchedulers.mainThread())
                                                        .subscribe({
                                                        }) {}
                                                } else {
                                                    binding.like.setImageResource(R.drawable.ic_baseline_favorite_24)
                                                    img.islike = true
                                                    mydatbase.updateImg(img)
                                                        .subscribeOn(Schedulers.io())
                                                        .observeOn(AndroidSchedulers.mainThread())
                                                        .subscribe({
                                                        }) {}
                                                }
                                            }

                                            binding.fon.setOnClickListener {
                                                setWallpaper()
                                            }

                                        }

                                        override fun onError(e : Exception?) {

                                        }
                                    })
                            }
                        }
                    } else {
                        Picasso.get().load(photo.urls.regular).transform(BlurTransformation(this))
                            .into(binding.ivActv, object : Callback {
                                @SuppressLint("CheckResult")
                                @RequiresApi(Build.VERSION_CODES.N)
                                override fun onSuccess() {
                                    binding.progressbar.isGone
                                    binding.fon.isVisible = true
                                    binding.like.isVisible = true

                                    val image = SavedImg()
                                    image.id = photo.id
                                    image.regular = photo.urls.regular
                                    image.full = photo.urls.full
                                    image.small = photo.urls.small
                                    image.raw = photo.urls.raw
                                    image.islike = false
                                    image.isview = true
                                    mydatbase.addImage(image)

                                    binding.fon.setOnClickListener {
                                        setWallpaper()
                                    }

                                    binding.like.setOnClickListener {
                                        if (!islike) {
                                            islike = !islike
                                            image.islike = true
                                            mydatbase.updateImg(image)
                                                .subscribeOn(Schedulers.io())
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .subscribe({
                                                }) {}
                                            binding.like.setImageResource(R.drawable.ic_baseline_favorite_24)
                                        } else {
                                            islike = !islike
                                            image.islike = false
                                            mydatbase.updateImg(image)
                                                .subscribeOn(Schedulers.io())
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .subscribe({
                                                }) {}
                                            binding.like.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                                        }
                                    }

                                }

                                override fun onError(e : Exception?) {

                                }
                            })
                    }
                } catch (e : Exception) {

                }
            }) {
            }


    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun setWallpaper() {
        binding.fon.setOnClickListener {
            Toast.makeText(this, "Image set home screen!", Toast.LENGTH_SHORT).show()
            try {
                Toast.makeText(this@MainActivity2, "Wallpaper is set", Toast.LENGTH_SHORT)
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