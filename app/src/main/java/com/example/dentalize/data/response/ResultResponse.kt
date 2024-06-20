package com.example.dentalize.data.response

import com.google.gson.annotations.SerializedName

data class  ResultResponse(

	@field:SerializedName("result")
	val result: String,

	@field:SerializedName("confidenceScore")
	val confidenceScore: String,

	@field:SerializedName("createdDate")
	val createdDate: String,

	@field:SerializedName("suggestion")
	val suggestion: String,

	@field:SerializedName("imageUrl")
	val imageUrl: String,

	@field:SerializedName("cause")
	val cause: String,

	@field:SerializedName("explanation")
	val explanation: String,

	@field:SerializedName("predictionId")
	val predictionId: String,

	@field:SerializedName("prevention")
	val prevention: String
)
