package com.example.francesco.speseprova;

import android.content.Context;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String filename = "listaScontrini";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler db = new DatabaseHandler(this);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());
        String log="";
        db.addReceipt(new Receipt(1,52.42,"20/10/2017","onCreate1"));
        db.addReceipt(new Receipt(2,124655,"20/10/2017","onCreate2"));
        db.addReceipt(new Receipt(3,1024.21,"20/10/2017","onCreate3"));
        List<Receipt> receipts = db.getAllReceipts();
        for (Receipt cn : receipts) {
             log += "Id: " + cn.getID() + " ,Importo: " + cn.getImporto() + " ,Data: " + cn.getDate()+ ",Descrizione: "+cn.getDesc();
        }//for
        if(log.equals(""))
            log="Database Vuoto!";
        textView.setText(log);
    }//onCreate

    public void saveValueDB(View view){
        EditText importo = (EditText) findViewById(R.id.textImporto);
        EditText data = (EditText) findViewById(R.id.textData);
        EditText desc = (EditText) findViewById(R.id.textType);
        DatabaseHandler db = new DatabaseHandler(this);
        db.addReceipt(new Receipt(40,Double.parseDouble(importo.toString()),data.toString(),desc.toString()));
        //Controllo input
        /*if((!(importo.getText().toString().equals("")))/*&&(Double.parseDouble(importo.getText().toString())<10000000.0)) {
            if(!(data.getText().toString().equals(""))){
                db.addReceipt(new Receipt(1,Double.parseDouble(importo.toString()),data.toString(),desc.toString()));
                emptyImporto(view);
                emptyData(view);
                emptyDescription(view);
            }//if
            else{
                Toast.makeText(getApplicationContext(), "Data non inserita!", Toast.LENGTH_SHORT).show();
            }//else
        }//if
        else{
            Toast.makeText(getApplicationContext(), "importo non valido!", Toast.LENGTH_SHORT).show();
        }//else
        TextView textView = (TextView) findViewById(R.id.textView);*/
        //textView.setText(readFromFile(filename, getApplicationContext()));
    }//saveValue

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
    public void saveValueToFile (View view){
        EditText importo = (EditText) findViewById(R.id.textImporto);
        EditText data = (EditText) findViewById(R.id.textData);
        EditText desc = (EditText) findViewById(R.id.textType);
        if((importo.getText().toString().equals(""))||(Long.parseLong(importo.getText().toString())>100000000)) {
            if(data.getText().toString().equals("")) {
                String toSave = (importo.getText() + "€   " + data.getText() + "   "
                        + desc.getText() + "\n");
                writeToFile(filename, toSave, getApplicationContext());
                emptyImporto(view);
                emptyData(view);
                emptyDescription(view);
            }//if
            else{
                Toast.makeText(getApplicationContext(), "Data non inserita!", Toast.LENGTH_SHORT).show();
            }//else
        }//if
        else{
            Toast.makeText(getApplicationContext(), "importo non valido!", Toast.LENGTH_SHORT).show();
        }//else
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
        }//catch
        ris +=new String(bytes);
        return ris;
    }//readFromFile

}//MainActivity
