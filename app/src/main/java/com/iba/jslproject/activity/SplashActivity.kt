package com.iba.jslproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.auth.FirebaseAuth
import com.iba.jslproject.R

class SplashActivity : AppCompatActivity() {

    lateinit var result: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                startChat()
            } else {
                //TODO: finish
            }
        }
    }

    fun startAuth() {
        val intent = Intent(applicationContext, AuthActivity::class.java)
        result.launch(intent)
    }

    override fun onResume() {
        super.onResume()
        FirebaseAuth.getInstance().currentUser
        if (FirebaseAuth.getInstance().currentUser == null) {
            startAuth()
        } else {
            startChat()
        }
    }

    fun startChat(){
        val startChat = Intent(applicationContext, ChatActivity::class.java)
        startActivity(startChat)
        finish()
    }
}