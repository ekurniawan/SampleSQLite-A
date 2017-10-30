package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import models.MyWish;

/**
 * Created by erick on 30/10/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        super(context,"wishdb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String strCreateSql = "create table wishes (itemId integer primary key autoincrement,"+
                "title text,content text, recordDate long);";
        sqLiteDatabase.execSQL(strCreateSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists wishes");
        onCreate(sqLiteDatabase);
    }

    public void addWish(MyWish wish){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title",wish.getTitle());
        values.put("content",wish.getContent());
        values.put("recordDate",java.lang.System.currentTimeMillis());

        db.insert("wishes",null,values);
        db.close();
    }

    public void deleteWish(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("wishes","itemId=?",new String[]{String.valueOf(id)});
        db.close();
    }

    private void updateWish(MyWish wish){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title",wish.getTitle());
        values.put("content",wish.getContent());
        db.update("wishes",values,"itemId=?",new String[]{String.valueOf(wish.getItemId())});
        db.close();
    }
}
