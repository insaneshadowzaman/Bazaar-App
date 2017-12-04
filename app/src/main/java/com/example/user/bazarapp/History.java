package com.example.user.bazarapp;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class History extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        SQLiteDatabase db = openOrCreateDatabase("mydb", Context.MODE_PRIVATE, null);
        final Cursor cursor = db.query("mydb", new String[]{"name", "price"}, null, null, null, null, null);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(
                        this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new RecyclerView.Adapter<HistoryView>() {
            @Override
            public HistoryView onCreateViewHolder(ViewGroup parent, int viewType) {
                return new HistoryView(LayoutInflater.from(parent.getContext()).inflate(R.layout.view, parent, false));
            }

            @Override
            public void onBindViewHolder(HistoryView holder, int position) {
                if( cursor.moveToPosition(position) ) {
                    holder.name.setText(cursor.getString(0));
                    holder.price.setText(cursor.getString(1));
                }
            }

            @Override
            public int getItemCount() {
                return cursor.getCount();
            }
        });
        findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrCreateDatabase("mydb", Context.MODE_PRIVATE, null).execSQL("delete from mydb");
                finish();
            }
        });

    }



    class HistoryView extends RecyclerView.ViewHolder {
        public TextView name, price;
        public HistoryView(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.rename);
            price = itemView.findViewById(R.id.reprice);
        }
    }
}
