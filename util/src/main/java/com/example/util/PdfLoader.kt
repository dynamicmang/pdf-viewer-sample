package com.example.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import java.io.File
import java.io.FileOutputStream

object PdfLoader {

    fun getPdfRenderer(file: File): PdfRenderer {
        val fileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
        return PdfRenderer(fileDescriptor)
    }

    fun PdfRenderer.getThumbnailUri(context: Context, name: String): String {
        val bitmap = getBitmap(0)
        val thumbnailFile = File(context.filesDir, name + "_thumbnail")
        FileOutputStream(thumbnailFile).use { out: FileOutputStream ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        }
        return thumbnailFile.absolutePath
    }

    fun PdfRenderer.getBitmap(position: Int): Bitmap {
        return openPage(position).use { page ->
            val width = 1080
            val height = 1080 * page.height / page.width
            val bitmap = Bitmap.createBitmap(
                width,
                height,
                Bitmap.Config.ARGB_8888
            )
            page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
            bitmap
        }
    }

}