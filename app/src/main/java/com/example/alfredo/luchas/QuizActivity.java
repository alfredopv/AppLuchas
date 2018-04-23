package com.example.alfredo.luchas;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.alfredo.luchas.clases.Pregunta;
import com.example.alfredo.luchas.clases.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuizActivity extends Activity {

    private int numeroEmpresa;
    final private String link = "https://ddom.000webhostapp.com/ddom/campeones.json";
    private RequestQueue mQueue;

    private ArrayList<Pregunta> preguntas;

    private TextView textViewMarcador;
    private TextView textViewPregunta;
    private Button res1, res2, res3, salida;

    private String respuesta;
    private int marcador = 0;
    private int numPregunta = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        numeroEmpresa = (int)getIntent().getSerializableExtra("numeroEmpresa");
        preguntas = new ArrayList<>();
        textViewMarcador = (TextView)findViewById(R.id.score);
        textViewPregunta = (TextView)findViewById(R.id.question);
        res1 = (Button)findViewById(R.id.choice1);
        res2 = (Button)findViewById(R.id.choice2);
        res3 = (Button)findViewById(R.id.choice3);
        salida = (Button)findViewById(R.id.salir);

        cargarPreguntas(numeroEmpresa);



        res1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(res1.getText() == respuesta){
                    marcador += 1;
                    actualizaMarcador(marcador);
                    actualizaPreguntas();
                    Toast.makeText(QuizActivity.this, "CORRECTO", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(QuizActivity.this, "INCORRECTO", Toast.LENGTH_SHORT).show();
                    actualizaPreguntas();
                }
            }
        });

        res2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(res2.getText() == respuesta){
                    marcador += 1;
                    actualizaMarcador(marcador);
                    actualizaPreguntas();
                    Toast.makeText(QuizActivity.this, "CORRECTO", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(QuizActivity.this, "INCORRECTO", Toast.LENGTH_SHORT).show();
                    actualizaPreguntas();
                }
            }
        });

        res3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(res3.getText() == respuesta){
                    marcador += 1;
                    actualizaMarcador(marcador);
                    actualizaPreguntas();
                    Toast.makeText(QuizActivity.this, "CORRECTO", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(QuizActivity.this, "INCORRECTO", Toast.LENGTH_SHORT).show();
                    actualizaPreguntas();
                }
            }
        });

        salida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //System.out.println(preguntas[numPregunta].question);
        actualizaPreguntas();
    }

    private void actualizaPreguntas(){
        if(numPregunta >= 10){
            Intent it = new Intent(QuizActivity.this, ResultadoTestActivity.class);
            it.putExtra("nivel", marcador);
            startActivity(it);
            finish();
        }else{
            Pregunta p = preguntas.get(numPregunta);
            System.out.println(p);
            textViewPregunta.setText(p.question);
            res1.setText(p.options.get(0));
            res2.setText(p.options.get(1));
            res3.setText(p.options.get(2));
            respuesta = p.answer;
            numPregunta++;
        }
    }

    private void actualizaMarcador(int total){
        textViewMarcador.setText(total + "");
    }

    private void cargarPreguntas(int n){
        switch (n){
            case 0:
                Pregunta p0 = new Pregunta();
                p0.question = "¿Quién es el máximo ganador del torneo Rey de Reyes?";
                p0.answer = "La Parka";
                p0.options.add("El Cibernético");
                p0.options.add("El Hijo del Perro Aguayo");
                p0.options.add("La Parka");
                preguntas.add(p0);
                Pregunta p1 = new Pregunta();
                p1.question = "¿Quién desenmascaró a Dr Wagner Jr en Triplemanía XXV?";
                p1.answer = "Psycho Clown";
                p1.options.add("Psycho Clown");
                p1.options.add("Pagano");
                p1.options.add("Johnny Mundo");
                preguntas.add(p1);
                Pregunta p2 = new Pregunta();
                p2.question = "¿Quién fundó la AAA?";
                p2.answer = "Antonio Peña";
                p2.options.add("Antonio Peña");
                p2.options.add("Joaquín Roldan");
                p2.options.add("Dorian Roldan");
                preguntas.add(p2);
                Pregunta p3 = new Pregunta();
                p3.question = "Significado de las siglas AAA";
                p3.answer = "Asistencia, Asesoría y Administración";
                p3.options.add("Administración, Asistencia y Asesoría");
                p3.options.add("Asesoría, Asistencia y Administración");
                p3.options.add("Asistencia, Asesoría y Administración");
                preguntas.add(p3);
                Pregunta p4 = new Pregunta();
                p4.question = "Luchador con más cantidad de reinados del Megacampeonato AAA";
                p4.answer = "El Mesías";
                p4.options.add("El Mesías");
                p4.options.add("Dr Wagner Jr");
                p4.options.add("Texano Jr");
                preguntas.add(p4);
                Pregunta p5 = new Pregunta();
                p5.question = "Primer luchador en ganar el Megacampeonato AAA";
                p5.answer = "El Mesías";
                p5.options.add("El Cibernético");
                p5.options.add("El Mesías");
                p5.options.add("La Parka");
                preguntas.add(p5);
                Pregunta p6 = new Pregunta();
                p6.question = "Año del último 'Rey de Reyes' ganado por la Parka";
                p6.answer = "2014";
                p6.options.add("2014");
                p6.options.add("2015");
                p6.options.add("2016");
                preguntas.add(p6);
                Pregunta p7 = new Pregunta();
                p7.question = "¿Qué luchador aplicaba 'El Martinete' como movimiento especial?";
                p7.answer = "Abismo Negro";
                p7.options.add("El Cibernético");
                p7.options.add("Abismo Negro");
                p7.options.add("Chessman");
                preguntas.add(p7);
                Pregunta p8 = new Pregunta();
                p8.question = "¿Quién es el actual director de talentos AAA?";
                p8.answer = "Vampiro";
                p8.options.add("Marisela Peña");
                p8.options.add("Vampiro");
                p8.options.add("Raúl 'Copetes' Salazar");
                preguntas.add(p8);
                Pregunta p9 = new Pregunta();
                p9.question = "¿A quién le pertenece el movimiento especial 'Noa Noa Driver'";
                p9.answer = "Pagano";
                p9.options.add("Pagano");
                p9.options.add("Chessman");
                p9.options.add("Hijo del Fantasma");
                preguntas.add(p9);
                break;
            case 1:
                Pregunta p10 = new Pregunta();
                p10.question = "¿A qué agrupación pertece Rush?";
                p10.answer = "Los Ingobernables";
                p10.options.add("Los Intratables");
                p10.options.add("Los Internacionales");
                p10.options.add("Los Ingobernables");
                preguntas.add(p10);
                Pregunta p11 = new Pregunta();
                p11.question = "¿Qué significan las siglas CMLL?";
                p11.answer = "Consejo Mundal de Lucha Libre";
                p11.options.add("Consejo Mundal de Lucha Libre");
                p11.options.add("Comisión Mexicana de Lucha Libre");
                p11.options.add("Cooperación Máxima de Lucha Libre");
                preguntas.add(p11);
                Pregunta p12 = new Pregunta();
                p12.question = "¿A quién se le conoce como el 'Ídolo de los Niños'?";
                p12.answer = "Atlantis";
                p12.options.add("Místico");
                p12.options.add("Atlantis");
                p12.options.add("Caristico");
                preguntas.add(p12);
                Pregunta p13 = new Pregunta();
                p13.question = "Actual campeón de Peso Completo de CMLL";
                p13.answer = "Marco Corleone";
                p13.options.add("Carístico");
                p13.options.add("Rush");
                p13.options.add("Marco Corleone");
                preguntas.add(p13);
                Pregunta p14 = new Pregunta();
                p14.question = "¿En qué año se celebró el evento Padrísimo?";
                p14.answer = "2000";
                p14.options.add("1999");
                p14.options.add("2000");
                p14.options.add("2001");
                preguntas.add(p14);
                Pregunta p15 = new Pregunta();
                p15.question = "¿Quién desenmascaró a Volador Jr?";
                p15.answer = "La Sombra";
                p15.options.add("Diamante Azul");
                p15.options.add("Blue Panther");
                p15.options.add("La Sombra");
                preguntas.add(p15);
                Pregunta p16 = new Pregunta();
                p16.question = "¿A quién se le conoce como 'El pequeño maestro'?";
                p16.answer = "Virus";
                p16.options.add("Virus");
                p16.options.add("Shokercito");
                p16.options.add("Pierrothito");
                preguntas.add(p16);
                Pregunta p17 = new Pregunta();
                p17.question = "¿Contra quién perdió la máscara El Último Guerrero?";
                p17.answer = "Atlantis";
                p17.options.add("Místico");
                p17.options.add("La Sombra");
                p17.options.add("Atlantis");
                preguntas.add(p17);
                Pregunta p18 = new Pregunta();
                p18.question = "¿Quién es el 'Maestro Lagunero'?";
                p18.answer = "Blue Panther";
                p18.options.add("Blue Panther");
                p18.options.add("Negro Casas");
                p18.options.add("El Último Guerrero");
                preguntas.add(p18);
                Pregunta p19 = new Pregunta();
                p19.question = "¿A quién desenmascaró El Cuatrero en 'Homenaje a dos Leyendas 2018'";
                p19.answer = "Ángel de Oro";
                p19.options.add("Ángel de Oro");
                p19.options.add("Soberano Jr");
                p19.options.add("Niebla Roja");
                preguntas.add(p19);
                break;
            case 2:
                Pregunta p20 = new Pregunta();
                p20.question = "¿Qué significan las siglas WWE?";
                p20.answer = "World Wrestling Entertainment";
                p20.options.add("World Wrestling Entertainment");
                p20.options.add("Wrestling World Entetainning");
                p20.options.add("World Wide Entartainment");
                preguntas.add(p20);
                Pregunta p21 = new Pregunta();
                p21.question = "¿Quién es el dueño actual de WWE?";
                p21.answer = "Vince Mcmahon";
                p21.options.add("Vince Mcmahon");
                p21.options.add("Shane Mcmahon");
                p21.options.add("Eric Bishop");
                preguntas.add(p21);
                Pregunta p22 = new Pregunta();
                p22.question = "¿Cuál es el evento máximo de la empresa?";
                p22.answer = "Wrestlemania";
                p22.options.add("Royal Rumble");
                p22.options.add("Summerslam");
                p22.options.add("Wrestlemania");
                preguntas.add(p22);
                Pregunta p23 = new Pregunta();
                p23.question = "¿Quién ganó la lucha 'Icon vs Icon' en Wrestlemania X8?";
                p23.answer = "The Rock";
                p23.options.add("The Rock");
                p23.options.add("Hulk Hogan");
                p23.options.add("Empate");
                preguntas.add(p23);
                Pregunta p24 = new Pregunta();
                p24.question = "¿Quién perdió la cabellera en 'La Batalla de los Billonarios' en Wrestlemania 24?";
                p24.answer = "Vince McMahon";
                p24.options.add("Donald Trump");
                p24.options.add("Vince McMahon");
                p24.options.add("Los dos");
                preguntas.add(p24);
                Pregunta p25 = new Pregunta();
                p25.question = "¿Qué luchadora ganó el campeonato femenil en su primera lucha en Raw?";
                p25.answer = "Paige";
                p25.options.add("AJ Lee");
                p25.options.add("Charlotte Flair");
                p25.options.add("Paige");
                preguntas.add(p25);
                Pregunta p26 = new Pregunta();
                p26.question = "¿Quién retiró a 'Macho Man' Randy Savage en Wrestlemania VII?";
                p26.answer = "Ultimate Warrior";
                p26.options.add("Hulk Hogan");
                p26.options.add("Undertaker");
                p26.options.add("Ultimate Warrior");
                preguntas.add(p26);
                Pregunta p27 = new Pregunta();
                p27.question = "¿Quién terminó con la racha del Undertaker en Wrestlemania?";
                p27.answer = "Brock Lesnar";
                p27.options.add("Shawn Michaels");
                p27.options.add("Triple H");
                p27.options.add("Brock Lesnar");
                preguntas.add(p27);
                Pregunta p28 = new Pregunta();
                p28.question = "¿Qué luchador tiene el record de más campeonatos mundiales?";
                p28.answer = "Ric Flair";
                p28.options.add("Ric Flair");
                p28.options.add("John Cena");
                p28.options.add("Hulk Hogan");
                preguntas.add(p28);
                Pregunta p29 = new Pregunta();
                p29.question = "¿Quién ganó la lucha del Main Event de Wrestlemania III?";
                p29.answer = "Hulk Hogan";
                p29.options.add("Andre the Giant");
                p29.options.add("Hulk Hogan");
                p29.options.add("Empate");
                preguntas.add(p29);
                break;
            case 3:
                Pregunta p30 = new Pregunta();
                p30.question = "¿Qué significan las siglas NJPW?";
                p30.answer = "New Japan Pro Wrestling";
                p30.options.add("Native Japan Professional Wrestling");
                p30.options.add("New Japan Pro Wrestling");
                p30.options.add("National Japan Performance Wrestling");
                preguntas.add(p30);
                Pregunta p31 = new Pregunta();
                p31.question = "Máximo Evento de la Empresa";
                p31.answer = "Wrestle Kingdom";
                p31.options.add("G1 Climax");
                p31.options.add("Wrestle Kingdom");
                p31.options.add("Dominion");
                preguntas.add(p31);
                Pregunta p32 = new Pregunta();
                p32.question = "¿Quién es el líder de Los Ingobernables de Japón?";
                p32.answer = "Tetsuya Naito";
                p32.options.add("Evil");
                p32.options.add("Sanada");
                p32.options.add("Tetsuya Naito");
                preguntas.add(p32);
                Pregunta p33 = new Pregunta();
                p33.question = "¿Qué luchador tiene más reinados con el campeonato IWGP Heavyweight?";
                p33.answer = "Hiroshi Tanahashi";
                p33.options.add("Kasuchika Okada");
                p33.options.add("Tatsumi Fujinami");
                p33.options.add("Hiroshi Tanahashi");
                preguntas.add(p33);
                Pregunta p34 = new Pregunta();
                p34.question = "¿Qué luchadores son Los Young Bucks?";
                p34.answer = "Matt Jackson y Nick Jackson";
                p34.options.add("Matt Jackson y Nick Jackson");
                p34.options.add("Kenny Omega y Kota Ibushi");
                p34.options.add("Tama Tonga y Tanga Loa");
                preguntas.add(p34);
                Pregunta p35 = new Pregunta();
                p35.question = "¿Quién fue el primer campeon de Estados Unidos IWGP?";
                p35.answer = "Kenny Omega";
                p35.options.add("Kenny Omega");
                p35.options.add("Juice Robinson");
                p35.options.add("Jay White");
                preguntas.add(p35);
                Pregunta p36 = new Pregunta();
                p36.question = "¿Quién es el luchador con mayor número de victorias en el G1 Climax?";
                p36.answer = "Antonio Inoki";
                p36.options.add("Tetsuya Naito");
                p36.options.add("Antonio Inoki");
                p36.options.add("Masahiro Chono");
                preguntas.add(p36);
                Pregunta p37 = new Pregunta();
                p37.question = "¿Quién fue el primer líder del Bullet CLub?";
                p37.answer = "Prince Devitt";
                p37.options.add("Karl Anderson");
                p37.options.add("Prince Devitt");
                p37.options.add("AJ Styles");
                preguntas.add(p37);
                Pregunta p38 = new Pregunta();
                p38.question = "¿Cuál de las siguientes luchas obtuvo una calificación de 6 estrellas?";
                p38.answer = "Kasuchika Okada vs Kenny Omega";
                p38.options.add("Kasuchika Okada vs Hiroshi Tanahashi");
                p38.options.add("Kasuchika Okada vs Kenny Omega");
                p38.options.add("Kasuchika Okada vs Tetsuya Naito");
                preguntas.add(p38);
                Pregunta p39 = new Pregunta();
                p39.question = "¿En que WrestleKingdom retuvo Kasuchika Okada el campeonato IWGP Heavyweight ante Hiroshi Tanahashi?";
                p39.answer = "Wrestle Kingdom 10";
                p39.options.add("Wrestle Kingdom 7");
                p39.options.add("Wrestle Kingdom 9");
                p39.options.add("Wrestle Kingdom 10");
                preguntas.add(p39);
                break;
        }

    }
}
