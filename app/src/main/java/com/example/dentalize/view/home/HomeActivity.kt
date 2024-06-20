package com.example.dentalize.view.home

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dentalize.R
import com.example.dentalize.data.adapter.HomeAdapter
import com.example.dentalize.databinding.ActivityHomeBinding
import com.example.dentalize.view.ViewModelFactory
import com.example.dentalize.view.add.AddActivity
import com.example.dentalize.view.login.LoginActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.show()
        val layoutManager = LinearLayoutManager(this)
        binding.listHistory.layoutManager = layoutManager

        val adapter = HomeAdapter()
//        binding.listHistory.adapter = adapter.withLoadStateFooter(
//            footer = LoadingStateAdapter{
//                adapter.retry()
//            }
//        )
        binding.fab.setOnClickListener {
            val addIntent = Intent(this@HomeActivity, AddActivity::class.java)
            startActivity(addIntent)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


//        val gifUrl = "https://hips.hearstapps.com/hmg-prod/images/giphy-36-1503998280.gif?crop=1.00xw:0.753xh;0,0.129xh&resize=980:*"
//        val gifImageView = findViewById<ImageView>(R.id.gifImageView)
//        Glide.with(this)
//            .asGif()
//            .load(gifUrl)
//            .into(gifImageView)
    }
