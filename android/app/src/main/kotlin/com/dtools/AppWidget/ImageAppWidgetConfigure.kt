package com.dtools.AppWidget

import android.app.*
import android.appwidget.AppWidgetManager
import android.content.*
import android.graphics.*
import android.net.*
import android.os.*
import android.util.*
import android.widget.*
import com.dtools.*
import com.dtools.Storage.*

class ImageAppWidgetConfigure : Activity() {

    private var imageAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID

    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.blank_activity)

        val intent = intent
        val extras = intent.extras
        if (extras != null) {
            imageAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID)
            val reslut = Intent()
            reslut.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, imageAppWidgetId)
            setResult(Activity.RESULT_CANCELED, reslut)

            val chooseImage = Intent()
            chooseImage.type = "image/*"
            chooseImage.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(chooseImage, 1)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (resultCode == Activity.RESULT_OK) {
            val uri = data.data
            val cr = this.contentResolver
            try {
                val bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri))
                val remoteView = RemoteViews(packageName, R.layout.image_app_widget)
                remoteView.setImageViewBitmap(R.id.image_app_widget, bitmap)
                AppWidgetManager.getInstance(this).updateAppWidget(imageAppWidgetId, remoteView)
                val reslut = Intent()
                AndroidSharedPreferences.putBitmap(this, imageAppWidgetId.toString(), bitmap)
                reslut.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, imageAppWidgetId)
                setResult(Activity.RESULT_OK, reslut)
                finish()
            } catch (e: Exception) {
                Log.e("Exception", e.message, e)
            }

        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}
