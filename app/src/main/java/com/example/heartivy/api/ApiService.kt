package com.example.heartify.api

import com.example.heartify.model.PredictionRequest
import com.example.heartify.model.PredictionResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("predict")  // Ganti dengan path yang sesuai dari API Anda
    fun predictHealth(@Body predictionRequest: PredictionRequest): Call<PredictionResponse>
}
