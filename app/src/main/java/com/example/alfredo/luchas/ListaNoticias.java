package com.example.alfredo.luchas;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.alfredo.luchas.adapters.CampeonesAdapter;
import com.example.alfredo.luchas.adapters.NoticiasAdapter;
import com.example.alfredo.luchas.clases.Campeon;
import com.example.alfredo.luchas.clases.Noticia;
import com.example.alfredo.luchas.clases.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListaNoticias extends Activity {

    private ListView listView;
    private NoticiasAdapter adapter;
    private int numeroEmpresa;
    private String link;

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_noticias);

        System.out.print("Me inicie");
        numeroEmpresa = (int)getIntent().getSerializableExtra("numeroEmpresa");

        listView = (ListView)findViewById(R.id.listaNoticias);
        link = "https://ddom.000webhostapp.com/ddom/campeones.json";

        adapter = new NoticiasAdapter(this, R.layout.noticia_lista_layout, new ArrayList<Noticia>());
        listView.setAdapter(adapter);
        mQueue = VolleySingleton.getInstance(this).getRequestQueue();

        jsonNoticias(getJSONString(link),adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Noticia n = adapter.getItem(i);
                Intent intent = new Intent(ListaNoticias.this, NoticiaMainActivity.class);
                intent.putExtra("noticia", n);
                startActivity(intent);
            }
        });

    }

    private void jsonNoticias(String url, final NoticiasAdapter adapter){
        adapter.clear();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("empresas");
                    JSONObject jsonObject = jsonArray.getJSONObject(numeroEmpresa);
                    System.out.println(jsonObject);
                    JSONArray noticias = jsonObject.getJSONArray("noticias");
                    for(int i = 0; i < noticias.length(); i++){
                        JSONObject noticia = noticias.getJSONObject(i);
                        Noticia n = new Noticia();
                        n.titulo = noticia.getString("titulo");
                        n.fecha = noticia.getString("fecha");
                        n.autor = noticia.getString("autor");
                        n.texto = noticia.getString("texto");
                        n.imagen = noticia.getString("imagen");
                        n.fuente = noticia.getString("fuente");
                        adapter.add(n);
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

    private String getJSONString(String url){
        Uri builtUri;
        builtUri = Uri.parse(url).buildUpon().build();
        System.out.println(builtUri.toString());
        return builtUri.toString();
    }
}
