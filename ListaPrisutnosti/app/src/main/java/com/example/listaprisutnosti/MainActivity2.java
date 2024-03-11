package com.example.listaprisutnosti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.os.Handler;
import android.widget.Toast;
import java.util.Arrays;

/**
 * Created by: Andrea
 *
 *This class acts as an intermediary step where the user can review the entered data, confirm,
 * and proceed with additional actions like saving the data to Google Sheets or correcting the input
 */

public class MainActivity2 extends AppCompatActivity {


    private TextView rezultat1;
    private TextView rezultat2;
    private TextView rezultat6;
    private TextView rezultat3;
    private TextView rezultat4;
    private TextView rezultat5;
    private Button potvrdi;
    private Button ispravi;


    // Variables to store input data
    String inputIme;
    String inputPrezime ;
    String inputOpstinaGrad ;
    String inputRadnoMesto;
    String inputBrojTelefona ;
    String inputMejlAdresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout for the activity
        setContentView(R.layout.activity_main2);
        // Set status bar and navigation bar colors
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.status_bar_color2));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.custom_navigation_bar_color_login_screen2));

        // Find UI elements by their IDs
        potvrdi = findViewById(R.id.potvrdi);
        ispravi = findViewById(R.id.ispravi);

        // Disable certain UI elements
        findViewById(R.id.rezultat1).setEnabled(false);
        findViewById(R.id.rezultat2).setEnabled(false);
        findViewById(R.id.rezultat3).setEnabled(false);
        findViewById(R.id.rezultat4).setEnabled(false);
        findViewById(R.id.rezultat5).setEnabled(false);
        findViewById(R.id.rezultat6).setEnabled(false);
        findViewById(R.id.pitanje).setEnabled(false);

        // Set click listener for the 'potvrdi' Button
        potvrdi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Check if all required fields are filled
                if(!inputMejlAdresa.equals("") && !inputIme.equals("")&& !inputPrezime.equals("") && !inputOpstinaGrad.equals("") && !inputRadnoMesto.equals("") && !substringPhoneNumber(inputBrojTelefona).equals("")) {

                    // Validate email and phone number
                    boolean validateEmail = validateEmailAdress(inputMejlAdresa);
                    if(validateEmail==true && substringPhoneNumber(inputBrojTelefona).length()>8) {

                       saveData(inputIme, inputPrezime, inputOpstinaGrad, inputRadnoMesto, substringPhoneNumber(inputBrojTelefona), inputMejlAdresa);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                performSecondAction();

                            }
                        }, 3000); // 2 seconds delay (2000 milliseconds)
                    }

                } else{
                    Toast.makeText(MainActivity2.this, "Попуните сва поља!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set click listener for the 'ispravi' Button to finish the activity
        ispravi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Find TextViews by their IDs
        rezultat1 = findViewById(R.id.rezultat1);
        rezultat2 = findViewById(R.id.rezultat2);
        rezultat3 = findViewById(R.id.rezultat3);
        rezultat4 = findViewById(R.id.rezultat4);
        rezultat5 = findViewById(R.id.rezultat5);
        rezultat6 = findViewById(R.id.rezultat6);

        // Retrieve data from the intent
        Intent intent = getIntent();
         inputIme = capitalizeFirstLetter(convertLatinToCyrillic(intent.getStringExtra("ime")));
         inputPrezime = capitalizeFirstLetter(convertLatinToCyrillic(intent.getStringExtra("prezime")));
         inputOpstinaGrad = capitalizeFirstLetter(convertLatinToCyrillic(intent.getStringExtra("opstinaGrad")));
         inputRadnoMesto = convertLatinToCyrillic(intent.getStringExtra("radnoMesto"));
         inputBrojTelefona = intent.getStringExtra("brojTelefona");
         inputMejlAdresa = intent.getStringExtra("mejlAdresa");

        // Set TextViews with formatted data
            rezultat1.setText("Име: "+inputIme );
            rezultat2.setText("Презиме: "+inputPrezime);
            rezultat3.setText("Општина/Град: "+inputOpstinaGrad);
            rezultat4.setText("Радно место: "+inputRadnoMesto);
            rezultat5.setText("Број телефона: "+inputBrojTelefona);
            rezultat6.setText("Мејл: "+inputMejlAdresa);
    }

    // Method to perform the second action
    private void performSecondAction() {
        Intent intent = new Intent(MainActivity2.this,MainActivity.class);
        startActivity(intent);
    }

    // Method to save data to Google Sheets
    private void saveData(String jedan, String dva, String tri,String cetiri, String pet, String sest){
        String url = "https://script.google.com/macros/s/AKfycbxADZV436FMx5p9KJP5-3NWVYSM9n5opUP3bCa4ExJ8x454y0v0ZZelyQ6xmjhbKayX/exec";

        url = url+"?action=create2&jedan="+jedan+"&dva="+dva+"&tri="+tri+"&cetiri="+cetiri+"&pet="+pet+"&sest="+sest;

        // Define a StringRequest to handle the HTTP request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity2.this,response,Toast.LENGTH_SHORT).show();
            }
    }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity2.this,error.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });

        // Add the request to the RequestQueue
        RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(stringRequest);

        }


    // Method to validate email address
    private boolean validateEmailAdress(String email){
        String emaiilInput = email;

        if(!emaiilInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emaiilInput).matches()){
          //  Toast.makeText(this,"Исправна мејл адреса!",Toast.LENGTH_SHORT).show();
            return true;
        } else{
            Toast.makeText(this, "Неисправна мејл адреса!", Toast.LENGTH_SHORT).show();
            return false;
        }
        }

    // Method to format and validate phone number
    private String substringPhoneNumber(String phoneNumber){
            String newPhoneNmumber = "";


            if(phoneNumber.length()<9){
                Toast.makeText(this,"Неисправан број телефона!", Toast.LENGTH_SHORT).show();
                newPhoneNmumber = "";
            }else{
                // Format the phone number
                newPhoneNmumber += phoneNumber.substring(0, 3) + " " + phoneNumber.substring(3, phoneNumber.length());
            }



        return newPhoneNmumber;
        }

    // Method to capitalize the first letter of a string
    public static String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input; // Return the input string if it's null or empty
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }



    // Method to convert Latin characters to Cyrillic
    public static String convertLatinToCyrillic(String input) {
            // Define a mapping of Latin characters to Cyrillic equivalents
            String[] latinChars =    {"A","a","B","b","V","v","G","g","D","d","E","e","Ž","ž","Z","z","I","i","K","k","L","l","M","m","N","n","O","o",
                    "P","p","R","r","S","s","T","t","U","u","F","f","H","h","C","c","Č","č","Š","š","J","j","Lj","lj","Nj","nj","Ć","ć","Đ","đ","Dž","dž"};
            String[] cyrillicChars = {"А","а","Б","б","В","в","Г","г","Д","д","Е","е","Ж","ж","З","з","И","и","К","к","Л","л","М","м","Н","н","О","о",
                    "П","п","Р","р","С","с","Т","т","У","у","Ф","ф","Х","х","Ц","ц","Ч","ч","Ш","ш","Ј","ј","Љ","љ","Њ","њ","Ћ","ћ","Ђ","ђ","Џ","џ"};

            StringBuilder output = new StringBuilder(input);

            // Iterate through the input string and replace Latin characters with Cyrillic equivalents
            for (int i = 0; i < output.length(); i++) {
                char c = output.charAt(i);
                String charAsString = String.valueOf(c);

                // Check if the character is in the LatinChars array
                int index = Arrays.asList(latinChars).indexOf(charAsString);
                if (index != -1) {
                    // Replace the character with its Cyrillic equivalent
                    char cyrillicChar = cyrillicChars[index].charAt(0);
                    output.setCharAt(i, cyrillicChar);
                }
            }

            return output.toString();
        }


    }