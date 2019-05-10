package com.hha.heinhtetaung.kotlinroomdb.activities

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.hha.heinhtetaung.kotlinroomdb.R
import com.hha.heinhtetaung.kotlinroomdb.adapters.TodoListAdapter
import com.hha.heinhtetaung.kotlinroomdb.roomdb.AppDatabase
import com.hha.heinhtetaung.kotlinroomdb.roomdb.Daos.ToDoListDao
import com.hha.heinhtetaung.kotlinroomdb.roomdb.entities.ToDoListEntity
import com.hha.heinhtetaung.kotlinroomdb.viewholders.TodoListViewholder

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.item_todolist.*


class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: TodoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        var db = AppDatabase.getAppDataBase(this)!!.toDoListDao()

        val users = db.getTodoList()

        rvTodo.layoutManager = LinearLayoutManager(this)
        mAdapter = TodoListAdapter(users, this)
        rvTodo.adapter = mAdapter


        fab.setOnClickListener {
            var intent = Intent(this, TodoListActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
