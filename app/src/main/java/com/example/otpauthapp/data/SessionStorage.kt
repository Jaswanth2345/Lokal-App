package com.example.otpauthapp.data

import android.content.Context

class SessionStorage(context: Context) {

    private val prefs =
        context.getSharedPreferences("session_data", Context.MODE_PRIVATE)



    fun saveHistory(list: List<Long>) {

        val data = list.joinToString(",")

        prefs.edit()
            .putString("history", data)
            .apply()
    }


    fun getHistory(): List<Long> {

        val data = prefs.getString("history", "") ?: ""

        if (data.isEmpty()) return emptyList()

        return data.split(",").map { it.toLong() }
    }


    fun clear() {
        prefs.edit().clear().apply()
    }
}
