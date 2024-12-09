// ResultActivity.kt
package com.example.loginapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.unirfp.examen.R

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val welcomeMessageTextView = findViewById<TextView>(R.id.welcomeMessageTextView)
        val email = intent.getStringExtra("EMAIL")
        val password = intent.getStringExtra("PASSWORD")

        welcomeMessageTextView.text = "Bienvenido\n\n User:\n\n$email\n\nPassword:\n\n$password"

    }
}