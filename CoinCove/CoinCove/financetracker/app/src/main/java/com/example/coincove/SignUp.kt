package com.example.coincove

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Patterns

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)

        // Apply edge-to-edge insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get references to input fields
        val emailInput = findViewById<EditText>(R.id.email_input)
        val nameInput = findViewById<EditText>(R.id.name_input)
        val passwordInput = findViewById<EditText>(R.id.password_input)

        // Get reference to the Register button (here, id "login_button" is used for register)
        val registerButton = findViewById<Button>(R.id.login_button)


        registerButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val username = nameInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            // Simple validation
            if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Email validation
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Save credentials to SharedPreferences
            val sharedPref = getSharedPreferences("UserCredentials", MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("email", email)
                putString("username", username)
                putString("password", password)
                apply()
            }

            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
            // Navigate to SignIn activity
            startActivity(Intent(this, SignIn::class.java))
            finish()
        }


    }
}
