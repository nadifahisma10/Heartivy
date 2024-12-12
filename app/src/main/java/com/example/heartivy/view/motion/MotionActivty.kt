package com.example.heartify.view.motion

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Button
import com.example.heartify.loginwithanimation.R
import com.example.heartify.view.main.MainActivity

class MotionActivty : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motion)

        setupView()

        // Tambahkan listener untuk button
        val buttonIntent = findViewById<Button>(R.id.button_intent)
        buttonIntent.setOnClickListener {
            // Berpindah ke MainActivity
            val intent = Intent(this@MotionActivty, MainActivity::class.java)
            startActivity(intent)
            finish() // Mengakhiri aktivitas ini
        }
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

}
