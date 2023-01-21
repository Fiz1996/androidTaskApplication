package com.faisal.task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private val database = UserDatabase(this)
    private fun checkPhoneNumber(phoneNumber:String): Boolean {

        val d = database.checkPhoneNumber(phoneNumber)
        Log.d("TAG", " check number value is: $d")
        return d >= 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var loginButton = findViewById<Button>(R.id.login)
        val phoneNumberInput = findViewById<EditText>(R.id.phone_number_input)
        val signUp = findViewById<Button>(R.id.signup)

        loginButton.setOnClickListener{
            val phoneNumber =  phoneNumberInput.text.toString()
            val isPhoneNumberValid = checkPhoneNumber(phoneNumber)

            when {
                  isPhoneNumberValid -> {
                    val intent = Intent(this, TaskActivity::class.java)
                    intent.putExtra("phoneNumber", phoneNumber)
                    startActivity(intent)
                }
                !isPhoneNumberValid -> Toast.makeText(this, "phoneNumber does not exists please go back to registration page", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(this, "Password is wrong", Toast.LENGTH_SHORT).show()
            }
        }





        signUp.setOnClickListener{
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}