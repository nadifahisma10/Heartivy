package com.example.heartify.view.main

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.heartify.loginwithanimation.databinding.ActivityMainBinding
import com.example.heartify.loginwithanimation.R
import com.example.heartify.view.checking.CheckingActivity
import com.example.heartify.view.predict.InfoActivity
import com.example.heartify.view.predict.PredictionActivity
import com.example.heartify.view.welcome.WelcomeActivity

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupToolbar()
        setupAction()
        playAnimation()

        viewModel.getSession().observe(this) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()
            }
        }

        // Observe loading state
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        // Observe error message
        viewModel.errorMessage.observe(this) { message ->
            message?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            @Suppress("DEPRECATION")
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu) // Inflate menu dari main_menu.xml
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                // Kirim Intent ke CheckingActivity dengan data tambahan
                val intent = Intent(this, CheckingActivity::class.java)
                intent.putExtra("EXTRA_DATA", "Data dari MainActivity")
                startActivity(intent)
                return true
            }
            R.id.action_info -> {
                // Kirim Intent ke CheckingActivity dengan data tambahan
                val intent = Intent(this, InfoActivity::class.java)
                intent.putExtra("EXTRA_DATA", "Data dari MainActivity")
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupAction() {
        binding.imageDoctor1.setOnClickListener {
            Toast.makeText(this, "Doctor 1 clicked", Toast.LENGTH_SHORT).show()
        }

        binding.imageDoctor2.setOnClickListener {
            Toast.makeText(this, "Doctor 2 clicked", Toast.LENGTH_SHORT).show()
        }

        binding.imageDoctor3.setOnClickListener {
            Toast.makeText(this, "Doctor 3 clicked", Toast.LENGTH_SHORT).show()
        }

        binding.logoutButton.setOnClickListener {
            viewModel.logout()
            val intent = Intent(this, WelcomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        binding.fabAddChecking.setOnClickListener {
            val intent = Intent(this, PredictionActivity::class.java).apply {}
            startActivity(intent)
        }
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val dokter = ObjectAnimator.ofFloat(binding.dokter, View.ALPHA, 1f).setDuration(100)
        val quote = ObjectAnimator.ofFloat(binding.Text1, View.ALPHA, 1f).setDuration(100)

        val image1 = ObjectAnimator.ofFloat(binding.imageDoctor1, View.ALPHA, 1f).setDuration(100)
        val name1 = ObjectAnimator.ofFloat(binding.nameText1, View.ALPHA, 1f).setDuration(100)

        val image2 = ObjectAnimator.ofFloat(binding.imageDoctor2, View.ALPHA, 1f).setDuration(100)
        val name2 = ObjectAnimator.ofFloat(binding.nameText2, View.ALPHA, 1f).setDuration(100)

        val image3 = ObjectAnimator.ofFloat(binding.imageDoctor3, View.ALPHA, 1f).setDuration(100)
        val name3 = ObjectAnimator.ofFloat(binding.nameText3, View.ALPHA, 1f).setDuration(100)

        val name = ObjectAnimator.ofFloat(binding.nameTextView, View.ALPHA, 1f).setDuration(100)
        val message = ObjectAnimator.ofFloat(binding.messageTextView, View.ALPHA, 1f).setDuration(100)
        val logout = ObjectAnimator.ofFloat(binding.logoutButton, View.ALPHA, 1f).setDuration(100)

        AnimatorSet().apply {
            playSequentially(name, message, logout, dokter, quote, image1, name1, image2, name2, image3, name3)
            startDelay = 100
        }.start()


    }
}
