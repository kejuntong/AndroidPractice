package com.kejuntong.practiceproject.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kejuntong.practiceproject.R
import com.kejuntong.practiceproject.api.TodoItem

class TodoListAdapter(private var todoList: List<TodoItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    internal fun refresh(todoList: List<TodoItem>) {
        this.todoList = todoList
        notifyDataSetChanged()
    }

    private var onItemClickListener: OnItemClickListener? = null
    internal fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    // A nested class marked as inner can access the members of its outer class.
    // Inner classes carry a reference to an object of an outer class
    private inner class TodoListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.title_text)
        val statusTextView: TextView = itemView.findViewById(R.id.status_text)

        // since TodoListViewHolder is inner class, it can get access to todoList
        fun bindView(position: Int) {
            bindView(todoList[position])
        }

        // if it's not an inner class, we need to use the below method
        fun bindView(todoItem: TodoItem) {
            titleTextView.text = todoItem.title
            statusTextView.text = if(todoItem.completed) "Completed" else "TODO"
            itemView.setOnClickListener {
                onItemClickListener?.onItemClicked(todoItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.view_todo_list_item, parent, false)
        return TodoListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TodoListViewHolder).bindView(position)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    interface OnItemClickListener {
        fun onItemClicked(todoItem: TodoItem)
    }
}