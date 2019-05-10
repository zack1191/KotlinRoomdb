package com.hha.heinhtetaung.kotlinroomdb.roomdb.Daos

import android.arch.persistence.room.*
import com.hha.heinhtetaung.kotlinroomdb.roomdb.entities.ToDoListEntity

@Dao
interface ToDoListDao {
    @Query("Select * from todolist")
    fun getTodoList(): List<ToDoListEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg todo: ToDoListEntity)

    @Update
    fun update(vararg todoUpdate: ToDoListEntity)

//    @Query("UPDATE todolist SET title=:title AND content=:cont WHERE id=:id")
//    fun UpdateQuery(vararg title:String,cont:String,id:String)

    @Query("DELETE FROM todolist WHERE id=:id")
    fun deleteQuery(vararg id: Int)

}