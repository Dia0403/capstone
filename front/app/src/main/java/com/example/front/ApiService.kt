package com.example.front

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/signup")
    fun signup(@Body request: SignupRequest): Call<ResponseBody>

    @POST("/login")
    fun login(@Body request: LoginRequest): Call<ResponseBody>
}
