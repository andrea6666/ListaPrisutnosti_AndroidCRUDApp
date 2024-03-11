package com.example.listaprisutnosti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by: Andrea
 *
 *  This class facilitates the collection of user input related to personal information
 *  and initiates the transition to another activity (MainActivity2) to further process or display the entered data.
 *  Additionally, it provides an option to navigate to the login page (LoginPage) through the admin button
 */

public class MainActivity extends AppCompatActivity {

    private EditText ime;
    private EditText prezime;
    private EditText opstinaGrad;
    private EditText radnoMesto;
    private EditText brojTelefona;
    private EditText mejlAdresa;
    private Button dugmeSacuvaj;
    private ImageButton admin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout for the activity
        setContentView(R.layout.activity_main);
        // Set status bar and navigation bar colors
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.status_bar_color2));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.custom_navigation_bar_color_login_screen2));

        // Find UI elements by their IDs
        ime = findViewById(R.id.ime);
        prezime = findViewById(R.id.prezime);
        opstinaGrad = findViewById(R.id.opstinaGrad);
        radnoMesto = findViewById(R.id.radnoMesto);
        brojTelefona = findViewById(R.id.brojTelefona);
        mejlAdresa = findViewById(R.id.mejlAdresa);
        dugmeSacuvaj = findViewById(R.id.dugmeSacuvaj);
        admin = findViewById(R.id.admin);

        //fields setEnabled to false
        findViewById(R.id.protektisNaziv).setEnabled(false);
        findViewById(R.id.listaPrisutnosti).setEnabled(false);

        // Set 'DigitsKeyListener' to restrict input for 'brojTelefona' to numeric digits only
        brojTelefona.setKeyListener(DigitsKeyListener.getInstance("0123456789"));

        // Set click listener for the 'admin' ImageButton to navigate to LoginPage
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginPage.class);
                startActivity(intent);
            }
        });

        // Set click listener for the 'dugmeSacuvaj' Button
        dugmeSacuvaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Implement the logic to display text from inputEditText to outputEditText
                String inputIme = ime.getText().toString();
                String inputPrezime = prezime.getText().toString();
                String inputOpstinaGrad = opstinaGrad.getText().toString();
                String inputRadnoMesto = radnoMesto.getText().toString();
                String inputBrojTelefona = brojTelefona.getText().toString();
                String inputMejlAdresa = mejlAdresa.getText().toString();

                // Log the retrieved data (for debugging purposes)
                Log.d("TEST ",inputIme);
                Log.d("TEST ",inputPrezime);
                Log.d("TEST ",inputOpstinaGrad);
                Log.d("TEST ",inputRadnoMesto);
                Log.d("TEST ",inputBrojTelefona);
                Log.d("TEST ",inputMejlAdresa);

                // Create an Intent to pass data to the next activity
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);

                intent.putExtra("ime", inputIme);
                intent.putExtra("prezime", inputPrezime);
                intent.putExtra("opstinaGrad", inputOpstinaGrad);
                intent.putExtra("radnoMesto", inputRadnoMesto);
                intent.putExtra("brojTelefona", inputBrojTelefona);
                intent.putExtra("mejlAdresa", inputMejlAdresa);

                // Start the next activity
                startActivity(intent);

            }
        });

    }
}