package com.example.heartify.view.predict

import android.content.Context
import android.content.res.AssetFileDescriptor
import java.io.IOException
import org.tensorflow.lite.Interpreter
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class PredictionHelper(
    private val context: Context,
    private val onResult: (String) -> Unit,
    private val onError: (String) -> Unit,
    private val onDownloadSuccess: () -> Unit
) {
    private lateinit var tflite: Interpreter

    init {
        loadModel()
    }

    private fun loadModelFile(): MappedByteBuffer {
        val assetFileDescriptor: AssetFileDescriptor = context.assets.openFd("model.tflite")
        val fileInputStream = assetFileDescriptor.createInputStream()
        val fileChannel = fileInputStream.channel
        val startOffset = assetFileDescriptor.startOffset
        val declaredLength = assetFileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    private fun loadModel() {
        try {
            val modelBuffer = loadModelFile()
            tflite = Interpreter(modelBuffer)
            onDownloadSuccess()
        } catch (e: IOException) {
            onError("Failed to load model: ${e.localizedMessage}")
        }
    }

    fun predict(input: String) {
        try {
            val inputArray = parseInput(input)
            val outputArray = Array(1) { FloatArray(3) } // Dimensi output sesuai model Anda
            tflite.run(inputArray, outputArray)

            val resultIndex = outputArray[0].indexOfMax()
            val result = resultIndex?.let { "Tingkat Kesehatan Jantung: ${getResult(it)}" }
            onResult(result ?: "No result")
        } catch (e: Exception) {
            onError("Prediction failed: ${e.localizedMessage}")
        }
    }

    private fun parseInput(input: String): Array<FloatArray> {
        val inputArray = floatArrayOf(input.toFloat())
        return arrayOf(inputArray)
    }

    private fun getResult(index: Int): String {
        return when (index) {
            0 -> "Tinggi"
            1 -> "Sedang"
            2 -> "Rendah"
            else -> "Tidak diketahui"
        }
    }
}

// Ekstensi untuk mencari indeks nilai maksimum pada array Float
fun FloatArray.indexOfMax(): Int? = this.indices.maxByOrNull { this[it] }



