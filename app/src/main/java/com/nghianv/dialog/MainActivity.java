package com.nghianv.dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.*;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    private Button btnShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Dialog dialog = new Dialog.Builder(this)
                .setGravity(Gravity.CENTER)
                .setTitle("Thông báo")
                .setMessage("Bạn có muốn thoát không, Yes or No?")
                .setPositiveButton("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                })
                .setNegativeButton("Cancle", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                //.setView(R.layout.dl_content_layout)
                .build();

//        Button btnDismis = (Button)dialog.findViewById(R.id.login);
//        btnDismis.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });



        btnShow = (Button)findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // dialog.show();
                dialog.show();
            }
        });









//        dialog.show();
//        dialog.dismiss();
    }

}
