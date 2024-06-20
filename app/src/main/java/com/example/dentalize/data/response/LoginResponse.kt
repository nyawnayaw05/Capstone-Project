package com.example.dentalize.data.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("userId")
	val userId: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String,

	@field:SerializedName("token")
	val token: String
)
