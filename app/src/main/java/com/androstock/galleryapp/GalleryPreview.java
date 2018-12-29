package com.androstock.galleryapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

//import com.androstock.galleryapp.Model.CenterRepository;
//import com.androstock.galleryapp.Widget.ExtendedViewPager;
//import com.androstock.galleryapp.Widget.TouchImageView;
import com.bumptech.glide.Glide;

import java.io.File;

/**
 * Created by SHAJIB on 25/12/2015.
 */
public class GalleryPreview extends AppCompatActivity {

    ImageView GalleryPreviewImg;
    String path;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;

    //    ExtendedViewPager mViewPager;
//    private TouchImageAdapter galleryAdaqpter;
    //int currentPagePosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.gallery_preview);

        Intent intent = getIntent();

        path = intent.getStringExtra("path");
        GalleryPreviewImg = (ImageView) findViewById(R.id.GalleryPreviewImg);
        Glide.with(GalleryPreview.this).load(new File(path)).into(GalleryPreviewImg);

        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.fab_delete);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.fab_edit);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.fab_share);
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditFile();
            }
        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareFile();
            }
        });
        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteFile(path);

            }
        });
    }

    public void ShareFile() {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        // Always use string resources for UI text.
        // This says something like "Share this photo with"
        String title = "Share with ";
        // Create intent to show the chooser dialog
        Intent chooser = Intent.createChooser(sendIntent, title);

        // Verify the original intent will resolve to at least one activity
        //if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        //}
    }

    public void EditFile() {
        Intent editIntent = new Intent(Intent.ACTION_EDIT);
        File image = new File(path);
        Uri uri=Uri.fromFile(image);
        editIntent.setDataAndType(uri, "image/*");
        editIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(editIntent, null));
    }

    public void DeleteFile(String path) {
        File image = new File(path);
        //image.delete();
        //galleryAddPic();
        //Uri uri=Uri.fromFile(image);

//        Bitmap bitmap=BitmapFactory.decodeFile(path);
//        bitmap.recycle();
        if (image.exists()) {
            boolean success = image.delete();
            callBroadCast();
            //sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,Uri.fromFile(new File(path))));
            Toast.makeText(GalleryPreview.this, "Image Deleted" + success, Toast.LENGTH_LONG).show();
            //return true;
        }
//        BaseApplication.appContext.getContentResolver().delete(
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                MediaStore.Images.Media.DATA + "='"
//                        + new File(fileUri).getPath() + "'", null);
        //return false;
    }

    public void callBroadCast() {
        MediaScannerConnection.scanFile(this, new String[]{Environment.getExternalStorageDirectory().toString()}, null, new MediaScannerConnection.OnScanCompletedListener() {
            @Override
            public void onScanCompleted(String path, Uri uri) {
                Log.e("Exernal storage: ", "Scacnned " + path);
                Log.e("Exernal storage: ", "Scacnned " + uri);
            }
        });
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(path);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }


}
