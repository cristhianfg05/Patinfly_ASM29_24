package com.patinfly.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.patinfly.R
import com.patinfly.databinding.ActivityLoginRegisterBinding

class LoginRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.loginBt.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.registerBt.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}