package com.example.francesco.speseprova;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }//onCreate

    /**Svuota la textbox*/
    public void emptyImporto (View view){
        EditText importo = (EditText) findViewById(R.id.textImporto);
        importo.setText("");
    }//emptyImporto

    /**Svuota la textbox*/
    public void emptyData (View view){
        EditText importo = (EditText) findViewById(R.id.textImporto);
        importo.setText("");
    }//emptyData

    /**Svuota la textbox*/
    public void emptyDescrizione (View view){
        EditText importo = (EditText) findViewById(R.id.textImporto);
        importo.setText("");
    }//emptyDescrizione

    /**Messaggio che visualizza a schermo l'importo */
    public void saveValue (View view){
        EditText importo = (EditText) findViewById(R.id.textImporto);
        EditText data = (EditText) findViewById(R.id.textData);
        EditText desc = (EditText) findViewById(R.id.textType);
        TextView textView  = (TextView) findViewById(R.id.textView);
        //Debug per vedere se ha preso correttamente i dati
        textView.setText("importo: "+importo.getText()+"\ndata: "+data.getText()+"\ndecrizione: "+desc.getText());

        //TO DO: salvare il valore su un file

        //(cristian) quando si salva il valore,il box torna come all'inizio
        EditText new_importo = (EditText) findViewById(R.id.textImporto);
        new_importo.setText("importo");
    }//saveValue
}//MainActivity
