package com.example.dentalize.view.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.dentalize.data.result.ResultState
import com.example.dentalize.databinding.ActivityRegisterBinding
import com.example.dentalize.view.ViewModelFactory
import com.example.dentalize.view.login.LoginActivity

class  RegisterActivity : AppCompatActivity() {
    private val registerViewModel by viewModels<RegisterViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityRegisterBinding
    private val factory: ViewModelFactory = ViewModelFactory.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        val loginActivity = Intent(this, LoginActivity::class.java)
        binding.apply {
            hyperlinkLogin.setOnClickListener {

                startActivity(loginActivity)
            }

            registerButton.setOnClickListener {
                if (edRegisterEmail.text?.isNotEmpty() == true && edRegisterName.text?.isNotEmpty() == true && edRegisterPassword.text?.length!! >= 8) {
                    registerViewModel.submitRegister(
                        username = edRegisterName.text.toString(),
                        email = edRegisterEmail.text.toString(),
                        password = edRegisterPassword.text.toString()
                    )
                } else {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Please fill in the form ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            val builder: AlertDialog.Builder = AlertDialog.Builder(this@RegisterActivity)
            val dialog: AlertDialog = builder.create()

            registerViewModel.responseResult.observe(this@RegisterActivity) { response ->
                when (response) {
                    is ResultState.Loading -> dialog.show()
                    is ResultState.Error -> {
                        dialog.dismiss()
                        Toast.makeText(
                            this@RegisterActivity,
                            response.error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is ResultState.Success -> {
                        dialog.dismiss()
                        val detailActivity = Intent(this@RegisterActivity, LoginActivity::class.java)
                        startActivity(detailActivity)
                        finish()
                    }
                    else -> dialog.dismiss()
                }
            }
        }
    }
}