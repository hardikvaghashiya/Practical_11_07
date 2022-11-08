package com.practical_11_07.utils

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.practical_11_07.model.PharmaResponse
import java.io.IOException

object Utility {
    fun getResponseFromAssets(context: Context): PharmaResponse {
        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("response.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            Log.e("IOException", ioException.toString())
        }

        return Gson().fromJson(jsonString, PharmaResponse::class.java)
    }
}