package com.example.providertest;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    String newId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button add_data= findViewById(R.id.add_data);
        add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加数据
                Uri uri= Uri.parse("content://com.example.databasetest.provider/book");
                ContentValues values= new ContentValues();
                values.put("name","A Clash Of Kings");
                values.put("author","George Martin");
                values.put("pages",1040);
                values.put("price",22.98);
                Uri newUri= getContentResolver().insert(uri,values);
                newId= newUri.getPathSegments().get(1);
            }
        });

        Button query_data= findViewById(R.id.query_data);
        query_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri= Uri.parse("content://com.example.databasetest.provider/book");
                Cursor cursor= getContentResolver().query(uri,null,null,null,null,null);
                if (cursor!= null){
                    while (cursor.moveToNext()){
                        String name= cursor.getString(cursor.getColumnIndex("name"));
                        String author= cursor.getString(cursor.getColumnIndex("author"));
                        String pages= cursor.getString(cursor.getColumnIndex("pages"));
                        String price= cursor.getString(cursor.getColumnIndex("price"));

                        Log.e("MainActivity","name is:"+name);
                        Log.e("MainActivity","author is:"+author);
                        Log.e("MainActivity","pages is:"+pages);
                        Log.e("MainActivity","price is:"+price);
                    }
                    cursor.close();
                }
            }
        });

        Button update_data= findViewById(R.id.update_data);
        update_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //更新数据
                Uri uri= Uri.parse("content://com.example.databasetest.provider/book/"+newId);
                ContentValues values= new ContentValues();
                values.put("name","A Storm Of Swords");
                values.put("pages",1216);
                values.put("price",24.05);
                getContentResolver().update(uri,values,null,null);
            }
        });

        Button delete_data= findViewById(R.id.delete_data);
        delete_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除数据
                Uri uri= Uri.parse("content://com.example.databasetest.provider/book/"+newId);
                getContentResolver().delete(uri,null,null);
            }
        });
    }

}
