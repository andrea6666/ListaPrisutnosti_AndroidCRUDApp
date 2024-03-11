package com.example.listaprisutnosti;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import androidx.appcompat.widget.SearchView;

/**
 * Created by:Andrea
 *
 * This class serves as a crucial component for retrieving, processing, and presenting a list of items
 * from a remote server, offering search functionality and user logout capability.
 */

public class ListItem extends AppCompatActivity {

    // UI elements
    ListView listView;
    ListAdapter adapter;
    ListAdapter adapter2;
    Button logoutBtn;
    private ProgressBar progressBar;
    TextView molimoSacekajte;
    HashMap<String,String> item;
    ArrayList<HashMap<String,String>> list;
    ArrayList<HashMap<String,String>> list2;

    // Strings to store data
    String text;
    String text1 ;
    String text2 ;
    String text3 ;
    String text4 ;
    String text5 ;
    String text6 ;
    String text7 ;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout for the activity
        setContentView(R.layout.list_item);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.lista_header_footer));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.lista_header_footer));

         HashMap<String, String> dataMap = new HashMap<>();

        // Initialize UI elements
         progressBar = findViewById(R.id.custom_progress_bar);
        molimoSacekajte = findViewById(R.id.molimoSacekajte);

        progressBar.setVisibility(View.VISIBLE);
        molimoSacekajte.setVisibility(View.VISIBLE);

        // Find UI elements by their IDs
        listView = (ListView)findViewById(R.id.iv_items);
        logoutBtn = findViewById(R.id.logoutBtn);

        // Set click listener for the logout button
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Sign out the user and navigate to the main activity
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(ListItem.this,MainActivity.class);
                startActivity(intent);

            }
        });


        // Call the method to get items
        getItems();
        // Set up the search functionality
        SearchView searchView = findViewById(R.id.searchlist);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                 list2 = new ArrayList<>();
                list2.clear();

                // Iterate through the list to filter items based on the search query
                for (HashMap<String, String> currentItem : list) {
                    for (String value : currentItem.values()) {
                        if (value.toLowerCase().trim().contains(s.toLowerCase().trim())) {
                            list2.add(currentItem);


                            Log.d("SLOVO_S ", s);

                            Log.d("VALUE", value.toString());

                        }
                    }
                    // Update the adapter with the filtered list
                    adapter2 = new SimpleAdapter(getApplicationContext(),list2,R.layout.list_item_row,
                            new String[]{"text","text1","text2","text3","text4","text5","text6","text7"},
                            new int[]{R.id.text,R.id.text1,R.id.text2,R.id.text3,R.id.text4,R.id.text5,R.id.text6,R.id.text7});
                    listView.setAdapter(adapter2);
                }
                // If the search query is empty, display the original list
                if(s.equals("")){
                    searchView.clearFocus();
                    adapter = new SimpleAdapter(getApplicationContext(),list,R.layout.list_item_row,
                            new String[]{"text","text1","text2","text3","text4","text5","text6","text7"},
                            new int[]{R.id.text,R.id.text1,R.id.text2,R.id.text3,R.id.text4,R.id.text5,R.id.text6,R.id.text7});

                    listView.setAdapter(adapter);

                }

                return true;
            }
        });

        }

    // Method to fetch items from the server
    private void getItems() {

       // loading = ProgressDialog.show(this,"Учитава се...","молимо сачекајте",false,true);


        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "https://script.google.com/macros/s/AKfycbw8N-V4vbXuVW3uYe_11HpW7tuhWnatvPSxzxY8RYdVnVtzP1jUaiFXG7ToU-NEeiqq/exec?action=getItems",
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseItems(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        // Set timeout and retry policy for the request
        int socketTimeOut = 5000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut,0,0);
        stringRequest.setRetryPolicy(policy);

        // Add the request to the queue
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

    }
    // Method to parse the JSON response and populate the list
    private void parseItems(String jsonResponse){

        list = new ArrayList<>();

        try{
            JSONObject jobj = new JSONObject(jsonResponse);
            JSONArray jarray = jobj.getJSONArray("items");
            // Iterate through the JSON array and extract data
            for (int i=0;i<jarray.length();i++){
                JSONObject jo =  jarray.getJSONObject(i);

                // Get data from JSON
                 text = jo.getString("Rb.");
                 text1 = jo.getString("Ime");
                 text2 = jo.getString("Prezime");
                 text3 = jo.getString("Opstina/Grad");
                 text4 = jo.getString("Radno mesto");
                 text5 = jo.getString("Broj telefona");
                 text6 = jo.getString("Mejl adresa");
                 text7 = jo.getString("Datum");

                // Create a HashMap to store the data
                 item = new HashMap<>();
                item.put("text","Рб.: "+text+".");
                item.put("text1","Име: "+text1);
                item.put("text2","Презиме: "+text2);
                item.put("text3","Општина/Град: "+text3);
                item.put("text4","Радно место: "+text4);
                item.put("text5","Број телефона: "+text5);
                item.put("text6","Мејл адреса: "+text6);
                item.put("text7","Датум: "+text7.substring(0,10)+"  Време: "+text7.substring(11,19));

                // Add the item to the list
                list.add(item);

            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        // Create and set the adapter for the list view
        adapter = new SimpleAdapter(this,list,R.layout.list_item_row,
                new String[]{"text","text1","text2","text3","text4","text5","text6","text7"},
                new int[]{R.id.text,R.id.text1,R.id.text2,R.id.text3,R.id.text4,R.id.text5,R.id.text6,R.id.text7});

        listView.setAdapter(adapter);


     //   loading.dismiss();
        // Hide the progress bar and loading message
        progressBar.setVisibility(View.INVISIBLE);
        molimoSacekajte.setVisibility(View.INVISIBLE);

    }


}
