package com.hha.heinhtetaung.kotlinroomdb.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.hha.heinhtetaung.kotlinroomdb.R
import com.hha.heinhtetaung.kotlinroomdb.roomdb.AppDatabase
import android.arch.persistence.room.Room
import com.hha.heinhtetaung.kotlinroomdb.roomdb.entities.ToDoListEntity
import kotlinx.android.synthetic.main.activity_todo_list.*
import kotlinx.android.synthetic.main.item_todolist.*
import android.content.Intent
import android.support.design.widget.Snackbar


class TodoListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)

        var db = AppDatabase.getAppDataBase(this)!!.toDoListDao()


        btnAddtodb.setOnClickListener {
            var title = etTitle.text.toString()
            var content = etContent.text.toString()
            db.insertAll(ToDoListEntity(0, title, content))
            startActivity(Intent(this, MainActivity::class.java))

        }


    }
}
