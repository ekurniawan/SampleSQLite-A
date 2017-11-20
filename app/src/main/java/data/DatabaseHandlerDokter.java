package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
}
