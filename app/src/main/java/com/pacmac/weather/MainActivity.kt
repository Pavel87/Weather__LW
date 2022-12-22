package com.pacmac.weather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pacmac.weather.WeatherSearchUI.WeatherFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container, WeatherFragment.newInstance(),
                    WeatherFragment::class.java.simpleName
                )
                .commit()
        }
    }
}