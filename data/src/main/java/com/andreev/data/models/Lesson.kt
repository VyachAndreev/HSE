package com.andreev.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Lesson(
    @PrimaryKey(autoGenerate = true)
    val id: String?,
    val building: String?,
    val type: String?,
    val lecturer: String?,
    val lecturer_id: Int?,
    val stream: String?,
    val auditorium: String?,
    val auditorium_id: Int?,
    val date_start: Date?,
    val date_end: Date?,
    val created_at: Date?,
    val updated_at: Date?,
    val importance_level: Byte?,
    val building_id: Int?,
    val city: String?,
    val discipline: String?,
    val discipline_link: String?,
    val duration: Array<Int>?,
    val note: String?,
    val stream_links: Array<Property>?,
    val lesson_number_start: Byte?,
    val hash: String?,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Lesson

        if (id != other.id) return false
        if (building != other.building) return false
        if (type != other.type) return false
        if (lecturer != other.lecturer) return false
        if (lecturer_id != other.lecturer_id) return false
        if (stream != other.stream) return false
        if (auditorium != other.auditorium) return false
        if (auditorium_id != other.auditorium_id) return false
        if (date_start != other.date_start) return false
        if (date_end != other.date_end) return false
        if (created_at != other.created_at) return false
        if (updated_at != other.updated_at) return false
        if (importance_level != other.importance_level) return false
        if (building_id != other.building_id) return false
        if (city != other.city) return false
        if (discipline != other.discipline) return false
        if (discipline_link != other.discipline_link) return false
        if (duration != null) {
            if (other.duration == null) return false
            if (!duration.contentEquals(other.duration)) return false
        } else if (other.duration != null) return false
        if (note != other.note) return false
        if (stream_links != null) {
            if (other.stream_links == null) return false
            if (!stream_links.contentEquals(other.stream_links)) return false
        } else if (other.stream_links != null) return false
        if (hash != other.hash) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (building?.hashCode() ?: 0)
        result = 31 * result + (type?.hashCode() ?: 0)
        result = 31 * result + (lecturer?.hashCode() ?: 0)
        result = 31 * result + (lecturer_id ?: 0)
        result = 31 * result + (stream?.hashCode() ?: 0)
        result = 31 * result + (auditorium?.hashCode() ?: 0)
        result = 31 * result + (auditorium_id ?: 0)
        result = 31 * result + (date_start?.hashCode() ?: 0)
        result = 31 * result + (date_end?.hashCode() ?: 0)
        result = 31 * result + (created_at?.hashCode() ?: 0)
        result = 31 * result + (updated_at?.hashCode() ?: 0)
        result = 31 * result + (importance_level ?: 0)
        result = 31 * result + (building_id ?: 0)
        result = 31 * result + (city?.hashCode() ?: 0)
        result = 31 * result + (discipline?.hashCode() ?: 0)
        result = 31 * result + (discipline_link?.hashCode() ?: 0)
        result = 31 * result + (duration?.contentHashCode() ?: 0)
        result = 31 * result + (note?.hashCode() ?: 0)
        result = 31 * result + (stream_links?.contentHashCode() ?: 0)
        result = 31 * result + (hash?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Lesson(id=$id, building=$building, type=$type, lecturer=$lecturer, lecturer_id=$lecturer_id, stream=$stream, auditorium=$auditorium, auditorium_id=$auditorium_id, date_start=$date_start, date_end=$date_end, created_at=$created_at, updated_at=$updated_at, importance_level=$importance_level, building_id=$building_id, city=$city, discipline=$discipline, discipline_link=$discipline_link, duration=${duration?.contentToString()}, note=$note, stream_links=${stream_links?.contentToString()}, hash=$hash)"
    }


}

data class Property(
    val link: String?,
    val description: String?,
)