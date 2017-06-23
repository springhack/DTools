package com.dtools.Storage;

import android.app.*;
import android.content.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.*;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class AndroidSharedPreferences {

    public static final String MY_PREFERENCE = "main";

    private static void paraCheck(SharedPreferences sp, String key) {
        if (sp == null) {
            throw new IllegalArgumentException();
        }
        if (TextUtils.isEmpty(key)) {
            throw new IllegalArgumentException();
        }
    }

    public static void set(Context context, String key, String value) {
        SharedPreferences sharedPreferences= context.getSharedPreferences(MY_PREFERENCE, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String get(Context context, String key) {
        SharedPreferences sharedPreferences= context.getSharedPreferences(MY_PREFERENCE, Activity.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }

    public static boolean putBitmap(Context context, String key, Bitmap bitmap) {
        SharedPreferences sp = context.getSharedPreferences(MY_PREFERENCE, Context.MODE_PRIVATE);
        paraCheck(sp, key);
        if (bitmap == null || bitmap.isRecycled()) {
            return false;
        } else {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            String imageBase64 = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            SharedPreferences.Editor e = sp.edit();
            e.putString(key, imageBase64);
            return e.commit();
        }
    }

    public static Bitmap getBitmap(Context context, String key, Bitmap defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(MY_PREFERENCE, Context.MODE_PRIVATE);
        paraCheck(sp, key);
        String imageBase64 = sp.getString(key, "");
        if (TextUtils.isEmpty(imageBase64)) {
            return defaultValue;
        }
        byte[] base64Bytes = Base64.decode(imageBase64.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
        Bitmap ret = BitmapFactory.decodeStream(bais);
        if (ret != null) {
            return ret;
        } else {
            return defaultValue;
        }
    }

}
