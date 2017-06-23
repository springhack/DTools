package com.dtools.AppWidget;

import android.appwidget.*;
import android.content.*;
import android.graphics.*;
import android.widget.*;
import com.dtools.R;
import com.dtools.Storage.*;

public class ImageAppWidgetProvider extends AppWidgetProvider {
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        for (int id:appWidgetIds) {
            AndroidSharedPreferences.set(context, String.valueOf(id), "");
        }
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Bitmap defaultValue = BitmapFactory.decodeResource(context.getResources(), R.drawable.image_app_widget_preview);
        for (int id:appWidgetIds) {
            Bitmap bitmap = AndroidSharedPreferences.getBitmap(context, String.valueOf(id), defaultValue);
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.image_app_widget);
            remoteViews.setImageViewBitmap(R.id.image_app_widget, bitmap);
            AppWidgetManager.getInstance(context).updateAppWidget(id, remoteViews);
        }
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }
}
