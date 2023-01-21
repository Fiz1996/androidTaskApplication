package com.faisal.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

class TaskActivity : AppCompatActivity() {
    private val database = UserDatabase(this)
    private lateinit var adapter: TasksRecyclerView
    private lateinit var usersList: RecyclerView
    private lateinit var userInput: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)



        usersList = findViewById(R.id.user_tasks)
        userInput = findViewById(R.id.user_name)
        val addButton = findViewById<Button>(R.id.add)

        listAdapter()

        addButton.setOnClickListener {


            database.insertTask(name = userInput.text.toString())
            userInput.setText("")

            listAdapter()
        }
    }

    private fun listAdapter() {
        adapter = TasksRecyclerView(database.getTasks())

        usersList.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
//        listAdapter()
    }
    }
