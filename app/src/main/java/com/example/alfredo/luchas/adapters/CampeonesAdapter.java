package com.example.alfredo.luchas.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.alfredo.luchas.R;
import com.example.alfredo.luchas.clases.Campeon;
import com.example.alfredo.luchas.clases.VolleySingleton;

import java.util.List;

/**
 * Created by Alfredo on 24/02/2018.
 */

public class CampeonesAdapter extends ArrayAdapter<Campeon> {

    private Context context;

    public CampeonesAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Campeon campeon = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.campeon_layout,parent,false);
        }

        TextView nombre = (TextView)convertView.findViewById(R.id.nombreLuchador);
        TextView edad = (TextView)convertView.findViewById(R.id.edadLuchador);
        TextView experiencia = (TextView)convertView.findViewById(R.id.experienciaLuchador);
        TextView nacionalidad = (TextView)convertView.findViewById(R.id.nacionalidadLuchador);
        TextView campeonato = (TextView)convertView.findViewById(R.id.campeonatoLuchador);
        NetworkImageView imagen = (NetworkImageView) convertView.findViewById(R.id.imagenLuchador);

        nombre.setText("Nombre: " + campeon.nombre);
        edad.setText("Año Nacimiento: " + campeon.edad);
        experiencia.setText("Año Debut: " + campeon.experiencia);
        nacionalidad.setText("Nacionalidad: " + campeon.nacionalidad);
        campeonato.setText(campeon.campeonato);

        RequestQueue requestQueue = VolleySingleton.getInstance(context).getRequestQueue();
        ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(10);
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
        imagen.setImageUrl(campeon.foto,imageLoader);


        return convertView;
    }
}
