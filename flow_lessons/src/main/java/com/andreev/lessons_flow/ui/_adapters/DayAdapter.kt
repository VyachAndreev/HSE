package com.andreev.lessons_flow.ui._adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andreev.data.models.Lesson
import com.andreev.data.utils.DateUtils
import com.andreev.lessons_flow.R
import timber.log.Timber
import java.util.*

class DayAdapter(var lessons: Map<Date, List<Lesson>> = mapOf())
    : RecyclerView.Adapter<DayAdapter.DayViewHolder>() {
    var onItemClick: ((id: String?) -> Unit)? = null

    private val keys = lessons.keys.sorted().toList()

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
        Timber.i("OnBind")
        if (keys.isNotEmpty()) {
            val key = keys[position]
            with(holder) {
                Timber.i("key: $key")
                recyclerAdapter = lessons[key]?.let { LessonAdapter(it.toTypedArray()) }!!
                titleTextView.text = DateUtils.formatSimpleDateDay(key)
                with(recyclerView) {
                    layoutManager = LinearLayoutManager(context)
                    adapter = recyclerAdapter
                    addItemDecoration(VerticalSpaceDecoration(2))
                }
                recyclerAdapter.onItemClick = {
                    onItemClick?.invoke(it)
                }
            }
        }
    }

    override fun getItemCount(): Int = lessons.size
}