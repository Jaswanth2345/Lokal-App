package com.example.otpauthapp.ui

fun formatTime(sec: Long): String {

    val min = sec / 60
    val s = sec % 60

    return "%02d:%02d".format(min, s)
}
