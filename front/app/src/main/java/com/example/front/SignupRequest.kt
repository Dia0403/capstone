package com.example.front

data class SignupRequest(
    val username: String,
    val password: String,
    val email: String,
    val phone: String
)
