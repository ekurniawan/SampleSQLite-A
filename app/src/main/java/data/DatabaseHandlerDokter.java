package data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by erick on 20/11/2017.
 */

public class DatabaseHandlerDokter extends SQLiteOpenHelper {

    public DatabaseHandlerDokter(Context context) {
        super(context,"dokterdb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
