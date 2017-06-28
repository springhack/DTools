package com.dtools.Storage

import android.app.*
import android.content.*
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.text.*
import android.util.Base64

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

object AndroidSharedPreferences {

    val MY_PREFERENCE = "main"

    private fun paraCheck(sp: SharedPreferences?, key: String) {
        if (sp == null) {
            throw IllegalArgumentException()
        }
        if (TextUtils.isEmpty(key)) {
            throw IllegalArgumentException()
        }
    }

    operator fun set(context: Context, key: String, value: String) {
        val sharedPreferences = context.getSharedPreferences(MY_PREFERENCE, Activity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    operator fun get(context: Context, key: String): String {
        val sharedPreferences = context.getSharedPreferences(MY_PREFERENCE, Activity.MODE_PRIVATE)
        return sharedPreferences.getString(key, "")
    }

    fun putBitmap(context: Context, key: String, bitmap: Bitmap?): Boolean {
        val sp = context.getSharedPreferences(MY_PREFERENCE, Context.MODE_PRIVATE)
        paraCheck(sp, key)
        if (bitmap == null || bitmap.isRecycled) {
            return false
        } else {
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
            val imageBase64 = String(Base64.encode(baos.toByteArray(), Base64.DEFAULT))
            val e = sp.edit()
            e.putString(key, imageBase64)
            return e.commit()
        }
    }

    fun getBitmap(context: Context, key: String, defaultValue: Bitmap): Bitmap {
        val sp = context.getSharedPreferences(MY_PREFERENCE, Context.MODE_PRIVATE)
        paraCheck(sp, key)
        val imageBase64 = sp.getString(key, "")
        if (TextUtils.isEmpty(imageBase64)) {
            return defaultValue
        }
        val base64Bytes = Base64.decode(imageBase64.toByteArray(), Base64.DEFAULT)
        val bais = ByteArrayInputStream(base64Bytes)
        val ret = BitmapFactory.decodeStream(bais)
        if (ret != null) {
            return ret
        } else {
            return defaultValue
        }
    }

}
