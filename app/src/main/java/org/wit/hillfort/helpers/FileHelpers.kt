package org.wit.hillfort.helpers

import android.content.Context
import android.util.Log
import java.io.*
import java.lang.Exception
import java.lang.StringBuilder

fun write(context: Context, fileName: String, data: String) {
    try {
        val outputStreamWriter = OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE))
        outputStreamWriter.write(data)
        outputStreamWriter.close()
    } catch (e: Exception) {
        Log.e("Error: ", "Cannot read file: $e")
    }
}

fun read(context: Context, fileName: String): String {
    var str = ""
    try {
        val inputStream = context.openFileInput((fileName))
        if (inputStream != null) {
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferReader = BufferedReader(inputStreamReader)
            val partialStr = StringBuilder()
            var done = false
            while (!done) {
                var line = bufferReader.readLine()
                done = (line == null)
                if (line != null) partialStr.append(line)
            }
            inputStream.close()
            str = partialStr.toString()
        }
    } catch (e: FileNotFoundException) {
        Log.e("Error: ", "file not found: $e")
    } catch (e: IOException) {
        Log.e("Error: ", "cannot read file: $e")
    }
    return str
}

fun exists(context: Context, fileName: String): Boolean {
    val file = context.getFileStreamPath(fileName)
    return file.exists()
}