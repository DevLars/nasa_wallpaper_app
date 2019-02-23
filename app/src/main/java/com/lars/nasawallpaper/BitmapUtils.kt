package com.lars.nasawallpaper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.Point
import android.graphics.RectF
import android.view.WindowManager


class BitmapUtils {
    fun scaleBitmapToFullScreen(context: Context, bitmap: Bitmap): Bitmap {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return scaleImageKeepAspectRatio(bitmap, size.y +600)
    }

    fun scaleBitmapAndKeepRation(targetBmp: Bitmap, reqHeightInPixels: Int, reqWidthInPixels: Int): Bitmap {
        val matrix = Matrix()
        matrix.setRectToRect(
            RectF(0f, 0f, targetBmp.width.toFloat(), targetBmp.height.toFloat()),
            RectF(0f, 0f, reqWidthInPixels.toFloat(), reqHeightInPixels.toFloat()),
            Matrix.ScaleToFit.CENTER
        )
        return Bitmap.createBitmap(targetBmp, 0, 0, targetBmp.width, targetBmp.height, matrix, true)
    }

    fun scaleImageKeepAspectRatio(bitmap: Bitmap, height: Int): Bitmap {
        val imageWidth = bitmap.width
        val imageHeight = bitmap.height
        val newWidth = imageWidth * height / height
        return Bitmap.createScaledBitmap(bitmap, newWidth, height, false)

    }

    fun getCroppedBitmap(bitmap: Bitmap, height: Int, width: Int) =
        Bitmap.createBitmap(bitmap, 0, 0, width, height)
}