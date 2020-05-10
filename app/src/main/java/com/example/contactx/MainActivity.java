package com.example.contactx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et1;
    private EditText et2;
    private EditText et3;
    private Spinner spinner1;
    private Button btn1;
    private Button btn2;
    int tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText)findViewById(R.id.txt_nombre);
        et2 = (EditText)findViewById(R.id.txt_telefono);
        et3 = (EditText)findViewById(R.id.txt_correo);
        btn1 = (Button)findViewById(R.id.btn_guardar);
        btn2 = (Button)findViewById(R.id.btn_cancelar);
        spinner1 = (Spinner)findViewById(R.id.spinner);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar(v  );
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelar();
            }
        });

        String[] opciones = {"Casa", "Movil", "Trabajo"};
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        spinner1.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (parent.getSelectedItemPosition()) {
                    case 0:
                        tipo = ContactsContract.CommonDataKinds.Phone.TYPE_HOME;
                        break;
                    case 1:
                        tipo = ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE;
                        break;
                    case 2:
                        tipo = ContactsContract.CommonDataKinds.Phone.TYPE_WORK;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void guardar(View v) {
        switch (v.getId()) {
            case R.id.btn_guardar:
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
                intent.putExtra(ContactsContract.Intents.Insert.NAME, et1.getText().toString());
                intent.putExtra(ContactsContract.Intents.Insert.PHONE, et2.getText().toString());
                intent.putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, tipo);
                intent.putExtra(ContactsContract.Intents.Insert.EMAIL, et3.getText().toString());

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Contacto guardado", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_cancelar:
                cancelar();
                break;
        }
    }

    private void cancelar() {
        et1.setText("");
        et2.setText("");
        et3.setText("");
    }


}