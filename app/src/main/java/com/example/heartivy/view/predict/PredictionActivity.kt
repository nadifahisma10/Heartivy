package com.example.heartify.view.predict

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.heartify.api.ApiConfig
import com.example.heartify.model.PredictionRequest
import com.example.heartify.model.PredictionResponse
import com.example.heartify.loginwithanimation.R
import com.example.heartify.view.result.ResultActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PredictionActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prediction)

        val predictButton = findViewById<Button>(R.id.btn_save)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        predictButton.setOnClickListener {
            try {
                val bmi = findViewById<EditText>(R.id.edt_bmi).text.toString().toInt()
                val sex = findViewById<Spinner>(R.id.edt_sex).selectedItem.toString()
                val smoking = findViewById<CheckBox>(R.id.edt_smoking).isChecked
                val alcohol = findViewById<EditText>(R.id.edt_alcohol).text.toString().toInt()
                val fruit = findViewById<EditText>(R.id.edt_fruit).text.toString().toInt()
                val vegetables = findViewById<EditText>(R.id.edt_vegetables).text.toString().toInt()
                val potato = findViewById<EditText>(R.id.edt_potato).text.toString().toInt()

                val predictionRequest = PredictionRequest(
                    bmi = bmi,
                    sex = sex,
                    smoking = smoking,
                    alcohol = alcohol,
                    fruit = fruit,
                    vegetables = vegetables,
                    potato = potato
                )

                // Tampilkan progress bar selama permintaan berlangsung
                progressBar.visibility = View.VISIBLE
                val apiService = ApiConfig.getApiService()
                apiService.predictHealth(predictionRequest).enqueue(object : Callback<PredictionResponse> {
                    override fun onResponse(call: Call<PredictionResponse>, response: Response<PredictionResponse>) {
                        // Sembunyikan progress bar setelah selesai
                        progressBar.visibility = View.GONE
                        if (response.isSuccessful) {
                            val prediction = response.body() // Ambil body dari response
                            if (prediction != null) {
                                val resultIntent = Intent(this@PredictionActivity, ResultActivity::class.java).apply {
                                    putExtra("HEALTH_STATUS", prediction.healthStatus)
                                }
                                startActivity(resultIntent)
                            } else {
                                Toast.makeText(this@PredictionActivity, "Empty response", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(this@PredictionActivity, "Prediction failed", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<PredictionResponse>, t: Throwable) {
                        // Sembunyikan progress bar jika terjadi kegagalan
                        progressBar.visibility = View.GONE
                        Toast.makeText(this@PredictionActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
            } catch (e: Exception) {
                Toast.makeText(this, "Invalid input: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
