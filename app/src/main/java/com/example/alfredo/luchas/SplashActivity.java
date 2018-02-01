package com.example.alfredo.luchas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class SplashActivity extends Activity {

    private ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imagen = (ImageView)findViewById(R.id.imageSplash);
        imagen.setImageResource(R.drawable.lucha);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent it = new Intent(SplashActivity.this,SeleccionActivity.class);
                startActivity(it);
                finish();
            }
        },5000);
    }
}
