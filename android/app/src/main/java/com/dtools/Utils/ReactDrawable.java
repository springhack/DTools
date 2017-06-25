package com.dtools.Utils;

import android.graphics.*;
import android.graphics.drawable.*;
import android.util.*;
import java.io.*;

public class ReactDrawable {

    public static String drawableToBase64(Drawable drawable) {
        if (drawable != null) {
            Bitmap bitmap = Bitmap.createBitmap(
                    drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight(),
                    drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            int size = bitmap.getWidth() * bitmap.getHeight() * 4;
            ByteArrayOutputStream baos = new ByteArrayOutputStream(size);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] imagedata = baos.toByteArray();
            String icon= Base64.encodeToString(imagedata, Base64.DEFAULT);
            return icon;
        }
        return null;
    }

}
