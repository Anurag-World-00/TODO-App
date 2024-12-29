package com.codinghits.viewmodel

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters


@Database(entities = [Todo::class], version = 2)
@TypeConverters(Converter::class)
abstract class TodoDatabase : RoomDatabase(){
    companion object{
        const val NAME = "TODO_DB"
    }
    abstract fun getAllTodo() : TodoDao
}