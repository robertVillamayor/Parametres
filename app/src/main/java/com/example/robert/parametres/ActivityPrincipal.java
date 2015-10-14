package com.example.robert.parametres;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityPrincipal extends Activity {

    Button boton;
    EditText cajaTexto1;
    RadioGroup radioGrupp,radioGrupp1;
    RadioButton r1,r2,r3,r4;
    TextView mesajeEdad;
    RatingBar estrellas;
    SeekBar punt;
    String valorNombre, genero, edad, conductor;
    Integer estrellass,puntuacion;

    public static final int ACTIVITY_EDAD=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_principal);

        captarCampos();
    }


    public boolean datosCorrectos() {
        if (cajaTexto1.getText().length() <= 0) {
            return false;
        } else {
            if (radioGrupp.getCheckedRadioButtonId() == -1) {
                return false;
            }else{
                if(radioGrupp1.getCheckedRadioButtonId() == -1){
                    return false;
                }
                else{
                    return true;
                }
            }

        }

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK&&requestCode==ACTIVITY_EDAD){
            if(data.hasExtra("edadIntent")) {
                mesajeEdad = (TextView) findViewById(R.id.lblMensajeEddad);
                mesajeEdad.setText(data.getExtras().getString("edadIntent"));
            }
        }else{
            Toast.makeText(getApplicationContext(), "Error en la aplicacion", Toast.LENGTH_SHORT).show();
        }
    }


    public void captarCampos(){
        boton = (Button) findViewById(R.id.btnEnviar);
        cajaTexto1 = (EditText) findViewById(R.id.cjtxNombre);
        radioGrupp = (RadioGroup) findViewById(R.id.radioGrup);
        radioGrupp1 = (RadioGroup) findViewById(R.id.radioGrup1);
        estrellas = (RatingBar) findViewById(R.id.ratingBar);
        punt = (SeekBar) findViewById(R.id.seekBar);
        r1 = (RadioButton) findViewById(R.id.rBtnMasc);
        r2 = (RadioButton) findViewById(R.id.rBtnFem);
        r3 = (RadioButton) findViewById(R.id.rBtnSi);
        r4 = (RadioButton) findViewById(R.id.rBtnNo);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datosCorrectos() == true) {
                    valorNombre = cajaTexto1.getText().toString();
                    if(r1.isChecked()==true) genero="sr.";
                    if(r2.isChecked()==true) genero="sra.";
                    if(r3.isChecked()==true) conductor="es conductor";
                    if(r4.isChecked()==true) conductor="no tiene carnet";
                    estrellass=estrellas.getProgress();
                    puntuacion=punt.getProgress();
                    Intent i = new Intent(getApplicationContext(), ActivityEdad.class);
                    i.putExtra("nombreIntent", valorNombre);
                    i.putExtra("generoIntent", genero);
                    i.putExtra("conductorIntent", conductor);
                    i.putExtra("estrellasIntent", estrellass);
                    i.putExtra("puntuacionIntent", puntuacion);
                    startActivityForResult(i, ACTIVITY_EDAD);
                } else {
                    Toast.makeText(getApplicationContext(), "Revisa los datos, llenalo TODO", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
