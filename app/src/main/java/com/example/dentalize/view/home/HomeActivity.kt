package com.example.dentalize.view.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.dentalize.R
import com.example.dentalize.data.response.ItemHistoryResponseItem
import com.example.dentalize.databinding.ActivityHomeBinding
import com.example.dentalize.view.HistoryAdapter
import com.example.dentalize.view.ViewModelFactory
import com.example.dentalize.view.add.AddActivity
import com.example.dentalize.view.login.LoginViewModel
import com.example.dentalize.view.result.ResultActivity

class HomeActivity : AppCompatActivity() {

    private val homeViewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val gifUrl =
//            "https://hips.hearstapps.com/hmg-prod/images/giphy-36-1503998280.gif?crop=1.00xw:0.753xh;0,0.129xh&resize=980:*"
//        val gifImageView = findViewById<ImageView>(R.id.gifImageView)
//        Glide.with(this)
//            .asGif()
//            .load(gifUrl)
//            .into(gifImageView)

        binding.fab.setOnClickListener {
            val addIntent = Intent(this@HomeActivity, AddActivity::class.java)
            startActivity(addIntent)
        }

//        val homeViewModel = ViewModelProvider(
//            this,
//            ViewModelProvider.NewInstanceFactory()
//        ).get(HomeViewModel::class.java)

        homeViewModel.history.observe(this) { history ->
            setHistoryData(history)
        }

        val layoutManager = LinearLayoutManager(this)
        binding.listHistory.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.listHistory.addItemDecoration(itemDecoration)

        homeViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun setHistoryData(item: List<ItemHistoryResponseItem>) {
        val adapter = HistoryAdapter()
        adapter.submitList(item)
        binding.listHistory.adapter = adapter

        adapter.setOnItemClickCallback(object : HistoryAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ItemHistoryResponseItem) {
                val ResultIntent = Intent(this@HomeActivity, ResultActivity::class.java)
                ResultIntent.putExtra("key_user", data.result)
                startActivity(ResultIntent)
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
