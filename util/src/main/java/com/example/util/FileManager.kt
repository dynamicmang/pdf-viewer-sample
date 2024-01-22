package com.example.util

import android.content.Context
import android.net.Uri
import androidx.documentfile.provider.DocumentFile
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream

object FileManager {

    fun getPdfFile(context: Context, name: String): File {
        return File("${context.filesDir}/$name")
    }

    fun getPdfFileList(context: Context): List<File> {
        return context.filesDir.listFiles()?.filter { it.name.contains(".pdf") } ?: emptyList()
    }

    fun savePdfFile(context: Context, uri: Uri): File {
        val documentFile = DocumentFile.fromSingleUri(context, uri)
        val inputStream = context.contentResolver.openInputStream(uri) ?: throw FileNotFoundException()
        val file = File(context.filesDir, documentFile?.name ?: "temp.pdf")
        val output = FileOutputStream(file)
        val buffer = ByteArray(4 * 1024)
        var read: Int = inputStream.read(buffer)
        while ((read) != -1) {
            output.write(buffer, 0, read)
            read = inputStream.read(buffer)
        }
        output.flush()
        return file
    }

}