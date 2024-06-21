package com.example.dentalize.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.dentalize.R
import com.example.dentalize.data.result.ResultState
import com.example.dentalize.databinding.ActivityLoginBinding
import com.example.dentalize.view.ViewModelFactory
import com.example.dentalize.view.home.HomeActivity
import com.example.dentalize.view.register.RegisterActivity


class LoginActivity : AppCompatActivity() {
    private val loginViewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.hyperlinkRegister.setOnClickListener {
            val signUpActivity = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(signUpActivity)
        }
        binding.loginButton.setOnClickListener {
            loginViewModel.login( binding.edLoginEmail.text.toString(),
                binding.passwordToggle.text.toString())

        }
        val builder: AlertDialog.Builder = AlertDialog.Builder(this@LoginActivity)
        builder.setView(R.layout.activity_login)
        val dialog: AlertDialog = builder.create()

        loginViewModel.responseResult.observe(this@LoginActivity) { response ->
            when (response) {
                is ResultState.Loading -> dialog.show()
                is ResultState.Error -> {
                    dialog.dismiss()
                    Toast.makeText(
                        this@LoginActivity,
                        response.error,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is ResultState.Success -> {
                    dialog.dismiss()
                    val storyActivity = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(storyActivity)
                    finish()
                }
                else -> dialog.dismiss()
            }
        }
    }
}