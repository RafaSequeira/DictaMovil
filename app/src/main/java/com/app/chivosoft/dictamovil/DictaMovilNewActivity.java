package com.app.chivosoft.dictamovil;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.regex.Pattern;

public class DictaMovilNewActivity extends AppCompatActivity {

    private TextInputLayout tilNombre;
    private TextInputLayout tilCedula;
    private TextInputLayout tilDomicilio;
    private TextInputLayout tilPresion;
    private TextInputLayout tilPeso;
    private TextInputLayout tilAltura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dicta_movil_new);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new_dictamen);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Referencia a los TIL´s
        tilNombre = (TextInputLayout) findViewById(R.id.til_nombre);
        tilCedula = (TextInputLayout) findViewById(R.id.til_cedula);
        tilDomicilio = (TextInputLayout) findViewById(R.id.til_domicilio);
        tilPresion = (TextInputLayout) findViewById(R.id.til_presión);
        tilPeso = (TextInputLayout) findViewById(R.id.til_peso);
        tilAltura = (TextInputLayout) findViewById(R.id.til_altura);

        // Referencia al boton de guardar

        Button botonAceptar = (Button) findViewById(R.id.boton_aceptar);
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarDatos();
            }
        });
    }

    private boolean esNombreValido(String nombre) {
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        if (!patron.matcher(nombre).matches() || nombre.length() > 30) {
            tilNombre.setError("Nombre inválido");
            return false;
        } else {
            tilNombre.setError(null);
        }

        return true;
    }

    private  boolean esCedulaValida(String cedula){
        if(cedula==" "){
            tilCedula.setError("Campo vacio");
            return false;
        }
        else {
            tilCedula.setError(null);
        }
        return true;
    }
    private  boolean esDomicilioValido(String domicilio){
        if(domicilio==" "){
            tilDomicilio.setError("Campo vacio");
            return false;
        }
        else {
            tilDomicilio.setError(null);
        }
        return true;
    }

    private  boolean esPesoValido(String peso){
        if(peso==" " && Patterns.PHONE.matcher(peso).matches()){
            tilPeso.setError("Campo vacio o digito valor incorrecto");
            return false;
        }
        else {
            tilPeso.setError(null);
        }
        return true;
    }

    private  boolean esPresionValido(String presion){
        if(presion==" "){
            tilPresion.setError("Campo vacio");
            return false;
        }
        else {
            tilPresion.setError(null);
        }
        return true;
    }

    private  boolean esAlturaValido(String altura){
        if(altura==" " && Patterns.PHONE.matcher(altura).matches()){
            tilAltura.setError("Campo vacio o digito valor incorrecto");
            return false;
        }
        else {
            tilAltura.setError(null);
        }
        return true;
    }

    private void validarDatos() {
        String nombre = tilNombre.getEditText().getText().toString();
        String cedula = tilCedula.getEditText().getText().toString();
        String domicilio = tilDomicilio.getEditText().getText().toString();
        String presion = tilPresion.getEditText().getText().toString();
        String peso = tilPeso.getEditText().getText().toString();
        String altura = tilAltura.getEditText().getText().toString();

        boolean a = esNombreValido(nombre);
        boolean b = esCedulaValida(cedula);
        boolean c = esDomicilioValido(domicilio);
        boolean d = esPresionValido(presion);
        boolean e = esPesoValido(peso);
        boolean f = esAlturaValido(altura);

        if (a && b && c && d && e && f) {

            Toast.makeText(this, "Se guarda el registro", Toast.LENGTH_LONG).show();
        }

    }


    public void guardarDictamen(){

    }

}
