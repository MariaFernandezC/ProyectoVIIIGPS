package com.example.proyectoviiigps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        final EditText nombret              = findViewById(R.id.RegistroNombre);
        final EditText cedulat              = findViewById(R.id.RegistroCedula);
        final EditText edadt                = findViewById(R.id.RegistroEdad);
        final EditText direcciont           = findViewById(R.id.RegistroDireccion);
        final EditText telefonot            = findViewById(R.id.RegistroTelefono);
        final EditText correoelectronicot   = findViewById(R.id.RegistroCorreo);
        final EditText usuariot             = findViewById(R.id.RegistroUsuario);
        final EditText clavet               = findViewById(R.id.RegistroContrasena);
        Button btnRegistro                  = findViewById(R.id.btnRegistro);

        btnRegistro.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                String nombre       = nombret.getText().toString();
                int cedula          = Integer.parseInt( cedulat.getText().toString());
                int    edad         =  Integer.parseInt( edadt.getText().toString());
                String direccion    = direcciont.getText().toString();
                int telefono        = Integer.parseInt(telefonot.getText().toString());
                String correo       = correoelectronicot.getText().toString();
                String usuario      = usuariot.getText().toString();
                String clave        = clavet.getText().toString();

                Response.Listener <String> respuesta=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonRespuesta =new JSONObject(response);
                            boolean ok = jsonRespuesta.getBoolean("success");

                            if ( ok){
                                Intent i = new Intent(Registro.this, MainActivity.class);
                                Registro.this.startActivity(i);
                                Registro.this.finish();
                            }else {
                                AlertDialog.Builder alerta =new AlertDialog.Builder( Registro.this);
                                alerta.setMessage("Fallo en el Registro")
                                        .setNegativeButton( "Reintertar", null)
                                        .create()
                                        .show();
                            }
                        }catch (JSONException e){
                            e.getMessage();
                        }
                    }
                };
                RegistroRequest r = new RegistroRequest(nombre,cedula,edad,direccion,telefono,correo,usuario,clave, respuesta);
                RequestQueue cola = Volley.newRequestQueue( Registro.this);
                cola.add(r);
            }
        });
    }
}
