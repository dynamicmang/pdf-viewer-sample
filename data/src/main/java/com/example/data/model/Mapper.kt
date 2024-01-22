package com.example.data.model

import com.example.domain.model.PdfBook

fun PdfBookEntity.asPdfBook(): PdfBook {
    return PdfBook(
        id = this.id,
        name = this.name,
        thumbnail = this.thumbnail,
        totalPage = this.totalPage,
        bookmarkCount = this.bookmarkCount,
        learningTime = this.learningTime,
        learningDate = this.learningDate,
    )
}

fun List<PdfBookEntity>.asPdfBooks() = this.map { it.asPdfBook() }

fun PdfBook.toMap(): PdfBookEntity {
    return PdfBookEntity(
        name = this.name,
        thumbnail = this.thumbnail,
        totalPage = this.totalPage,
        bookmarkCount = this.bookmarkCount,
        learningTime = this.learningTime,
        learningDate = this.learningDate,
    )
}