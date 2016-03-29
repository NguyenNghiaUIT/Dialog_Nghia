package com.nghianv.dialog;
import android.content.*;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Manifest;


public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        View[] view = new View[4];
        for(int i =0; i < view.length; i++){
            Log.i(TAG, "" + view);
        }

        ListView lvContacts = (ListView)findViewById(R.id.lvContacts);
        final List<String> _data = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            _data.add("Item " + i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, _data);
        if(adapter != null)
            lvContacts.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        lvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }

}
