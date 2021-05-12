package com.example.gallerydatabse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(sql);
    }
    public boolean insertData(String name,String price, byte[] image)
    {
        SQLiteDatabase db=getWritableDatabase();
//      String sql="Insert Into food values(NULL,?,?,?)";
//      SQLiteStatement stmt=db.compileStatement(sql);
//      stmt.clearBindings();
//      stmt.bindString(1,name);
//      stmt.bindString(2,price);
//      stmt.bindBlob(3,image);
//      stmt.executeInsert();

        //from previous project AndroidSqlite

        ContentValues cv=new ContentValues();
        cv.put("name",name);
        cv.put("price",price);
        cv.put("image",image);
        long succ=db.insert("food",null,cv);
        if(succ==-1)
            return  false;
        else
            return true;

    }

    public Cursor getData(String sql)
    {
        SQLiteDatabase db=getReadableDatabase();
        return db.rawQuery(sql,null);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String creatTable="CREATE TABLE IF NOT EXISTs food (id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR, price VARCHAR,image BLOB)";
        db.execSQL(creatTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public ArrayList<Food> fetch() {
        ArrayList<Food> returnList = new ArrayList<Food>();
        SQLiteDatabase db1 = this.getReadableDatabase();
        String query = "SELECT * FROM food ";
        try (Cursor cursor = db1.rawQuery(query,null)) {
            if (cursor.moveToFirst()) {
                do {
                      int id = cursor.getInt(0);
                    String name = cursor.getString(1);
                      String price = cursor.getString(2);
                    byte[] image = cursor.getBlob(3);

                    returnList.add(new Food(name,price,image));

                } while (cursor.moveToNext());
            }
            cursor.close();
            db1.close();
        }
        return returnList;
    }


}





//package com.example.gallerydatabse;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.database.sqlite.SQLiteStatement;
//
//import androidx.annotation.Nullable;
//
//import java.util.ArrayList;
//
//public class DatabaseHelper extends SQLiteOpenHelper {
//    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }
//
//  public void queryData(String sql)
//  {
//      SQLiteDatabase db=getWritableDatabase();
//      db.execSQL(sql);
//  }
//  public boolean insertData(String name,String price, byte[] image)
//  {
//      SQLiteDatabase db=getWritableDatabase();
////      String sql="Insert Into food values(NULL,?,?,?)";
////      SQLiteStatement stmt=db.compileStatement(sql);
////      stmt.clearBindings();
////      stmt.bindString(1,name);
////      stmt.bindString(2,price);
////      stmt.bindBlob(3,image);
////      stmt.executeInsert();
//
//      //from previous project AndroidSqlite
//
//      ContentValues cv=new ContentValues();
//      cv.put("name",name);
//      cv.put("price",price);
//      cv.put("image",image);
//      long succ=db.insert("food",null,cv);
//      if(succ==-1)
//          return  false;
//          else
//              return true;
//
//  }
//
//  public Cursor getData(String sql)
//  {
//      SQLiteDatabase db=getReadableDatabase();
//      return db.rawQuery(sql,null);
//  }
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//String creatTable="CREATE TABLE IF NOT EXISTs food (id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR, price VARCHAR,image BLOB)";
//  db.execSQL(creatTable);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }
//
//
//    public ArrayList<String> fetch() {
//        ArrayList<String> returnList = new ArrayList<String>();
//        SQLiteDatabase db1 = this.getReadableDatabase();
//        String query = "SELECT name FROM food ";
//        try (Cursor cursor = db1.rawQuery(query,null)) {
//          if (cursor.moveToFirst()) {
//              do {
//                  //  int id = cursor.getInt(0);
//                  String name = cursor.getString(0);
//                  //  String price = cursor.getString(2);
//                  //  byte[] image = cursor.getBlob(3);
//
//                  returnList.add(name);
//
//              } while (cursor.moveToNext());
//          }
//            cursor.close();
//            db1.close();
//        }
//        return returnList;
//    }
//
//
//}
