package com.nghianv.dialog;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import junit.framework.Test;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    final String TAG = TestActivity.class.getSimpleName();
    Button btnAlertDialog;
    Button btnProgress;
    Button btnListView;
    Button btnSystemDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        final List<Author> _authors = new ArrayList<>();
        for(int i =0; i < 2; i++){
            _authors.add(new Author(i, "Author: " + i));
        }

        final DialogAdapter dialogAdapter = new DialogAdapter(this, _authors);

        List<String> _posts = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            _posts.add(i + "");
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, _posts);

        btnAlertDialog = (Button)findViewById(R.id.btnAlertDialog);
        btnAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog alertDialog = new DialogBuilder(TestActivity.this)
                        .setTitle("Nghia")
                        .setPadding(50, 50, 50, 50)
                        .setMessage("Have a nice day!")
                        .setGravity(Gravity.CENTER)
                        .setContentView(R.layout.dl_content_layout)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("NEGA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNeuralButton("NEU", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create();

                alertDialog.show();
            }
        });


        btnProgress = (Button)findViewById(R.id.btnProgress);
        btnProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog progressDialog = new DialogBuilder(TestActivity.this)

                        .setContentView(R.layout.progress_layout)

                        .create();

                progressDialog.show();

            }
        });

        btnListView = (Button)findViewById(R.id.btnDlListView);
        btnListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog listViewDialog = new DialogBuilder(TestActivity.this)
                        .setTitle("DialogListView")
                        .setAdapter(adapter)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create();

                listViewDialog.show();


            }
        });





        //dialog system
        btnSystemDialog = (Button)findViewById(R.id.btnSystem);
        btnSystemDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
