package com.daedongyeojido.daedae.feature.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.daedongyeojido.daedae.MainActivity
import com.daedongyeojido.daedae.databinding.ActivityLoginBinding
import com.daedongyeojido.daedae.feature.login.model.LoginRequest
import com.daedongyeojido.daedae.feature.login.model.LoginResponse
import com.daedongyeojido.daedae.network.ApiProvider
import com.daedongyeojido.daedae.network.AuthApi
import com.daedongyeojido.daedae.util.accessToken
import com.daedongyeojido.daedae.util.part
import com.daedongyeojido.daedae.util.refreshToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val id = binding.editLoginId.text
            val pw = binding.editLoginPw.text
            if (id != null && pw != null) {
                login(id.toString(), pw.toString())
            }
        }
    }

    private fun login(id: String, pw: String) {
        val apiProvider = ApiProvider.getRetrofit().create(AuthApi::class.java)
        apiProvider.login(LoginRequest(id, pw)).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.d("server", response.code().toString())
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if(responseBody != null) {
                        accessToken = responseBody.accessToken
                        refreshToken = responseBody.refreshToken
                        part = responseBody.part
                    }
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Log.d("server", response.message().toString())
                    Toast.makeText(this@LoginActivity, "아이디나 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "서버 연동 실패", Toast.LENGTH_SHORT).show()
                Log.d("server", t.message.toString())
            }
        })
    }
}