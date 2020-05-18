package com.iamjoey.heartstonejson

import android.content.Context
import java.io.IOException

// https://bezkoder.com/kotlin-android-read-json-file-assets-gson/
fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}