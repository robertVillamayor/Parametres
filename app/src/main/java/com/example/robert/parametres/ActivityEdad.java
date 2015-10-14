package com.example.robert.parametres;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityEdad extends Activity {

    TextView indicativo,indicativo1,indicativo2;
    EditText edadEditText;
    Button boton2;
    int edadUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_edad);
        Bundle b=getIntent().getExtras();
        if(b!=null) {
            String nombreUsuario = b.getString("nombreIntent");
            String genero = b.getString("generoIntent");
            String conductor = b.getString("conductorIntent");
            Integer estrella = b.getInt("estrellasIntent");
            estrella=estrella/2;
            Integer puntuacion = b.getInt("puntuacionIntent");
            indicativo = (TextView)findViewById(R.id.lblMensaje);
            indicativo1 = (TextView)findViewById(R.id.lblMensaje1);
            indicativo2 = (TextView)findViewById(R.id.lblMensaje2);
            indicativo.setText(genero+" "+nombreUsuario+", usted "+conductor+".");
            indicativo1.setText("Usted ha valorado la practica con "+estrella+" estrellas .");
            indicativo2.setText("Usted ha calificado la practica de 0 a 100 con "+puntuacion+" puntos .");

            continuar();
        }

    }

    public void finish() {
        edadEditText= (EditText)findViewById(R.id.cjtxEdad);
        edadUsuario =Integer.parseInt(edadEditText.getText().toString());
        Intent data = new Intent();
        if(edadUsuario<18) {
            data.putExtra("edadIntent", "Tienes " + edadUsuario + " años, ATENCIÓN CONTROL PARENTAL, NO HAY NADA QUE VER AQUÍ ");
        }else {
            if (edadUsuario >= 18 && edadUsuario < 25) {
                data.putExtra("edadIntent", "Tienes " + edadUsuario + " años, ya eres mayor de edad.");
            } else {
                if (edadUsuario >= 25 || edadUsuario <= 35) {
                    data.putExtra("edadIntent", "Tienes " + edadUsuario + " años, ya estás en la flor de la vida.");
                } else {
                    if (edadUsuario > 35) {
                        data.putExtra("edadIntent", "Tienes " + edadUsuario + " años, AY AY AY.");
                    }
                }
            }
        }

        setResult(RESULT_OK, data);
        super.finish();
    }

    public boolean datosCorrectos(){
        if(edadEditText.getText().length()<=0){
            return false;
        }else{
            return true;
        }
    }

    public void continuar(){
        boton2 = (Button) findViewById(R.id.btnContinuar);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // if(datosCorrectos()==true){
                    finish();
                //}else{
                    //Toast.makeText(getApplicationContext(), "Revisa los datos, llenalo TODO", Toast.LENGTH_SHORT).show();
               // }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_edad, menu);
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
