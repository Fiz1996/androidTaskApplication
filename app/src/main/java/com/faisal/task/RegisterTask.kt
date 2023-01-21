package com.faisal.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterTask : AppCompatActivity() {
    private val database = UserDatabase(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_task)
        val isCompleted = findViewById<EditText>(R.id.is_completed_text)
        val taskName = findViewById<EditText>(R.id.task_name_text)
        val register = findViewById<Button>(R.id.register_task)
        val phoneNumber: String? = intent.getStringExtra("phoneNumber")
        register.setOnClickListener{

            var taskIsCompleted = false
            if ( isCompleted.text.toString().equals("done")) {
                taskIsCompleted =true
            }

            database.insertTask(taskName.text.toString(),taskIsCompleted,phoneNumber)
            finish()
        }

    }
}