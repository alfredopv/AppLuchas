package com.example.alfredo.luchas;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;

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
}
