package com.faisal.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class RegisterActivity : AppCompatActivity() {
    private val database = UserDatabase(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_acitivity)
        val email = findViewById<EditText>(R.id.email_input_register)
        val phoneNumber = findViewById<EditText>(R.id.phone_number_input_register)
        val name = findViewById<EditText>(R.id.register_name_text)
        val register = findViewById<Button>(R.id.register)


        register.setOnClickListener {
            database.insertUser(name.text.toString(),phoneNumber.text.toString(),email.text.toString())
            finish()
        }

    }
}