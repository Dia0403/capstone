package com.example.front

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.front.RetrofitInstance.api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ✅ 회원가입 버튼
        val registerButton = findViewById<Button>(R.id.registerLink)
        registerButton.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        // ✅ 로그인 버튼
        val loginButton = findViewById<Button>(R.id.loginButton)
        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            val loginRequest = LoginRequest(username, password)

            api.login(loginRequest).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        val loginResponse = response.body()
                        val role = loginResponse?.role

                        Toast.makeText(this@MainActivity, "로그인 성공!", Toast.LENGTH_SHORT).show()

                        // ✅ 역할에 따라 홈 화면 분기
                        val intent = when (role) {
                            "guardian" -> Intent(this@MainActivity, CareGiver_HomeActivity::class.java)
                            "senior" -> Intent(this@MainActivity, Senior_HomeActivity::class.java)

                            else -> {
                                Toast.makeText(this@MainActivity, "알 수 없는 사용자 역할입니다.", Toast.LENGTH_SHORT).show()
                                return
                            }
                        }

                        startActivity(intent)
                        finish()  // ← 뒤로 가기 방지
                    } else {
                        Toast.makeText(this@MainActivity, "아이디 또는 비밀번호가 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "서버 연결 실패: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
