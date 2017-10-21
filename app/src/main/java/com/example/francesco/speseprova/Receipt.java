package com.example.francesco.speseprova;

import android.graphics.Bitmap;

import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by Francesco on 20/10/2017.
 */

public class Receipt {
    private int id;
    private double value;
    private String date; //SQLite non supporta il formato Date
    private String description;
    //private Bitmap foto;
    public Receipt(){    }//DB
    public Receipt(int id, double importo, String data, String desc ){
        this.id=id;
        this.value=importo;
        this.date=data;
        this.description=desc;
    }//DB
    public Receipt(double importo, String data, String desc ){
        this.value=importo;
        this.date=data;
        this.description=desc;
    }//DB
    /**Metodo accessorio della classe scontrini
     * @return l'id
     */
    public int getID(){ return id;};

    /**Metodo accessorio della classe scontrini
     * @return l'importo
     */
    public double getValue(){ return value;};

    /**Metodo accessorio della classe scontrini
     * @return òa data
     */
    public String getDate(){ return date;};

    /**Metodo accessorio della classe scontrini
     * @return la descrizione
     */
    public String getDesc(){ return description;};

    /** Metodo Acessorio della classe Receipt
     * @param id il nuovo identificatore
     */
    public void setId (int id){
        this.id=id;
    }//setID

    /** Metodo Acessorio della classe Receipt
     * @param importo il nuovo importo
     */
    public void setImport (double importo){
        this.value=importo;
    }//setImport

    /** Metodo Acessorio della classe Receipt
     * @param data la nuova data
     */
    public void setDate (String data){
        this.date=data;
    }//SetDate

    /** Metodo Acessorio della classe Receipt
     * @param descrizione la nuova descrizione
     */
    public void setDesc (String descrizione){
        this.description=descrizione;
    }//detDesc



}//db
