package com.example.dentalize.data.response

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class HistoryResponse(
	@field:SerializedName("listStory")
	val listStory: List<PredictResponse> = emptyList(),

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

@Entity(tableName = "listStory")
@Parcelize
data class PredictResponse(
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
):Parcelable
