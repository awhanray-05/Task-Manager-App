package com.example.taskmanager

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskmanager.data.Task

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeView(navController: NavController, viewModel: TaskViewModel) {
//    val colorDefault = painterResource(R.color.app_background_color)

    Scaffold(
        topBar = { AppBar(title = "Task List", {}) },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(16.dp),
                contentColor = Color.White,
                backgroundColor = Color.Black,
                onClick = {
                    // TODO Add navigation to Add screen
                    navController.navigate(Screen.AddEditScreen.route + "/0L")
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) {
        val taskList = viewModel.getAllTasks.collectAsState(initial = listOf())
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(it)
            .background(color = colorResource(R.color.app_background_color))) {
            //  TODO Add Items
            items(items = taskList.value, key = { task -> task.id ?: 0}) { task ->

                val dismissState = rememberDismissState(
                    confirmStateChange = {
                        if(it == DismissValue.DismissedToStart)
                            viewModel.deleteTask(task)
                        true
                    }
                )
                SwipeToDismiss(
                    state = dismissState,
                    background = {
                        val color by animateColorAsState(
                            if(dismissState.dismissDirection == DismissDirection.EndToStart) Color.Red else Color.Transparent,
                            label = "Delete Color Animation"
                        )
                        val alignment = Alignment.CenterEnd
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color)
                                .padding(horizontal = 20.dp),
                            contentAlignment = alignment
                        ) { Icon(Icons.Default.Delete, contentDescription = "Delete Icon", tint = Color.White) }
                    },
                    directions = setOf(DismissDirection.EndToStart),
                    dismissThresholds ={ FractionalThreshold(0.5f) }

                ) {
                    TaskItem(task, {
                        val id = task.id
                        navController.navigate(Screen.AddEditScreen.route + "/$id")
                    })
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TaskItem(task: Task, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, start = 8.dp, end = 8.dp, bottom = 4.dp)
            .clickable { onClick() },
        elevation = 10.dp
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = task.taskTitle, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(text = task.taskDate)
                Text(text = task.lastModifiedDateTime, modifier = Modifier.padding(start = 80.dp))
            }
        }
    }
}