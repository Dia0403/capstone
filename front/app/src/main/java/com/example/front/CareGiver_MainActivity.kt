package com.example.front

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class CareGiver_MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caregiver_home) // ← XML 제대로 연결되어야 함
    }
}