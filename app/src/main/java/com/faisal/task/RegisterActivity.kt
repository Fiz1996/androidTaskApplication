package com.faisal.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    private val database = UserDatabase(this)
    private fun checkPhoneNumber(phoneNumber:String): Boolean {

        val d = database.checkPhoneNumber(phoneNumber)
        Log.d("TAG", " check number value is: $d")
        return d <= 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_acitivity)
        val email = findViewById<EditText>(R.id.email_input_register)
        val phoneNumber = findViewById<EditText>(R.id.phone_number_input_register)
        val name = findViewById<EditText>(R.id.register_name_text)
        val register = findViewById<Button>(R.id.register)


        register.setOnClickListener {
            val isPhoneNumberValid = checkPhoneNumber(phoneNumber.text.toString())

            when {
                isPhoneNumberValid -> {
                    database.insertUser(
                        name.text.toString(),
                        phoneNumber.text.toString(),
                        email.text.toString()
                    )
                }
             !isPhoneNumberValid -> Toast.makeText(this,"this phone number has been registered"+
             "please go back to the login page",Toast.LENGTH_SHORT).show()
            }

            finish()
        }

    }
}