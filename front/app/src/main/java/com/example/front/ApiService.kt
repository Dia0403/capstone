package com.example.front

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/api/signup")  // ✅ 수정됨
    fun signup(@Body request: SignupRequest): Call<ResponseBody>

    @POST("/api/login")   // ✅ 이것도 같이 수정
    fun login(@Body request: LoginRequest): Call<ResponseBody>
}
