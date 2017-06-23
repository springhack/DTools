package com.dtools.AppWidget;

import android.app.*;
import android.appwidget.AppWidgetManager;
import android.content.*;
import android.graphics.*;
import android.net.*;
import android.os.*;
import android.util.*;
import android.widget.*;
import com.dtools.*;
import com.dtools.Storage.*;

public class ImageAppWidgetConfigure extends Activity {

    private int imageAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.blank_activity);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            imageAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
            Intent reslut = new Intent();
            reslut.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, imageAppWidgetId);
            setResult(RESULT_CANCELED, reslut);

            Intent chooseImage = new Intent();
            chooseImage.setType("image/*");
            chooseImage.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(chooseImage, 1);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            ContentResolver cr = this.getContentResolver();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                RemoteViews remoteView = new RemoteViews(getPackageName(), R.layout.image_app_widget);
                remoteView.setImageViewBitmap(R.id.image_app_widget, bitmap);
                AppWidgetManager.getInstance(this).updateAppWidget(imageAppWidgetId, remoteView);
                Intent reslut = new Intent();
                AndroidSharedPreferences.putBitmap(this, String.valueOf(imageAppWidgetId), bitmap);
                reslut.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, imageAppWidgetId);
                setResult(RESULT_OK, reslut);
                finish();
            } catch (Exception e) {
                Log.e("Exception", e.getMessage(),e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
