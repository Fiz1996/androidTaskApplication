package com.faisal.task

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class TasksViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name = view.findViewById<TextView>(R.id.name)
    private val root = view.findViewById<ConstraintLayout>(R.id.root)

    fun bind(user: TaskModel) {
        name.text = user.name

        root.setOnClickListener {
            Log.d("TAG", "bind: CLICKED!!")
        }
    }
}