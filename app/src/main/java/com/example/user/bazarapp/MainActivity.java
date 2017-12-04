package com.example.user.bazarapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SQLiteDatabase db = openOrCreateDatabase("mydb", Context.MODE_PRIVATE, null);
        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("CREATE TABLE IF NOT EXISTS mydb(name TEXT,price INTEGER);");
                String name = ((TextView) findViewById(R.id.edit_text_name)).getText().toString();
                Integer price = Integer.parseInt(((TextView) findViewById(R.id.edit_text_price)).getText().toString());
                db.execSQL("INSERT INTO mydb VALUES('"+name+"','"+price + "');");
            }
        });

        findViewById(R.id.view_history).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, History.class);
                startActivity(intent);
            }
        });
    }
}
