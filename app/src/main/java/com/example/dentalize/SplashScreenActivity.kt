package com.example.dentalize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.example.dentalize.view.ViewModelFactory
import com.example.dentalize.view.home.HomeActivity
import com.example.dentalize.view.login.LoginActivity
import com.example.dentalize.view.main.MainActivity
import com.example.dentalize.view.main.MainViewModel

class SplashScreenActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            mainViewModel.getToken().observe(this){
                    token ->
                val intentActivity = Intent(this, if (token == null) LoginActivity::class.java else HomeActivity::class.java)
                startActivity(intentActivity)
                finish()
            }
        }, SPLASH_TIME_OUT)
    }
    companion object {
        private const val SPLASH_TIME_OUT: Long = 3000
    }
}