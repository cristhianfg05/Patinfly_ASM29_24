package com.patinfly.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.patinfly.R
import com.patinfly.data.datasource.UserDataSource
import com.patinfly.data.model.UserModel
import com.patinfly.data.repository.UserRepository
import com.patinfly.databinding.ActivityLoginBinding
import com.patinfly.databinding.ActivityRegisterBinding
import java.util.Date
import java.util.UUID

class RegisterActivity : AppCompatActivity() {

    private lateinit var userRepository: UserRepository
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val userDataSource = UserDataSource(this)
        userRepository = UserRepository(userDataSource)

        binding.registerBt.setOnClickListener{
            if (binding.emailText.text.isEmpty() || binding.claveEditText.text.isEmpty() || binding.usernameEditText.text.isEmpty()){
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
            }else{
                val newUser = UserModel(UUID.randomUUID(),binding.usernameEditText.text.toString(),binding.emailText.text.toString(),false,null, Date(),0,binding.claveEditText.text.toString())
                Log.d("newUser", newUser.toString())
                if(userRepository.registerUser(newUser)){

                }

            }

        }

        binding.loginBt.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }


    }
}