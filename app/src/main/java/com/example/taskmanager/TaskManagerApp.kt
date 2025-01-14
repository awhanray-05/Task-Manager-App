package com.example.taskmanager

import android.app.Application

class TaskManagerApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}