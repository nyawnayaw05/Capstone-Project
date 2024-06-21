package com.example.dentalize.data.response

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class PredictResponse(
	@field:SerializedName("listHistory")
	val listStory: List<ListHistory> = emptyList(),

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

@Entity(tableName = "listHistory")
@Parcelize
data class ListHistory(
	@field:SerializedName("result")
	@ColumnInfo("result")
	val result: String,

	@field:SerializedName("confidenceScore")
	@ColumnInfo("confidenceScore")
	val confidenceScore: String,

	@field:SerializedName("createdDate")
	@ColumnInfo("createDate")
	val createdDate: String,

	@field:SerializedName("suggestion")
	@ColumnInfo("suggestion")
	val suggestion: String,

	@field:SerializedName("imageUrl")
	@ColumnInfo("imageUrl")
	val imageUrl: String,

	@field:SerializedName("cause")
	@ColumnInfo("cause")
	val cause: String,

	@field:SerializedName("explanation")
	@ColumnInfo("explanation")
	val explanation: String,

	@field:SerializedName("predictionId")
	@ColumnInfo("predictionId")
	val predictionId: String,

	@field:SerializedName("prevention")
	@ColumnInfo("prevention")
	val prevention: String
):Parcelable
