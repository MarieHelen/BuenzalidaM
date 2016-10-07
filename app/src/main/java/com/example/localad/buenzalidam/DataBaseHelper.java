package com.example.localad.buenzalidam;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context, String name, CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase _db) {
        _db.execSQL(DataBaseAdapter.DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
        Log.w("TaskDBAdapter", "Upgrading from version " + _oldVersion + " to "
                + _newVersion + ", which will destroy all old data");
        _db.execSQL("DROP TABLE IF EXISTS " + "TEMPLATE");

        onCreate(_db);
    }

    public long insert (String email, ContentValues values) throws NotValidException {
        validate(values);
        return  getWritableDatabase().insert(email, null, values);
    }

    protected void validate(ContentValues values) throws NotValidException {
        String email = null;
        if (!values.containsKey(email) || values.getAsString(email) == null || values.getAsString(email).isEmpty()){
            throw new NotValidException("Email must be set");
        }
    }
    public static class NotValidException extends Throwable {
        public NotValidException(String msg){
            super(msg);
        }
    }
}