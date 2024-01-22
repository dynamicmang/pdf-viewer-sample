package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.datasource.db.PdfBookDao
import com.example.data.datasource.db.PdfBookDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): PdfBookDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            PdfBookDataBase::class.java,
            "pdf_book.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePdfBookDao(database: PdfBookDataBase): PdfBookDao {
        return database.pdfBookDao
    }

    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO

}