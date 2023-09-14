package com.blaze.core.utils.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.ByteArrayOutputStream
import kotlin.math.roundToInt

fun Uri.compressImage(
    appContext: Context,
    compressionThresholdInBytes: Long = 1024 * 20L
): ByteArray? {
    val bytes = appContext.contentResolver.openInputStream(this)?.use {
        it.readBytes()
    } ?: return null
    val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    var outputBytes: ByteArray
    var quality = 100
    do {
        val outputStream = ByteArrayOutputStream()
        outputStream.use { outputStream ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
            outputBytes = outputStream.toByteArray()
            quality -= (quality * 0.1).roundToInt()
        }
    } while (outputBytes.size > compressionThresholdInBytes && quality > 5)
    return outputBytes
}