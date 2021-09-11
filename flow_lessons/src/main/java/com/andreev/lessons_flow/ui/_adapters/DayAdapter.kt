package com.andreev.lessons_flow.ui._adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andreev.data.models.Lesson
import com.andreev.lessons_flow.R

class DayAdapter(
    private val dayNames: Array<String>,
    private val lessons: Array<Array<Lesson>>,
) : RecyclerView.Adapter<DayAdapter.DayViewHolder>() {
    var onItemClick: ((id: String?) -> Unit)? = null

    inner class DayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var recyclerAdapter: LessonAdapter
        val titleTextView: TextView = itemView.findViewById(R.id.day_tv)
        val recyclerView: RecyclerView = itemView.findViewById(R.id.item_recycler)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        return DayViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_day,
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        with(holder) {
            recyclerAdapter = LessonAdapter(lessons[position])
            titleTextView.text = dayNames[position]
            with(recyclerView) {
                layoutManager = LinearLayoutManager(context)
                adapter = recyclerAdapter
            }
            recyclerAdapter.onItemClick = {
                onItemClick?.invoke(it)
            }
        }
    }

    override fun getItemCount(): Int = dayNames.size
}