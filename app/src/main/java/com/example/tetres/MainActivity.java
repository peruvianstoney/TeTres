package com.example.tetres;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText numero,mensaje;
    private Button enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numero = findViewById(R.id.numero);
        mensaje = findViewById(R.id.mensaje);
        enviar = findViewById(R.id.enviar);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                        enviarSMS();
                    }else{
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
                    }

                }

            }
        });


    }
    private void enviarSMS(){
        String numeroTelf = numero.getText().toString().trim();
        String SMS = mensaje.getText().toString().trim();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(numeroTelf,null,SMS,null,null);
            Toast.makeText(this,"Message enviado", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "No se pudo enviar el mensaje", Toast.LENGTH_SHORT).show();
        }



    }
}