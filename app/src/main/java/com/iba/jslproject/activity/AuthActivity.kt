package com.iba.jslproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.iba.jslproject.R
import com.iba.jslproject.databinding.ActivityAuthBinding
import timber.log.Timber

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_auth)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }

    private fun tryRegister() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        Timber.d("Creating a new user: $email + $password")
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful) {
                Timber.d("User created successfully: ${FirebaseAuth.getInstance().currentUser}")
                successfulAuth()
            } else {
                Timber.d("Error in user creation: ${it.exception.toString()}")
                if (it.exception is FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(applicationContext, (it.exception as FirebaseAuthInvalidCredentialsException).message.toString(), Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()
        binding.btnLogin.setOnClickListener {
            tryRegister()
        }
    }

    fun successfulAuth(){
        val resultIntent = Intent()
        setResult(RESULT_OK, resultIntent)
        finish()
    }
}