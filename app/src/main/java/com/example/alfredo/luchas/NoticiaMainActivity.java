package com.example.alfredo.luchas;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.LruCache;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.alfredo.luchas.clases.Noticia;
import com.example.alfredo.luchas.clases.VolleySingleton;

public class NoticiaMainActivity extends Activity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia_main);

        this.context = context;


        Noticia n = (Noticia) getIntent().getSerializableExtra("noticia");
        TextView titulo = (TextView)findViewById(R.id.noticiaMainTitulo);
        TextView fecha = (TextView)findViewById(R.id.noticiaMainFecha);
        TextView texto = (TextView)findViewById(R.id.noticiaMainTexto);
        NetworkImageView imagen = (NetworkImageView)findViewById(R.id.noticiaMainImagen);

        titulo.setText(n.titulo);
        fecha.setText(n.fecha);
        texto.setText(n.texto);

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
        imagen.setImageUrl(n.imagen,imageLoader);

    }
}
