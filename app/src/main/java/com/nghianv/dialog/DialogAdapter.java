package com.nghianv.dialog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nguyennghia on 28/03/2016.
 */
public class DialogAdapter extends ArrayAdapter<Author> {
    List<Author> _authors;
    Context mContext;
    public DialogAdapter(Context context, List<Author> objects) {
        super(context, 0, objects);
        mContext = context;
        _authors = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Author author = _authors.get(position);
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.row_test, parent, false);
        }
        TextView txtId = (TextView)convertView.findViewById(R.id.idAuthor);
        TextView txtName = (TextView)convertView.findViewById(R.id.txtAuthor);

        Log.i("Nghia", txtId + " ");
        Log.i("Nghia", txtName + " ");

        if(txtId != null)
            txtId.setText(String.valueOf(author.getId()));
        if(txtName != null)
            txtName.setText(author.getName());
        return convertView;
    }


}
