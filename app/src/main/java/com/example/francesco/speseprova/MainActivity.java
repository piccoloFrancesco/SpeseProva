package com.example.francesco.speseprova;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }//onCreate

    /**Svuota la textbox*/
    public void emptyImporto (View view){
        EditText importo = (EditText) findViewById(R.id.textImporto);
        importo.setText("");
    }//emptyImporto

    //(cristian) svuoto anche gli altri contenitori
    public void emptyData (View view){
        EditText data = (EditText) findViewById(R.id.textData);
        data.setText("");
    }//emptyData

    public void emptyDescription (View view){
        EditText descrizione = (EditText) findViewById(R.id.textType);
        descrizione.setText("");
    }//emptyDescription

    /**Messaggio che scrive su file il valore e cancella quello presente nelle caselle di test */
    public void saveValue (View view){
        EditText importo = (EditText) findViewById(R.id.textImporto);
        EditText data = (EditText) findViewById(R.id.textData);
        EditText desc = (EditText) findViewById(R.id.textType);
        String toSave =(importo.getText()+" | "+data.getText()+" | "+desc.getText()+"+");
        String filename = "listaScontrini";
        writeToFile(filename,toSave,getApplicationContext());
        //(cristian) quando si salva il valore,il box torna come all'inizio(edit: risulta più apprezzabile che il box torni vuoto)
        EditText new_importo = (EditText) findViewById(R.id.textImporto);
        new_importo.setText(null);
        EditText new_data = (EditText) findViewById(R.id.textData);
        new_data.setText(null);
        EditText new_description = (EditText) findViewById(R.id.textType);
        new_description.setText(null);
    }//saveValue

    /**Messaggio che scrive su file una stringa*/
    private void writeToFile(String filename,String data,Context context) {
        File path = context.getFilesDir();
        File file = new File(path, filename);
        try {
            FileOutputStream stream = new FileOutputStream(file,true);
            stream.write(data.getBytes());
            stream.close();
        } catch(java.io.IOException e){
            Log.e("IOException",e.toString());
        }//catch
    }//writeToFileAlt

    /** metodo che legge da file*/
    private String readFromFile(String filename,Context context){
        String ris="";
        File path = context.getFilesDir();
        File file = new File(path, filename);
        int length = (int) file.length();
        byte[] bytes = new byte[length];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(bytes);
            in.close();
        } catch (java.io.IOException e){
            Log.e("IOException",e.toString());
            ris=e.getMessage();
        }//catch
        ris += ""+new String(bytes);
        return ris;
    }//readFromFileAlt

    /**Metodo che stampa a schermo il contenuto del file*/
    public void readValue (View view){
        String filename = "listaScontrini";;
        String toPrint = readFromFile(filename,getApplicationContext()).replace("|","  ").replace("+","\n");
        TextView debug = (TextView) findViewById(R.id.textView);
        debug.setText(toPrint);
    }//readValueDEBUG
}//MainActivity
