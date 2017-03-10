package edu.csulb.android.photonotes;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class helperDB {

    private initializeDB dbObj;

    public helperDB(initializeDB dbObj)
    {
        this.dbObj=dbObj;
    }

    public void insertData(String caption, String Filepath )
    {
        SQLiteDatabase db = dbObj.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(initializeDB.dbEntry.COLUMN_TITLE_1,caption);
        content.put(initializeDB.dbEntry.COLUMN_TITLE_2,Filepath);
        long rowid = db.insert(initializeDB.dbEntry.TABLE_NAME,null,content);
    }

    public  void deleteData(String caption)
    {
        SQLiteDatabase db = dbObj.getWritableDatabase();
        String selection = initializeDB.dbEntry.COLUMN_TITLE_1 + " = ?";
        String[] selectionArgs = {caption};
        db.delete(initializeDB.dbEntry.TABLE_NAME, selection, selectionArgs);
    }

    public List readData()
    {
        SQLiteDatabase db = dbObj.getReadableDatabase();
        String[] Projection ={
                initializeDB.dbEntry._ID,
                initializeDB.dbEntry.COLUMN_TITLE_1,
                initializeDB.dbEntry.COLUMN_TITLE_2
        };

        Cursor cursor = db.query(
                initializeDB.dbEntry.TABLE_NAME,
                Projection,
                null,
                null,
                null,
                null,
                null

        );

        List<String> items = new ArrayList();
        while(cursor.moveToNext())
        {
            String caption=cursor.getString(cursor.getColumnIndexOrThrow(initializeDB.dbEntry.COLUMN_TITLE_1));
            items.add(caption);
        }

        return items;
    }

    public String getImagePath(String caption)
    {
        SQLiteDatabase db = dbObj.getReadableDatabase();

        String[] Projection = {
                        initializeDB.dbEntry.COLUMN_TITLE_2
                };

        String selection = initializeDB.dbEntry.COLUMN_TITLE_1 + " = ?";
        String[] selectionArgs = { caption };

        Cursor cursor = db.query(
                initializeDB.dbEntry.TABLE_NAME,
                Projection,
                selection,
                selectionArgs,
                null,
                null,
                null

        );

        cursor.moveToNext();
        String path = cursor.getString(cursor.getColumnIndexOrThrow(initializeDB.dbEntry.COLUMN_TITLE_2));

        return path;

    }



}
