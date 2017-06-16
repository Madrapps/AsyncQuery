package com.madrapps.asyncqueryhandler.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.madrapps.asyncqueryhandler.database.DatabaseHelper;

import static com.madrapps.asyncqueryhandler.database.DatabaseHelper.Organization.TABLE_NAME;
import static com.madrapps.asyncqueryhandler.provider.Contract.AUTHORITY;
import static com.madrapps.asyncqueryhandler.provider.Contract.ORGANIZATION;

public class DataProvider extends ContentProvider {

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int ORGANIZATION_URI = 1;

    static {
        uriMatcher.addURI(AUTHORITY, ORGANIZATION, ORGANIZATION_URI);
    }

    private SQLiteDatabase database;
    private DatabaseHelper sqlLiteHelper;

    @Override
    public boolean onCreate() {
        sqlLiteHelper = new DatabaseHelper(this.getContext(), "sample.db", null, 1);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        throw new UnsupportedOperationException();
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        throw new UnsupportedOperationException();
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.d("DataProvider", "Insert: " + uri);
        final int match = uriMatcher.match(uri);
        if (match == ORGANIZATION_URI) {
            getDatabase().beginTransaction();
            getDatabase().insert(TABLE_NAME, null, values);
            getDatabase().setTransactionSuccessful();
            getDatabase().endTransaction();
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        Log.d("DataProvider", "Insert: " + uri);
        final int match = uriMatcher.match(uri);
        if (match == ORGANIZATION_URI) {
            return insertInBulk(getDatabase(), TABLE_NAME, values);
        }
        return 0;
    }

    private int insertInBulk(SQLiteDatabase database, String tableName, ContentValues[] values) {
        database.beginTransaction();

        for (ContentValues value : values) {
            database.insertOrThrow(tableName, null, value);
        }

        database.setTransactionSuccessful();
        database.endTransaction();
        return values.length;
    }

    @NonNull
    private SQLiteDatabase getDatabase() {
        if (database == null) {
            database = sqlLiteHelper.getWritableDatabase();
        }
        return database;
    }
}
