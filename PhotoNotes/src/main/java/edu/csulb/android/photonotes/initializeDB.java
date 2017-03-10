package edu.csulb.android.photonotes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class initializeDB extends SQLiteOpenHelper {



    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + dbEntry.TABLE_NAME + " (" +
                    dbEntry._ID + " INTEGER PRIMARY KEY," +
                    dbEntry.COLUMN_TITLE_1 + " TEXT," +
                    dbEntry.COLUMN_TITLE_2 + " TEXT)";

    public static final String DATABASE_NAME="Photonotes.db";
    public static final int DATABASE_VERSION = 1;


    public  static class dbEntry implements BaseColumns {

        public static final String TABLE_NAME ="Entry";
        public static final String COLUMN_TITLE_1="Caption";
        public static final String COLUMN_TITLE_2="Path";

    }


    public initializeDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
