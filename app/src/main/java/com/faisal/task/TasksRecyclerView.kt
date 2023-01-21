package com.faisal.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TasksRecyclerView (
    private val tasks: List<TaskModel> ): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
            val context = parent.context
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.activity_task_item_layout, parent, false)

            return TasksViewHolder(view)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val user = tasks[position]

            if (holder is TasksViewHolder) holder.bind(user)
        }

        override fun getItemCount(): Int = tasks.size
    }
