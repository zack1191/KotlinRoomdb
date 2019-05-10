package com.hha.heinhtetaung.kotlinroomdb.adapters

import android.arch.persistence.room.Room
import android.content.Context
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.hha.heinhtetaung.kotlinroomdb.roomdb.entities.ToDoListEntity
import com.hha.heinhtetaung.kotlinroomdb.viewholders.TodoListViewholder
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import com.hha.heinhtetaung.kotlinroomdb.R
import com.hha.heinhtetaung.kotlinroomdb.R.id.*
import com.hha.heinhtetaung.kotlinroomdb.activities.MainActivity
import com.hha.heinhtetaung.kotlinroomdb.activities.TodoListActivity
import com.hha.heinhtetaung.kotlinroomdb.roomdb.AppDatabase
import com.hha.heinhtetaung.kotlinroomdb.roomdb.Daos.ToDoListDao
import kotlinx.android.synthetic.main.activity_todo_list.*
import kotlinx.android.synthetic.main.item_todolist.view.*


class TodoListAdapter() : RecyclerView.Adapter<TodoListViewholder>(), Parcelable {

    private lateinit var todolist: List<ToDoListEntity>
    private lateinit var context: Context
    private lateinit var db: ToDoListDao

    constructor(parcel: Parcel) : this()


    constructor(todolist: List<ToDoListEntity>, context: Context) : this() {

        this.context = context
        db = AppDatabase.getAppDataBase(context)!!.toDoListDao()
        this.todolist = db.getTodoList()
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TodoListViewholder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_todolist, p0, false)
        return TodoListViewholder(view)
    }

    override fun getItemCount(): Int {
        return todolist.size
    }

    override fun onBindViewHolder(p0: TodoListViewholder, p1: Int) {
        p0.itemView.tvTitle.text = todolist[p1].getTitle()
        p0.itemView.tvContent.text = todolist[p1].getContent()

        p0.itemView.btnUpdate.setOnClickListener {

            Snackbar.make(it, "Testing", Snackbar.LENGTH_INDEFINITE).show()


            p0.itemView.tvTitle.text = todolist[p1].getTitle()
            p0.itemView.tvContent.text = todolist[p1].getContent()

            var titleUpdate = p0.itemView.etTitleUpdate.text.toString()
            var contentUpdate = p0.itemView.etContentUpdate.text.toString()

            var entity = ToDoListEntity(todolist[p1].id, titleUpdate, contentUpdate)
//          db.update(entity)
            db.insertAll(entity)

            this.todolist = db.getTodoList()
            notifyDataSetChanged()

        }

        p0.itemView.btnDelete.setOnClickListener {

            db.deleteQuery(todolist[p1].id)
            this.todolist = db.getTodoList()
            notifyDataSetChanged()
        }


    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }


    companion object CREATOR : Parcelable.Creator<TodoListAdapter> {
        override fun createFromParcel(parcel: Parcel): TodoListAdapter {
            return TodoListAdapter(parcel)
        }

        override fun newArray(size: Int): Array<TodoListAdapter?> {
            return arrayOfNulls(size)
        }
    }

}