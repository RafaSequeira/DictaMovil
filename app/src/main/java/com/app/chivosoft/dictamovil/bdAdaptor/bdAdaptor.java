package com.app.chivosoft.dictamovil.bdAdaptor;

/**
 * Created by Rafa on 11/6/2017.
 */

import java.sql.SQLException;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class bdAdaptor extends  SQLiteOpenHelper{

    public static final String KEY_IDFILA="id";
    public static final String KEY_NOMBRE="nombre";
    public static final String KEY_CEDULA="cedula";
    public static final String KEY_DOMOCILIO="domicilio";
    public static final String KEY_PRESIÖN_ARTERIAL="presión";
    public static final String KEY_PESO="peso";
    public static final String KEY_ALTURA="altura";


    private static final String TAG="bdAdaptor";
    private static final String NOMBRE_BASEDATOS="DictaMovilDB";
    private static final String TABLA_BASEDATOS="registroDictamen";
    private static final int VERSION_BASEDATOS=1;
    private static final String CREAR_BASEDATOS="create table registroDictamen (_id integer primary key autoincrement, nombre text, emai text);";

    private SQLiteDatabase db;

    public bdAdaptor(Context context) {
        super(context, NOMBRE_BASEDATOS,null,VERSION_BASEDATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_BASEDATOS);
        System.out.println ("creating db");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "ACTUALIZANDO BASE DE DATOS VERSION"+oldVersion+" A "+ newVersion + ", LO QUE DESTRUIRÁ TODOS LOS DATOS " +
                "ANTERIORES");
                db.execSQL("DROP TABLE IF EXISTS contactos");
        onCreate(db);

    }

    public bdAdaptor open()
    {
        db=this.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }

    public long insertarDiactamen(String nombre, String cedula, String domicilio, String presionArterial, String peso, String altura)
    {
        ContentValues valoresIniciales=new ContentValues();
        valoresIniciales.put(KEY_NOMBRE, nombre);
        valoresIniciales.put(KEY_CEDULA, cedula);
        valoresIniciales.put(KEY_DOMOCILIO, domicilio);
        valoresIniciales.put(KEY_PRESIÖN_ARTERIAL, presionArterial);
        valoresIniciales.put(KEY_PESO, peso);
        valoresIniciales.put(KEY_ALTURA, altura);
        return db.insert(TABLA_BASEDATOS, null, valoresIniciales);
    }

    /* public boolean borrarContacto(long idFila)
    {
        return db.delete(TABLA_BASEDATOS, KEY_IDFILA + "=" + idFila, null)>0;
    } */

    public Cursor obtenerTodosLosContactos()
    {
        return db.query(TABLA_BASEDATOS, new String[] {KEY_IDFILA, KEY_NOMBRE,KEY_CEDULA,KEY_DOMOCILIO, KEY_PRESIÖN_ARTERIAL, KEY_PESO, KEY_ALTURA}, null, null, null,null,null);
    }
    public Cursor obtenerContacto(long idFila) throws SQLiteException
    {
        Cursor mCursor = db.query(true, TABLA_BASEDATOS, new String[]{
                        KEY_IDFILA, KEY_NOMBRE, KEY_CEDULA, KEY_DOMOCILIO, KEY_PRESIÖN_ARTERIAL, KEY_PESO, KEY_ALTURA }, KEY_IDFILA + "=" + idFila,
                null, null, null, null, null);
        if (mCursor != null)
        {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
}


