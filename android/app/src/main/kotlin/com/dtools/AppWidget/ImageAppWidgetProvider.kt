package com.dtools.AppWidget

import android.appwidget.*
import android.content.*
import android.graphics.*
import android.widget.*
import com.dtools.R
import com.dtools.Storage.*

class ImageAppWidgetProvider : AppWidgetProvider() {
    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        super.onDeleted(context, appWidgetIds)
        for (id in appWidgetIds) {
            AndroidSharedPreferences.set(context, id.toString(), "")
        }
    }

    override fun onDisabled(context: Context) {
        super.onDisabled(context)
    }

    override fun onEnabled(context: Context) {
        super.onEnabled(context)
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        val defaultValue = BitmapFactory.decodeResource(context.resources, R.drawable.image_app_widget_preview)
        for (id in appWidgetIds) {
            val bitmap = AndroidSharedPreferences.getBitmap(context, id.toString(), defaultValue)
            val remoteViews = RemoteViews(context.packageName, R.layout.image_app_widget)
            remoteViews.setImageViewBitmap(R.id.image_app_widget, bitmap)
            AppWidgetManager.getInstance(context).updateAppWidget(id, remoteViews)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
    }
}
