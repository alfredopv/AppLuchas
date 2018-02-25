package com.example.alfredo.luchas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.alfredo.luchas.clases.Empresa;

public class SeleccionActivity extends Activity {

    ImageButton wwe, njpw, aaa, cmll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion);

        wwe = (ImageButton)findViewById(R.id.botonWWE);
        njpw = (ImageButton)findViewById(R.id.botonNJPW);
        aaa = (ImageButton)findViewById(R.id.botonAAA);
        cmll = (ImageButton)findViewById(R.id.botonCMLL);

        wwe.setImageResource(R.drawable.wwe_cover);
        njpw.setImageResource(R.drawable.njpw_cover);
        aaa.setImageResource(R.drawable.aaa_cover);
        cmll.setImageResource(R.drawable.cmll_cover);
    }

    public void ActivaCMLL(View view){
        Empresa empresa = new Empresa();
        empresa.nombre = "Consejo Mundial de Lucha Libre";
        empresa.pais = "México";
        empresa.presidente = "Paco Alonso";
        empresa.fechaFundacion = "1933";
        empresa.id = 1;
        Intent intent =  new Intent(SeleccionActivity.this,MainActivity.class);
        intent.putExtra("empresa", empresa);
        startActivity(intent);
    }

    public void ActivaAAA(View view){
        Empresa empresa = new Empresa();
        empresa.nombre = "Asistencia Asesoría y Administración";
        empresa.pais = "México";
        empresa.presidente = "Marisela Peña";
        empresa.fechaFundacion = "1992";
        empresa.id = 0;
        Intent intent =  new Intent(SeleccionActivity.this,MainActivity.class);
        intent.putExtra("empresa", empresa);
        startActivity(intent);
    }

    public void ActivaWWE(View view){
        Empresa empresa = new Empresa();
        empresa.nombre = "World Wrestling Entertainment";
        empresa.pais = "Estados Unidos";
        empresa.presidente = "Vincent Kennedy McMahon";
        empresa.fechaFundacion = "1952";
        empresa.id = 2;
        Intent intent =  new Intent(SeleccionActivity.this,MainActivity.class);
        intent.putExtra("empresa", empresa);
        startActivity(intent);
    }

    public void ActivaNJPW(View view){
        Empresa empresa = new Empresa();
        empresa.nombre = "New Japan Pro Wrestling";
        empresa.pais = "Japón";
        empresa.presidente = "Kaname Tezuka";
        empresa.fechaFundacion = "1972";
        empresa.id = 3;
        Intent intent =  new Intent(SeleccionActivity.this,MainActivity.class);
        intent.putExtra("empresa", empresa);
        startActivity(intent);
    }


}
