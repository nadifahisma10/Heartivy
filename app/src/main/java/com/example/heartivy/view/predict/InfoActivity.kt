package com.example.heartify.view.predict

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.heartify.data.UserHomeModel
import com.example.heartify.loginwithanimation.R

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        val userHomeModel = intent.getParcelableExtra<UserHomeModel>("USER")
    }
}