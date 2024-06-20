package com.example.dentalize.data.response

import com.google.gson.annotations.SerializedName

data class ItemHistoryResponse(

	@field:SerializedName("ItemHistoryResponse")
	val itemHistoryResponse: List<ItemHistoryResponseItem>
)

data class ItemHistoryResponseItem(

	@field:SerializedName("result")
	val result: String,

	@field:SerializedName("confidenceScore")
	val confidenceScore: String,

	@field:SerializedName("createdDate")
	val createdDate: CreatedDate,

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

data class CreatedDate(

	@field:SerializedName("_nanoseconds")
	val nanoseconds: Int,

	@field:SerializedName("_seconds")
	val seconds: Int
)
