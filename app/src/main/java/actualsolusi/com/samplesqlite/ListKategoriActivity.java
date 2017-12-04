package actualsolusi.com.samplesqlite;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import models.Kategori;
import services.KategoriServices;

public class ListKategoriActivity extends AppCompatActivity {
    private Button btnShow;
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
