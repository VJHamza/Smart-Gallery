package com.androstock.galleryapp.Dialogue;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.androstock.galleryapp.AlbumActivity;
import com.androstock.galleryapp.AppRoomDatabase;
import com.androstock.galleryapp.Image;
import com.androstock.galleryapp.ImageDao;
import com.androstock.galleryapp.R;

import java.util.ArrayList;
import java.util.List;

public class SearchDialog {
    private Context context;
    public ArrayList<Image> images;

    public SearchDialog(Context context) {
        this.context = context;
    }

    public void SearchImage(int dialog_layout) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(dialog_layout, null);

        final EditText nameField = (EditText) subView.findViewById(R.id.searchImageText);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Search Images");
        builder.setView(subView);
        builder.create();
        builder.setPositiveButton("Search", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                final String queryText = nameField.getText().toString();
                if (TextUtils.isEmpty(queryText)) {
                    Toast.makeText(context, "Empty or invalid input", Toast.LENGTH_LONG).show();
                } else {
                    ImageDao imageDao = (ImageDao) AppRoomDatabase.getDatabase(context).imageDao();
                    String searchVariable = "%" + queryText + "%";
                    List<Image> imlist=imageDao.searchImageByLabel(searchVariable);
                    //list = imageDao.searchImageByLabel(searchVariable);
                    images=new ArrayList<>(imlist.size());
                    images.addAll(imlist);
                    //Bundle b = new Bundle();
                    //b.putSerializable("Images   ", images);
                    Intent intent = new Intent(context, AlbumActivity.class);
                    //intent.put
                    intent.putExtra("Images",images);
                    context.startActivity(intent);

                }
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Task cancelled", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
//@Override
//public Dialog onCreateDialog(Bundle savedInstanceState) {
//    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//    // Get the layout inflater
//    LayoutInflater inflater = getActivity().getLayoutInflater();
//
//    // Inflate and set the layout for the dialog
//    // Pass null as the parent view because its going in the dialog layout
//    builder.setView(inflater.inflate(R.layout.dialog_layout, null))
//            // Add action buttons
//            .setPositiveButton(R.string.search, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int id) {
//                    // sign in the user ...
//                    EditText edit = (EditText) ((AlertDialog) dialog).findViewById(R.id.searchImageText);
//                    String queryText=edit.getText().toString();
//                    if (TextUtils.isEmpty(queryText)) {
//                    Toast.makeText(, "Empty or invalid input", Toast.LENGTH_LONG).show();
//                } else {
//                    //Message content = new Message(message);
//                    //add new message to database
//
//                    ImageDao imageDao = (ImageDao) AppRoomDatabase.getDatabase(context).imageDao();
//                    images=imageDao.searchImageByLabel(queryText);
//                    Intent intent=new Intent(getBaseContext(),AlbumActivity.class);
//
//                }
//                }
//            })
//            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int id) {
//                    SearchDialog.this.getDialog().cancel();
//                }
//            });
//    return builder.create();
//}

}
