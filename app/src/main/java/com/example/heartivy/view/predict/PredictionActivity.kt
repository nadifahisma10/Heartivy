package com.example.heartify.view.predict

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.heartify.loginwithanimation.databinding.ActivityPredictionBinding

class PredictionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPredictionBinding
    private lateinit var predictionHelper: PredictionHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPredictionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Disable the predict button until the model is loaded
        binding.btnPredict.isEnabled = false
        binding.progressBar.visibility = View.VISIBLE

        predictionHelper = PredictionHelper(
            context = this,
            onResult = { result ->
                binding.tvResult.text = result
                binding.progressBar.visibility = View.GONE
            },
            onError = { errorMessage ->
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.GONE
            },
            onDownloadSuccess = {
                binding.btnPredict.isEnabled = true
                binding.progressBar.visibility = View.GONE
            }
        )

        // Set onClickListener for the predict button
        binding.btnPredict.setOnClickListener {
            val input = binding.edSales.text.toString()

            if (input.isNotBlank()) {
                try {
                    predictionHelper.predict(input)
                } catch (e: IllegalArgumentException) {
                    Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter valid input", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

