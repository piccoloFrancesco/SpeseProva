package com.example.francesco.speseprova;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

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
        EditText data = (EditText) findViewById(R.id.textData);
        data.setText("");
    }//emptyData

    /**Svuota la textbox*/
    public void emptyDescrizione (View view){
        EditText descrizione = (EditText) findViewById(R.id.textType);
        descrizione.setText("");
    }//emptyDescrizione

    /**Messaggio che visualizza a schermo l'importo */
    public void saveValue (View view){
        EditText importo = (EditText) findViewById(R.id.textImporto);
        EditText data = (EditText) findViewById(R.id.textData);
        EditText desc = (EditText) findViewById(R.id.textType);
        TextView textView  = (TextView) findViewById(R.id.textView);
        String toSave =(importo.getText()+"|"+data.getText()+"|"+desc.getText());
        //Debug per vedere se ha preso correttamente i dati
        textView.setText("importo/data/descrizione"+"\n"+toSave);
        //TO DO: salvare il valore su un file
        String filename = "listaScontrini";
        writeToFile(filename,toSave,getApplicationContext());
        //(cristian) quando si salva il valore,il box torna come all'inizio
        EditText new_importo = (EditText) findViewById(R.id.textImporto);
        new_importo.setText("importo");
    }//saveValue

    private void writeToFile(String filename,String data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_APPEND));
            outputStreamWriter.write("\n"+data);
            outputStreamWriter.close();
        }//try
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }//catch
    }//writeToFile

    private String readFromFile(Context context,String filename) {
        String ret = "";
        try {
            InputStream inputStream = context.openFileInput(filename);
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }//while
                inputStream.close();
                ret = stringBuilder.toString();
            }//if
        }//try
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }//catch
        return ret;
    }//redFromFile

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }//if
        return false;
    }//isExternalStorageWritable

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }//if
        return false;
    }//isExternalStorageReadable

    /**Metodo di debug per la lettura del file*/
    public void readValueDEBUG (View view){
        String filename = "listaScontrini";
        //TO DO: lettura del file ed inserimento nella textview
        TextView debug = (TextView) findViewById(R.id.textView);
        debug.setText("Debug\n"+readFromFile(getApplicationContext(),filename));
    }//readValueDEBUG
}//MainActivity
