package com.unirfp.ae_1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // se crean varuiables de la vista
        val usernameEditText = findViewById<EditText>(R.id.etUsername)
        val passwordEditText = findViewById<EditText>(R.id.etPassword)
        val loginButton = findViewById<Button>(R.id.btnLogin)
        //se le da funcionalidad al boton
        loginButton.setOnClickListener {
            // Obtener los valores de los campos de entrada
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()


            // Ir a MainActivity si las credenciales son validas
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
