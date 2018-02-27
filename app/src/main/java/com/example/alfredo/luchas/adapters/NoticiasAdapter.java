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
import com.example.alfredo.luchas.clases.Noticia;
import com.example.alfredo.luchas.clases.VolleySingleton;


import java.util.List;

/**
 * Created by Alfredo on 25/02/2018.
 */

public class NoticiasAdapter extends ArrayAdapter<Noticia> {

    private Context context;

    public NoticiasAdapter(Context context, int resource, List<Noticia> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Noticia noticia = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.noticia_lista_layout, parent, false);
        }

        TextView titulo = (TextView)convertView.findViewById(R.id.tituloNoticia);
        TextView autor = (TextView)convertView.findViewById(R.id.autorNoticia);
        TextView fecha = (TextView)convertView.findViewById(R.id.fechaNoticia);
        NetworkImageView imagen = (NetworkImageView)convertView.findViewById(R.id.imagenNoticia);

        titulo.setText(noticia.titulo);
        autor.setText(noticia.autor);
        fecha.setText(noticia.fecha);


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
        imagen.setImageUrl(noticia.imagen,imageLoader);



        return convertView;
    }
}
