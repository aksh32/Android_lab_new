package com.example.studentadmin.studentsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper  extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "student.db";
    public static final String col1 = "id";
    public static final String col2 = "Fname";
    public static final String col3 = "Lname";
    public static final String col4 = "marks";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase .execSQL("CREATE TABLE student_Details  (id integer PRIMARY KEY, Fname text, Lname text, marks int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE if exists student_Details");
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(int id, String FName, String LName,int marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col1,id);
        cv.put(col2,FName);
        cv.put(col3,LName);
        cv.put(col4,marks);

        db.insert("student_Details",null,cv);
        return true;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM student_Details",null);
        return res;
    }

    public Integer deleteData(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        Integer deleteRows = db.delete("student_Details","id=?",new String[]{id.toString()});
        return deleteRows;
    }

    public boolean updateData(Integer id, String FName, String LName, Integer marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(col1,id);
        cv.put(col2,FName);
        cv.put(col3,LName);
        cv.put(col4,marks);
        db.update("student_Details",cv,"id=?",new String[]{id.toString()});

        return true;
    }
}
