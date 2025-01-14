package com.example.taskmanager

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppBar(
    title: String,
    navBackOnClicked: () -> Unit
) {
    val navigationIcon: (@Composable () -> Unit)? = {
        if(!title.contains("Task List")) {
            IconButton(onClick = { navBackOnClicked() }) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home Icon",
                    tint = Color.White,
                    modifier = Modifier.padding(top = 18.dp)
                    )
            }
        } else null
    }

    TopAppBar(
        modifier = Modifier.height(90.dp),
        title = { Text(text = title, fontSize = 25.sp, modifier = Modifier.padding(top = 25.dp), fontStyle = FontStyle.Italic) },
        elevation = 3.dp,
        backgroundColor = colorResource(R.color.app_bar_color),
        navigationIcon = navigationIcon,
    )
}