package com.example.francesco.speseprova;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.text.Editable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }//onCreate

    /**Cerco di svuotare la textbox*/
    public void empty (View view){
        EditText importo = (EditText) findViewById(R.id.textImporto);
        importo.setText("");
    }//empty

    /**Messaggio che visualizza a schermo l'importo */
    public void saveValue (View view){
        EditText importo = (EditText) findViewById(R.id.textImporto);
        TextView textView  = (TextView) findViewById(R.id.textView);

        //analogamente,lettura dei valori su data e descrizione
        EditText data = (EditText) findViewById(R.id.textData);
        EditText description=(EditText) findViewById(R.id.textType);
        String im= importo.getText().toString();
        String dt=data.getText().toString();
        String ds=description.getText().toString();
        String resoconto=im+" "+dt+" "+ds;
        textView.setText(resoconto);

        /*TO DO: salvare il valore da qualche parte
        in un file?
        su un db?kkk
         */
        //(cristian) quando si salva il valore,il box torna come all'inizio
        EditText new_importo = (EditText) findViewById(R.id.textImporto);
        new_importo.setText("importo");
    }//saveValue
}//MainActivity
