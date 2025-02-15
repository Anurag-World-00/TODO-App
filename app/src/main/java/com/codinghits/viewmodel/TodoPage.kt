package com.codinghits.viewmodel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun TodoPage( viewModel: TodoViewModel) {
    val todoList by viewModel.todoList.observeAsState()
    var todoValue by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding( 10.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(
                value = todoValue,
                onValueChange = {
                    todoValue = it
                }
            )
            Button(onClick = {
                viewModel.addAllTodo(todoValue)
                todoValue = ""
            }, modifier = Modifier.background(Color(0xFF435D97), shape = CircleShape), colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,

            ), shape = CircleShape) {
                Text(text = "Add")
            }

        }
        todoList?.let {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                itemsIndexed(it) { index: Int, item: Todo ->
                    TodoItem(item = item , onDelete = {
                        viewModel.deleteTodo(item.id)
                    })
                }
            }
        } ?: Text(
            text = "No Items Yet",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )

    }

}

@Composable
fun TodoItem(item: Todo, onDelete :()-> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF435D97))
            .padding(16.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = SimpleDateFormat("HH:mm:aa, dd/mm", Locale.ENGLISH).format(item.createdAi),
                fontSize = 10.sp,
                color = Color.LightGray
            )
            Text(text = item.title, fontSize = 20.sp, color = Color.White)
        }
        IconButton(onClick = onDelete) {
            Icon(imageVector = Icons.Rounded.Delete, contentDescription = null, tint = Color.White)
        }
    }
}