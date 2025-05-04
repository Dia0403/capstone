package com.example.front

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.front.SeniorEmergencyCallActivity

class ConnectionCompleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connection_complete)

        val btnConfirm = findViewById<Button>(R.id.btnConfirm)
        btnConfirm.setOnClickListener {
            val intent = Intent(this, SeniorEmergencyCallActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}
