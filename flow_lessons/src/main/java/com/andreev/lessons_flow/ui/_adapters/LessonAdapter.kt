package com.andreev.lessons_flow.ui._adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andreev.data.models.Lesson
import com.andreev.lessons_flow.R

class LessonAdapter(var lessons: Array<Lesson>)
    : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    inner class LessonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.title_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_lesson,
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.titleTextView.text = lessons[position].discipline
    }

    override fun getItemCount(): Int = lessons.size
}