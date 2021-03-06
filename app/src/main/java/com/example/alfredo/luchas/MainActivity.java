package com.example.alfredo.luchas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alfredo.luchas.clases.Empresa;

public class MainActivity extends Activity {

    private TextView nombre, pais, presidente, fecha;
    private ImageView imagen;
    private ImageButton noticias, campeones, videos, test;
    private int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = (TextView)findViewById(R.id.nombreEmpresa);
        pais = (TextView)findViewById(R.id.paisEmpresa);
        presidente = (TextView)findViewById(R.id.presidenteEmpresa);
        fecha = (TextView)findViewById(R.id.fechaEmpresa);
        imagen = (ImageView)findViewById(R.id.imagenMain);
        noticias = (ImageButton)findViewById(R.id.botonNoticias);
        campeones = (ImageButton)findViewById(R.id.botonCampeones);
        videos = (ImageButton)findViewById(R.id.botonVideos);
        test = (ImageButton)findViewById(R.id.botonTest);

        Empresa empresa = (Empresa)getIntent().getSerializableExtra("empresa");
        nombre.setText(empresa.nombre);
        value = empresa.id;


        pais.setText("País: " + empresa.pais);
        presidente.setText("Presidente: " + empresa.presidente);
        fecha.setText("Año de Fundación: " + empresa.fechaFundacion);

        switch (empresa.nombre){
            case "Asistencia Asesoría y Administración":
                imagen.setImageResource(R.drawable.aaa_cover);
                break;
            case "Consejo Mundial de Lucha Libre":
                imagen.setImageResource(R.drawable.cmll_cover);
                break;
            case "World Wrestling Entertainment":
                imagen.setImageResource(R.drawable.wwe_cover);
                break;
            case "New Japan Pro Wrestling":
                imagen.setImageResource(R.drawable.njpw_cover);
                break;
        }

        noticias.setImageResource(R.drawable.noticias);
        campeones.setImageResource(R.drawable.campeones);
        videos.setImageResource(R.drawable.videos);
        test.setImageResource(R.drawable.test);
    }

    public void SiguientePagina(View view){
        switch(view.getId()){
            case R.id.botonNoticias:
                Intent intent = new Intent(MainActivity.this, ListaNoticias.class);
                intent.putExtra("numeroEmpresa", value);
                startActivity(intent);
                break;
            case R.id.botonCampeones:
                Intent intent2 = new Intent(MainActivity.this, ListaCampeones.class);
                intent2.putExtra("numeroEmpresa", value);
                System.out.print("Campeones");
                //intent2.putExtra("tipo", "campeones");
                startActivity(intent2);
                break;
            case R.id.botonVideos:
                Intent intent3 = new Intent(MainActivity.this, ListaVideosActivity.class);
                intent3.putExtra("numeroEmpresa", value);
                startActivity(intent3);
                break;
            case R.id.botonTest:
                Intent intent4 = new Intent(MainActivity.this, QuizActivity.class);
                intent4.putExtra("numeroEmpresa", value);
                startActivity(intent4);
                break;
        }
    }

}
