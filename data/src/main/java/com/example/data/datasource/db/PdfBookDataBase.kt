package com.example.data.datasource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.model.PdfBookEntity

@Database(entities = [PdfBookEntity::class], version = 1, exportSchema = false)
abstract class PdfBookDataBase : RoomDatabase() {
    abstract val pdfBookDao: PdfBookDao
}