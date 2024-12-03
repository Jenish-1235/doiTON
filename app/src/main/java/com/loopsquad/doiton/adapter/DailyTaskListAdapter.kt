package com.loopsquad.doiton.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.loopsquad.doiton.R
import com.loopsquad.doiton.models.Task

class DailyTaskListAdapter(context: Context, taskList: ArrayList<Task>): RecyclerView.Adapter<DailyTaskListAdapter.ViewHolder>() {
    var taskList = taskList
    var context = context

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val taskName = view.findViewById<TextView>(R.id.taskName)
        val taskDescription = view.findViewById<TextView>(R.id.taskDescription)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DailyTaskListAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_daily_task, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DailyTaskListAdapter.ViewHolder, position: Int) {
        holder.taskName.text = taskList[position].taskName
        holder.taskDescription.text = taskList[position].taskDescription
        holder.itemView.setOnClickListener {
            Toast.makeText(context, taskList[position].taskName, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}