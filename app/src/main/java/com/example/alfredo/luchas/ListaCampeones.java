package com.example.alfredo.luchas;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.alfredo.luchas.adapters.CampeonesAdapter;
import com.example.alfredo.luchas.clases.Campeon;
import com.example.alfredo.luchas.clases.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListaCampeones extends Activity {

    private ListView listView;
    private CampeonesAdapter adapter;
    private int numeroEmpresa;
    private String link;

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        numeroEmpresa = (int)getIntent().getSerializableExtra("numeroEmpresa");

        setContentView(R.layout.activity_lista_campeones);

        listView = (ListView)findViewById(R.id.listaCampeones);
        link = "https://ddom.000webhostapp.com/ddom/campeones.json";

        adapter = new CampeonesAdapter(this, R.layout.campeon_layout, new ArrayList<Campeon>());
        listView.setAdapter(adapter);
        mQueue = VolleySingleton.getInstance(this).getRequestQueue();

        jsonCampeones(getLuchadoresString(link),adapter);
    }

    private void jsonCampeones(String url, final CampeonesAdapter adapter){
        adapter.clear();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("empresas");
                    JSONObject jsonObject = jsonArray.getJSONObject(numeroEmpresa);
                    System.out.println(jsonObject);
                    JSONArray luchadores = jsonObject.getJSONArray("campeones");
                    for(int i = 0; i < luchadores.length(); i++){
                        JSONObject luchador = luchadores.getJSONObject(i);
                        Campeon c = new Campeon();
                        c.nombre = luchador.getString("luchador");
                        System.out.println(c.nombre);
                        c.edad = luchador.getString("nacimiento");
                        c.experiencia = luchador.getString("debut");
                        c.nacionalidad = luchador.getString("nacionalidad");
                        c.campeonato = luchador.getString("titulo");
                        c.foto = luchador.getString("imagen");
                        adapter.add(c);
                    }
                    adapter.notifyDataSetChanged(); //actualiza la vista
                } catch (JSONException e) {
                    System.out.println("Sí hay error");
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Sí hay error");
            }
        });
        mQueue.add(request);
    }

    private String getLuchadoresString(String url){
        Uri builtUri;
        builtUri = Uri.parse(url).buildUpon().build();
        System.out.println(builtUri.toString());
        return builtUri.toString();
    }

}
