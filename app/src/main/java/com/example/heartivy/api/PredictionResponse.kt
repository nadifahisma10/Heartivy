package com.example.heartify.api

import com.google.gson.annotations.SerializedName

data class PredictionResponse(

	@field:SerializedName("Alcohol_Consumption")
	val alcoholConsumption: Int? = null,

	@field:SerializedName("Exercise")
	val exercise: String? = null,

	@field:SerializedName("Heart_Disease")
	val heartDisease: String? = null,

	@field:SerializedName("Sex")
	val sex: String? = null,

	@field:SerializedName("Green_Vegetables_Consumption")
	val greenVegetablesConsumption: Int? = null,

	@field:SerializedName("Fruit_Consumption")
	val fruitConsumption: Int? = null,

	@field:SerializedName("FriedPotato_Consumption")
	val friedPotatoConsumption: Int? = null,

	@field:SerializedName("BMI")
	val bMI: Double? = null,

	@field:SerializedName("Smoking_History")
	val smokingHistory: String? = null
)
