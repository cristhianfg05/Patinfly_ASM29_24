package com.patinfly.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.patinfly.R
import com.patinfly.data.datasource.UserDataSource
import com.patinfly.data.repository.UserRepository
import com.patinfly.databinding.ActivityLoginBinding
import com.patinfly.databinding.ActivityLoginRegisterBinding
import kotlinx.coroutines.newSingleThreadContext

class LoginActivity : AppCompatActivity() {

    private lateinit var userRepository: UserRepository
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val userDataSource = UserDataSource(this)
        userRepository = UserRepository(userDataSource)

        binding.loginButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            loginUser(username, password)
        }
    }

    private fun loginUser(username: String, password: String) {
        val user = userRepository.getUserByUsername(username)

        if (user != null && user.encryptedKey == password) {
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            // Login fallido, mostrar mensaje
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
        }


    }
}