package com.nghianv.dialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TestListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.layout_test);

        ListView listView = (ListView)findViewById(R.id.select_dialog_listview);



//        final List<Author> _authors = new ArrayList<>();
//        for(int i =0; i < 10; i++){
//            _authors.add(new Author(i, "Author: " + i));
//        }

        ArrayList<String> _data = new ArrayList<>();
        for(int i =0; i < 100; i++) {
            _data.add("Item:" + i);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, _data);

            //final DialogAdapter dialogAdapter = new DialogAdapter(this, _data);

            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            listView.setAdapter(adapter);
            listView.setItemChecked(2, true);

        }





    }


}
