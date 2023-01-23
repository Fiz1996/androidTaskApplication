package com.faisal.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast

class RegisterTask : AppCompatActivity() {
    private val database = UserDatabase(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_task)
        val isCompleted = findViewById<RadioButton>(R.id.isCompleted)
        val taskName = findViewById<EditText>(R.id.task_name_text)
        val register = findViewById<Button>(R.id.register_task)
        val phoneNumber: String? = intent.getStringExtra("phoneNumber")

        isCompleted.setOnClickListener{
            Log.d("TAG", "onCreate: ${isCompleted.isChecked}")
//            isCompleted.isChecked = !isCompleted.isChecked
//            if(!isCompleted.isChecked) {
//                isCompleted.isChecked=true
//                radioButton = true
//            }
//            else if (isCompleted.isChecked) {
//                isCompleted.isChecked= false
//                radioButton = false
//            }
        }
        register.setOnClickListener{

            database.insertTask(taskName.text.toString(),isCompleted.isChecked,phoneNumber)
            finish()
        }

    }
}