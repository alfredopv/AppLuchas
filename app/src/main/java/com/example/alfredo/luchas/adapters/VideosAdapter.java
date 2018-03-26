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
import com.example.alfredo.luchas.clases.Video;
import com.example.alfredo.luchas.clases.VolleySingleton;


import java.util.List;

/**
 * Created by Alfredo on 25/03/2018.
 */

public class VideosAdapter extends ArrayAdapter<Video> {

    private Context context;

    public VideosAdapter(Context context, int resource, List<Video> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Video video = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.video_layout, parent, false);
        }

        NetworkImageView imagen = (NetworkImageView) convertView.findViewById(R.id.imagenVideo);
        TextView tituloVideo = (TextView)convertView.findViewById(R.id.tituloVideo);
        TextView duracionVideo = (TextView)convertView.findViewById(R.id.duracionVideo);

        tituloVideo.setText(video.nombre);
        duracionVideo.setText(video.duracion);

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
        imagen.setImageUrl(video.imagen,imageLoader);

        return convertView;
    }
}
