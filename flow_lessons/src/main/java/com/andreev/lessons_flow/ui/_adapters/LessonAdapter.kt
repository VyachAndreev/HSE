package com.andreev.lessons_flow.ui._adapters

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andreev.data.utils.DateUtils
import com.andreev.data.models.Lesson
import com.andreev.lessons_flow.R

class LessonAdapter(var lessons: Array<Lesson>):
    RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    var onItemClick: ((id: String?) -> Unit)? = null

    inner class LessonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(lessons[adapterPosition].id)
            }
        }
        val numberTextView: TextView = itemView.findViewById(R.id.number_tv)
        val typeTextView: TextView = itemView.findViewById(R.id.type_tv)
        val timeTextView: TextView = itemView.findViewById(R.id.time_tv)
        val titleTextView: TextView = itemView.findViewById(R.id.title_tv)
        val descriptionTextView: TextView = itemView.findViewById(R.id.description_tv)
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
        with(holder) {
            val lesson = lessons[position]
            numberTextView.text = lesson.lesson_number_start.toString()
            typeTextView.text = lesson.type
            timeTextView.text=
                "${DateUtils.formatSimpleDate(lessons[position].date_start)}-${DateUtils.formatSimpleDate(lessons[position].date_end)}"
            titleTextView.text = lesson.discipline
            descriptionTextView.text = "${lesson.auditorium}, ${lesson.building}"
        }
    }

    override fun getItemCount(): Int = lessons.size
}

class VerticalSpaceDecoration(private val height: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.getChildAdapterPosition(view) != parent.adapter?.itemCount?.minus(1)) {
            outRect.bottom = height
        }
    }
}