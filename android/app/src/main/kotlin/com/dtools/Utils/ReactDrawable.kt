package com.dtools.Utils

import android.graphics.*
import android.graphics.drawable.*
import android.util.*
import java.io.*

object ReactDrawable {

    fun drawableToBase64(drawable: Drawable?): String? {
        if (drawable != null) {
            val bitmap = Bitmap.createBitmap(
                    drawable.intrinsicWidth,
                    drawable.intrinsicHeight,
                    if (drawable.opacity != PixelFormat.OPAQUE) Bitmap.Config.ARGB_8888 else Bitmap.Config.RGB_565)
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
            drawable.draw(canvas)
            val size = bitmap.width * bitmap.height * 4
            val baos = ByteArrayOutputStream(size)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
            val imagedata = baos.toByteArray()
            val icon = Base64.encodeToString(imagedata, Base64.DEFAULT)
            return icon
        }
        return null
    }

}
