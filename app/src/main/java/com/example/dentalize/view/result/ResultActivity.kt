package com.example.dentalize.view.result

import android.content.Intent.EXTRA_USER
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.dentalize.R
import com.example.dentalize.data.response.ResultResponse
import com.example.dentalize.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private val viewModel: ResultViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var result = intent.getStringExtra(EXTRA_USER).toString()

        supportActionBar?.hide()

        viewModel.resultDisease(result)
        viewModel.result.observe(this) {
                result -> showDetailResult(result)
        }
    }

    private fun showDetailResult(result: ResultResponse){
        Glide.with(binding.root)
            .load(result.imageUrl)
            .into(binding.resultImageView)
        binding.tittleDescription.text = result.result
        binding.fillDesc.text = result.explanation
        binding.fillCauses.text = result.cause
        binding.fillPrevention.text = result.prevention
        binding.fillTreatment.text = result.suggestion
    }
}