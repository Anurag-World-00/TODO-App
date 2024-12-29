package com.codinghits.viewmodel

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface TodoDao {

    @Query("SELECT * FROM Todo")
    fun getAllTodo() : LiveData<List<Todo>>

    @Insert
    fun addAllTodo(todo : Todo)


@Query("DELETE FROM TODO where id = :id")
    fun deleteAllTodo(id : Int)

}