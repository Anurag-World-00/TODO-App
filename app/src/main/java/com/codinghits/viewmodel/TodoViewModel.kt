package com.codinghits.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date


class TodoViewModel: ViewModel() {

val todoDao = MainApplication.todoDatabase.getAllTodo()
            val todoList : LiveData<List<Todo>> = todoDao.getAllTodo()


    fun addAllTodo(title : String){
viewModelScope.launch(Dispatchers.IO) {
    todoDao.addAllTodo(Todo(title = title , createdAi = Date.from(Instant.now())))
}
    }
    fun deleteTodo(id : Int){
        viewModelScope.launch (Dispatchers.IO){
            todoDao.deleteAllTodo(id)
        }

    }
}