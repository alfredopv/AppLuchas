package com.example.alfredo.luchas;

import android.app.Activity;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultadoTestActivity extends Activity {

    private int level;
    private TextView nivel, descripcion;
    private ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_test);
        level = (int)getIntent().getSerializableExtra("nivel");
        nivel = (TextView)findViewById(R.id.nivelResultado);
        descripcion = (TextView)findViewById(R.id.descripcionResultado);
        imagen = (ImageView)findViewById(R.id.imagenResultado);
        if(level < 4){
            nivel.setText("Nivel Novato");
            descripcion.setText("Eres apenas un novato en el mundo de la lucha libre profesional. Te falta ver varias luchas históricas de las empresas de Wrestling pero vas por buen camino, esfuerzate y serás todo un conocedor.");
            imagen.setImageResource(R.drawable.nacholibre);
        }else if(level < 7){
            nivel.setText("Nivel Intermedio");
            descripcion.setText("Llevas tiempo viendo la luca libre y la disfrutas mucho. Sin embargo recuerda que es un mundo muy grande y todavía te queda mucho por observar. Sigue así y serás la persona que más conozca de la lucha libre.");
            imagen.setImageResource(R.drawable.soberanojr);
        }else{
            nivel.setText("Nivel Experto");
            descripcion.setText("FELICIDADES! Haz alcanzado el nivel máximo de conocedor de la lucha libre. Eres de las personas que sabe más de la región. Comparte ese conocimiento con tus amigos para hacer crecer este gran deporte.");
            imagen.setImageResource(R.drawable.santo);
        }
    }
}
