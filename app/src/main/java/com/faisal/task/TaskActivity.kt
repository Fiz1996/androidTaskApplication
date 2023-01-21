package com.faisal.task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class TaskActivity : AppCompatActivity() {
    private val database = UserDatabase(this)
    private lateinit var adapter: TasksRecyclerView
    private lateinit var usersList: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)



        usersList = findViewById(R.id.user_tasks)
        val addButton = findViewById<Button>(R.id.add)
        var phoneNumber = intent.getStringExtra("phoneNumber")
        Toast.makeText(this, "phone number is $phoneNumber", Toast.LENGTH_SHORT).show()
        listAdapter(phoneNumber)

        addButton.setOnClickListener {
            Toast.makeText(this, "phone number is $phoneNumber", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, RegisterTask::class.java)
            intent.putExtra("phoneNumber",phoneNumber)
            startActivity(intent)
        }
    }

    private fun listAdapter(phoneNumber:String?) {
        adapter = TasksRecyclerView(database.getTasks(phoneNumber))

        usersList.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
    }
    }
