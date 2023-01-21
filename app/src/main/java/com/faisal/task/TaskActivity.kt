package com.faisal.task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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

        listAdapter()

        addButton.setOnClickListener {
            val intent = Intent(this, RegisterTask::class.java)
            startActivity(intent)
        }
    }

    private fun listAdapter() {
        adapter = TasksRecyclerView(database.getTasks())

        usersList.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
    }
    }
