package actualsolusi.com.samplesqlite;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import models.Kategori;
import services.KategoriServices;

public class ListKategoriActivity extends AppCompatActivity {
    private Button btnShow,btnInsert;
    private List<Kategori> listKategori;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_kategori);

        btnShow = (Button)findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadContent();
            }
        });

        btnInsert = (Button)findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertData();

            }
        });
    }

    private void InsertData(){
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                Kategori newKat = new Kategori();
                newKat.setNamaKategori("Android Studio Kelas A");
                try {
                    KategoriServices.PostKategori(newKat);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                Toast.makeText(getApplicationContext(),"Data berhasil ditambah !",
                        Toast.LENGTH_LONG).show();
            }
        }.execute();
    }

    private void loadContent(){
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    listKategori = KategoriServices.GetAllKategori();
                } catch (IOException e) {
                    Log.d("Kesalahan",e.getLocalizedMessage());
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                String strResult = "";
                for(Kategori kat : listKategori){
                    strResult += "KategoriID: "+ String.valueOf(kat.getKategoriID())+" - "+
                            "Nama Kategori: "+kat.getNamaKategori()+"\n";
                }
                Toast.makeText(ListKategoriActivity.this,strResult,Toast.LENGTH_LONG).show();
            }
        }.execute();
    }
}
