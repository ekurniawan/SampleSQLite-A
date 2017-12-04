package services;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import models.Kategori;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by erick on 27/11/2017.
 */

public final class KategoriServices {
    private static final String SERVICE_URL = "http://actservices.azurewebsites.net/";
    private static final OkHttpClient _client;

    static {
        _client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS).readTimeout(30,TimeUnit.SECONDS).build();
    }

    public static List<Kategori> GetAllKategori() throws IOException {
        List<Kategori> listKategori = new ArrayList<>();
        Request request = new Request.Builder().url(SERVICE_URL+"api/Kategori").build();
        Response response = _client.newCall(request).execute();

        String result = response.body().string();
        try{
            JSONArray jsonArray = new JSONArray(result);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Kategori kategori = new Kategori();
                kategori.setKategoriID(jsonObject.getInt("KategoriID"));
                kategori.setNamaKategori(jsonObject.getString("NamaKategori"));
                listKategori.add(kategori);
            }
        }catch (JSONException jEx){
            Log.d("Kesalahan",jEx.getLocalizedMessage());
        }
        return listKategori;
    }

    public static int PostKategori(Kategori kategori) throws JSONException,IOException{
        HttpUrl.Builder urlBuilder = HttpUrl.parse(SERVICE_URL+"api/Kategori").newBuilder();
        String url = urlBuilder.build().toString();
        JSONObject newKategori = new JSONObject();
        newKategori.put("NamaKategori",kategori.getNamaKategori());
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),
                newKategori.toString());
        Request request = new Request.Builder().url(url).post(body).build();
        Response response = _client.newCall(request).execute();
        return response.code();
    }
}
