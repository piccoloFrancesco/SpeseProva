package com.example.francesco.speseprova;

import android.graphics.Bitmap;

import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by Francesco on 20/10/2017.
 */

public class Receipt {
    private int id;
    private double importo;
    private String data; //SQLite non supporta il formato Date
    private String descrizione;
    //private Bitmap foto;
    public Receipt(){    }//DB
    public Receipt(int id, double importo, String data, String desc ){
        this.id=id;
        this.importo=importo;
        this.data=data;
        this.descrizione=desc;
    }//DB

    /**Metodo accessorio della classe scontrini
     * @return l'id
     */
    public int getID(){ return id;};

    /**Metodo accessorio della classe scontrini
     * @return l'importo
     */
    public double getImporto(){ return importo;};

    /**Metodo accessorio della classe scontrini
     * @return òa data
     */
    public String getDate(){ return data;};

    /**Metodo accessorio della classe scontrini
     * @return la descrizione
     */
    public String getDesc(){ return descrizione;};

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
        this.importo=importo;
    }//setImport

    /** Metodo Acessorio della classe Receipt
     * @param data la nuova data
     */
    public void setDate (String data){
        this.data=data;
    }//SetDate

    /** Metodo Acessorio della classe Receipt
     * @param descrizione la nuova descrizione
     */
    public void setDesc (String descrizione){
        this.descrizione=descrizione;
    }//detDesc



}//db
