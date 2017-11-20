package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import models.Dokter;

/**
 * Created by erick on 20/11/2017.
 */

public class DatabaseHandlerDokter extends SQLiteOpenHelper {

    public DatabaseHandlerDokter(Context context) {
        super(context,"dokterdb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String strCreateSql = "create table dokter (Nik string primary key,"+
                "Nama text,Alamat text, Spesialisasi text);";
        sqLiteDatabase.execSQL(strCreateSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists dokter");
        onCreate(sqLiteDatabase);
    }

    public void addWish(Dokter dokter){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Nik",dokter.getNik());
        values.put("Nama",dokter.getNama());
        values.put("Alamat",dokter.getAlamat());
        values.put("Spesialisasi",dokter.getSpesialisasi());

        db.insert("dokter",null,values);
        db.close();
    }

    public void deleteWish(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("dokter","Nik=?",new String[]{id});
        db.close();
    }

    public void updateWish(Dokter dokter){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Nama",dokter.getNama());
        values.put("Alamat",dokter.getAlamat());
        values.put("Spesialisasi",dokter.getSpesialisasi());
        db.update("dokter",values,"Nik=?",new String[]{dokter.getNik()});
        db.close();
    }

    public Dokter getDokterById(String Nik){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from dokter where Nik=?",
                new String[]{Nik});
        if(cursor!=null){
            cursor.moveToFirst();
        }

        Dokter dokter = new Dokter();
        dokter.setNik(cursor.getString(cursor.getColumnIndex("Nik")));
        dokter.setNama(cursor.getString(cursor.getColumnIndex("Nama")));
        dokter.setSpesialisasi(cursor.getString(cursor.getColumnIndex("Spesialisasi")));
        dokter.setAlamat(cursor.getString(cursor.getColumnIndex("Alamat")));
        db.close();
        return dokter;
    }

    public ArrayList<Dokter> getAllDokter(){
        ArrayList<Dokter> dokterList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from dokter order by Nama asc",null);
        if(cursor.moveToFirst()){
            do{
                Dokter dokter = new Dokter();
                dokter.setNik(cursor.getString(cursor.getColumnIndex("Nik")));
                dokter.setNama(cursor.getString(cursor.getColumnIndex("Nama")));
                dokter.setSpesialisasi(cursor.getString(cursor.getColumnIndex("Spesialisasi")));
                dokter.setAlamat(cursor.getString(cursor.getColumnIndex("Alamat")));
                dokterList.add(dokter);
            }while (cursor.moveToNext());
        }
        db.close();
        return dokterList;
    }
}
