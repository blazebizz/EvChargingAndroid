package com.blaze.core.utils.util

import android.Manifest
import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.core.content.ContextCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

val ioScope = CoroutineScope(Dispatchers.IO)
val mainScope = CoroutineScope(Dispatchers.Main)


fun getBitmapFromUri(contentResolver: ContentResolver, uri: Uri): Bitmap? {
    var inputStream: InputStream? = null
    try {
        inputStream = contentResolver.openInputStream(uri)
        if (inputStream != null) {
            return BitmapFactory.decodeStream(inputStream)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        inputStream?.close()
    }
    return null
}

fun saveBitmapToFile(bitmap: Bitmap, context: Context): Uri? {
    val imagesFolder = File(context.cacheDir, "images")
    if (!imagesFolder.exists()) {
        imagesFolder.mkdirs()
    }
    val imageFile = File(imagesFolder, "image.jpg")

    val fos = FileOutputStream(imageFile)
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
    fos.flush()
    fos.close()

    return Uri.fromFile(imageFile)
}

fun uriToBitmap(contentResolver: ContentResolver, imageUri: Uri): Bitmap? {
    var inputStream: InputStream? = null
    try {
        inputStream = contentResolver.openInputStream(imageUri)
        if (inputStream != null) {
            return BitmapFactory.decodeStream(inputStream)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        inputStream?.close()
    }
    return null
}

fun ByteArray.toBitmap(): Bitmap = BitmapFactory.decodeByteArray(this, 0, this.size)


fun Context.hasLocationPermission(): Boolean {
    return ContextCompat.checkSelfPermission(
        this, Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
        this, Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}
