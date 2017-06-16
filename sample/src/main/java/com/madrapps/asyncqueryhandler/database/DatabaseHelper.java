package com.madrapps.asyncqueryhandler.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Locale;

import static com.madrapps.asyncqueryhandler.database.DatabaseHelper.Organization.COL_AGE;
import static com.madrapps.asyncqueryhandler.database.DatabaseHelper.Organization.COL_NAME;
import static com.madrapps.asyncqueryhandler.database.DatabaseHelper.Organization.TABLE_NAME;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format(Locale.US, "CREATE TABLE %s (_id INTEGER PRIMARY KEY AUTOINCREMENT, %s text not null, %s INTEGER not null)", TABLE_NAME, COL_NAME, COL_AGE));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public class Organization {
        public static final String TABLE_NAME = "organization";
        public static final String COL_NAME = "name";
        public static final String COL_AGE = "age";

        private Organization() {/* Do not allow instantiation */}
    }
}
