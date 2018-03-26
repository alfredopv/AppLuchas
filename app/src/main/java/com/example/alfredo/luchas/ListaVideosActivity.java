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
import com.example.alfredo.luchas.adapters.NoticiasAdapter;
import com.example.alfredo.luchas.adapters.VideosAdapter;
import com.example.alfredo.luchas.clases.Noticia;
import com.example.alfredo.luchas.clases.Video;
import com.example.alfredo.luchas.clases.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListaVideosActivity extends Activity {

    private VideosAdapter adapter;
    private ListView listView;
    private int numeroEmpresa;
    final private String link = "https://ddom.000webhostapp.com/ddom/campeones.json";
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_videos);

        numeroEmpresa = (int)getIntent().getSerializableExtra("numeroEmpresa");

        listView = (ListView)findViewById(R.id.listaVideos);
        adapter = new VideosAdapter(this, R.layout.video_layout, new ArrayList<Video>());
        listView.setAdapter(adapter);
        mQueue = VolleySingleton.getInstance(this).getRequestQueue();

        jsonVideos(getJSONString(link),adapter);

    }


    private void jsonVideos(String url, final VideosAdapter adapter){
        adapter.clear();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("empresas");
                    JSONObject jsonObject = jsonArray.getJSONObject(numeroEmpresa);
                    JSONArray videos = jsonObject.getJSONArray("videos");
                    for(int i = 0; i < videos.length(); i++){
                        JSONObject video = videos.getJSONObject(i);
                        Video v = new Video();
                        v.id = video.getString("id");
                        v.nombre = video.getString("nombre");
                        v.duracion = video.getString("duracion");
                        v.imagen = video.getString("imagen");
                        adapter.add(v);
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
