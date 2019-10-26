package com.example.student.phamvanduy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBBook extends SQLiteOpenHelper {


    public DBBook(Context context) {
        super(context, "book_manager", null, 1);
    }

    public DBBook(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Book (id interger primary key,tensach text, noidung text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Book");
        onCreate(db);

    }
    public boolean intsertBook(Book book)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",book.getId());
        contentValues.put("tensach",book.getTenSach());
        contentValues.put("noidung",book.getNoidung());
        db.insert("Book",null, contentValues);
        return true;

    }

    public ArrayList<Book> getBook(int id) {
        ArrayList<Book> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Book where id ="+id, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Book book = new Book (cursor.getInt(0),cursor.getString(1),cursor.getString(2));
            cursor.close();
            list.add(book);
        }
        db.close();

        return list;
    }
    public ArrayList<Book> getAllBook(){
        ArrayList<Book> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Book",null);
        if (cursor != null)
            cursor.moveToFirst();
        while (cursor.isAfterLast()== false){
            list.add(new Book((cursor.getInt(0)), cursor.getString(1),cursor.getString(2)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }
    public boolean deleteBook(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        int count = db.delete("Book","id" +"=?", new String[]{String.valueOf(id)});

        db.close();
        if (count > 0)
            return true;
        return false;
    }
    public boolean updateBook(Book book)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensach", book.getTenSach());
        contentValues.put("noidung", book.getNoidung());
        int count = db.update("Book",contentValues,"id" + "=?", new String[]{String.valueOf(book.getId())});
        db.close();
        if (count > 0)
            return true;
        else
            return false;
    }

}
