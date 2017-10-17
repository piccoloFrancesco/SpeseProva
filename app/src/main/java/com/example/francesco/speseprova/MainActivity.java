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
        String filename = "listaScontrini";
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());
        String fileStringa = readFromFile(filename, getApplicationContext());
        if(fileStringa.equals("IOException")){
            fileStringa="";
        }//if
        textView.setText(fileStringa);
    }//onCreate

    /**Svuota la textbox importo
     * Autore: Cristian Lazzarin
     * @param view */
    public void emptyImporto (View view){
        EditText importo = (EditText) findViewById(R.id.textImporto);
        importo.setText(null);
    }//emptyImporto

    /**Svuota la textbox importo
     * Autore: Cristian Lazzarin
     * @param view */
    public void emptyData (View view){
        EditText data = (EditText) findViewById(R.id.textData);
        data.setText(null);
    }//emptyData

    /**Svuota la textbox importo
     * Autore: Cristian Lazzarin
     * @param view */
    public void emptyDescription (View view){
        EditText descrizione = (EditText) findViewById(R.id.textType);
        descrizione.setText(null);
    }//emptyDescription

    /**Salva i dati delle textbox nel file
     * Autori: Francesco Piccolo, Cristian Lazzarin, Nicola Dal Maso
     * @param view */
    public void saveValue (View view){
        EditText importo = (EditText) findViewById(R.id.textImporto);
        EditText data = (EditText) findViewById(R.id.textData);
        EditText desc = (EditText) findViewById(R.id.textType);
        String toSave =(importo.getText()+"€   "+data.getText()+"   "
                +desc.getText()+"\n");
        String filename = "listaScontrini";
        writeToFile(filename,toSave,getApplicationContext());
        emptyImporto(view);
        emptyData(view);
        emptyDescription(view);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(readFromFile(filename, getApplicationContext()));
    }//saveValue

    /**Scrive su file la stringa data
     * Autore: Francesco Piccolo
     * @param filename il nome del file
     * @param data la stringa da scrivere sul file
     * @param context */
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
    }//writeToFile

    /**legge da file la stringa data
     * Autore: Francesco Piccolo
     * @param filename il nome del file
     * @param context */
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
            ris="IOException";
        }//catch
        ris += ""+new String(bytes);
        return ris;
    }//readFromFile

}//MainActivity
