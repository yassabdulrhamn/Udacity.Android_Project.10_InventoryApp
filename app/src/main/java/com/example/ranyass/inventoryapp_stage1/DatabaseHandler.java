package com.example.ranyass.inventoryapp_stage1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHandler";

    private static final String DATABASE_NAME = "inventoryDB";
    private static final int DATABASE_VERSION = 3;

    private static final String TableName = "inventory";
    private static final String ColumnID = "ID";
    private static final String ColumnItemName = "Name";
    private static final String ColumnItemPurveyorName = "Purveyor";
    private static final String ColumnItemCost = "Cost";
    private static final String ColumnItemCategory = "Category";
    private static final String ColumnItemQuantity = "Quantity";
    private static final String ColumnTIMESTAMP = "AddedTime";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("Error","Yaser 5");
        String createTable = "CREATE TABLE " + TableName + " (" +
                ColumnID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ColumnItemName + " VARCHAR(50)," +
                ColumnItemPurveyorName +" VARCHAR(50)," +
                ColumnItemCost + " INTEGER (11), " +
                ColumnItemCategory +" VARCHAR(50)," +
                ColumnItemQuantity + " INTEGER (11), " +
                ColumnTIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP " +
                ")";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int y) {
sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TableName);
onCreate(sqLiteDatabase);
    }
    public  boolean addData(ArrayList<String> item)
    {
        Log.d("addData1","OK!");
        SQLiteDatabase sqLiteDatabase =getWritableDatabase();
        Log.d("getWritableDatabase ","OK!");
        ContentValues contentValues = new ContentValues();
        Log.d("contentValues","OK!");
        contentValues.put(ColumnItemName,item.get(0).toString());
        contentValues.put(ColumnItemPurveyorName,item.get(3).toString());
        contentValues.put(ColumnItemCost,item.get(4).toString());
        contentValues.put(ColumnItemCategory,item.get(1).toString());
        contentValues.put(ColumnItemQuantity,item.get(2).toString());
        long result = sqLiteDatabase.insert(TableName,null,contentValues);
        Log.d(TAG,"addData (Adding) "+item.get(0).toString()+" into "+TableName);

        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    public HashMap<Integer, ArrayList<String>> getData() {
        HashMap<Integer, ArrayList<String>> hashMap;
        hashMap = new HashMap<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TableName,
                new String[]{ColumnID,ColumnItemName,ColumnItemCategory,ColumnItemCost,ColumnItemPurveyorName,ColumnItemQuantity,ColumnTIMESTAMP},
                null,null,
                null,
                null,
                ColumnID + " DESC ",
                null);

        // looping through all rows and adding to list
        Log.d("Row",String.valueOf(hashMap.size()));
        if(cursor.getCount()>0)
        {
            if (cursor.moveToFirst()) {
                int i=0;
                do {

                    final ArrayList<String> table_row = new ArrayList<>();
                    try{
                        table_row.add(String.valueOf(cursor.getInt(cursor.getColumnIndex(ColumnID))));
                        table_row.add(cursor.getString(cursor.getColumnIndex(ColumnItemName)).toString());
                        table_row.add(cursor.getString(cursor.getColumnIndex(ColumnItemCategory)).toString());
                        table_row.add(String.valueOf(cursor.getInt(cursor.getColumnIndex(ColumnItemCost))));
                        table_row.add(cursor.getString(cursor.getColumnIndex(ColumnItemPurveyorName)).toString());
                        table_row.add(String.valueOf(cursor.getInt(cursor.getColumnIndex(ColumnItemQuantity))));
                        table_row.add(cursor.getString(cursor.getColumnIndex(ColumnTIMESTAMP)).toString());
                        hashMap.put(i,table_row);
                    }
                    catch (Exception e)
                    {
                        Log.d("Row",String.valueOf(hashMap.size()));
                    }
                    //Log.d("Row",hashMap.get(i).get(0).toString()+" | "+hashMap.get(i).get(1).toString()+" | "+hashMap.get(i).get(2).toString()+" | "+hashMap.get(i).get(3).toString()+" | "+hashMap.get(0).get(4).toString()+" | "+hashMap.get(i).get(5).toString()+" | "+hashMap.get(i).get(6).toString());
                    i=i+1;
                } while (cursor.moveToNext());
            }
        }
        // close db connection
        db.close();
        return  hashMap;
    }
}
