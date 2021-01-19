package com.example.sitabah


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.agrawalsuneet.dotsloader.loaders.LazyLoader
import com.agrawalsuneet.dotsloader.loaders.TashieLoader
import kotlinx.android.synthetic.main.activity_splash_screen.*


class SplashScreen : AppCompatActivity() {
    
    lateinit var handler: Handler
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()


        var tashie = TashieLoader(
            this, 5,
            50, 10,
            ContextCompat.getColor(this, R.color.md_green_400))
            .apply {
                animDuration = 500
                animDelay = 100
                interpolator = LinearInterpolator()
            }
        containerLL.addView(tashie)

        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(intent)
            finish()

        }, 5000)
    }
}