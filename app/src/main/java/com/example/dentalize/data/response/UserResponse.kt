package com.example.dentalize.data.response

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("userId")
	val userId: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String
)
