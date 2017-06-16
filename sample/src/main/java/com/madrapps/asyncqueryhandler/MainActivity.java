package com.madrapps.asyncqueryhandler;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.madrapps.asyncqueryhandler.database.DatabaseHandler;

import static com.madrapps.asyncqueryhandler.database.DatabaseHelper.Organization.COL_AGE;
import static com.madrapps.asyncqueryhandler.database.DatabaseHelper.Organization.COL_NAME;
import static com.madrapps.asyncqueryhandler.provider.Contract.AUTHORITY;
import static com.madrapps.asyncqueryhandler.provider.Contract.ORGANIZATION;
import static com.madrapps.asyncqueryhandler.provider.Contract.SCHEME;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {
        findViewById(R.id.btnInsert).setOnClickListener(this);
        findViewById(R.id.btnBulkInsert).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnInsert:
                insertIntoTable();
                break;
            case R.id.btnBulkInsert:
                bulkInsertIntoTable();
                break;
        }
    }

    private void bulkInsertIntoTable() {
        Log.d("MainActivity", "Bulk Insert Started");
        final DatabaseHandler handler = new DatabaseHandler(getContentResolver());
        final Uri uri = new Uri.Builder().scheme(SCHEME).authority(AUTHORITY).appendEncodedPath(ORGANIZATION).build();

        handler.startBulkInsert(1, null, uri, getContentValues());

        Log.d("MainActivity", "Bulk Insert Ended");
    }

    private void insertIntoTable() {
        Log.d("MainActivity", "Insert Started");
        final DatabaseHandler handler = new DatabaseHandler(getContentResolver());
        final Uri uri = new Uri.Builder().scheme(SCHEME).authority(AUTHORITY).appendEncodedPath(ORGANIZATION).build();

        for (ContentValues initialValues : getContentValues()) {
            handler.startInsert(1, null, uri, initialValues);
        }
    }

    private ContentValues[] getContentValues() {
        final int size = 2000;
        final ContentValues[] values = new ContentValues[size];
        for (int i = 0; i < size; i++) {
            final ContentValues contentValues = new ContentValues();
            contentValues.put(COL_NAME, "John" + String.valueOf(i));
            contentValues.put(COL_AGE, i + 20);
            values[i] = contentValues;
        }
        return values;
    }
}
