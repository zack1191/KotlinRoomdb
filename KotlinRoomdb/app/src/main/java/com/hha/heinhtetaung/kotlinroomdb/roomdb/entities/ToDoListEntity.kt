package com.hha.heinhtetaung.kotlinroomdb.roomdb.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "todolist")
class ToDoListEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int = 0,
        @ColumnInfo(name = "title")
        private var title: String, @ColumnInfo(name = "content")
        private var content: String) {

    fun getTitle(): String {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getContent(): String {
        return content
    }

    fun setContent(content: String) {
        this.content = content

    }

}



