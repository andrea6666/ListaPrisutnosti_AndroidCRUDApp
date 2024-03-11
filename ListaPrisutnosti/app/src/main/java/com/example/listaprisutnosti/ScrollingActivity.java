package com.example.listaprisutnosti;

import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by: Andrea
 *
 * In the provided class,is the actual process of populating the ListView with the extracted data
 * is not implemented. You would typically extend the code within the onResponse method to update
 * the arrNames ArrayList with the data from the JSON response and then notify the adapter of the data set changes
 */

public class ScrollingActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String>arrNames = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        // Initialize ListView
        listView = findViewById(R.id.listView);

        // Add sample data to the ArrayList (can be replaced with actual data)
        arrNames.add("sest" + "\n"+ "\nsledeci element");  // + "\n"+
        arrNames.add("sest1");
        arrNames.add("sest2");
        arrNames.add("sest3");
        arrNames.add("sest4");
        arrNames.add("sest5");

        // Create ArrayAdapter and set it to the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,arrNames);
        listView.setAdapter(adapter);

        // Read data from Google Apps Script into the ListView
        readDataIntoView();

    }

    // Method to read data from Google Apps Script and populate the ListView
    public void readDataIntoView(){
    String url = "https://script.google.com/macros/s/AKfycbxKgv5EjHtwQK-IUjvstjg7V98PDP6z2M4Dm8qhsRxjKRWSK4ljKq0J4O2pbKoWWHxL/exec";

    // Initialize a request queue
    RequestQueue queue = Volley.newRequestQueue(this);

    // Create a request for the Google Apps Script
    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    // Parse the JSON response and populate your ListView
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        // Iterate through the JSON array and extract data
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String column1Data = jsonObject.getString("column1");
                            String column2Data = jsonObject.getString("column2");
                            // Extract more columns as needed

                            // Populate your ListView with the extracted data
                            // Here you can use an ArrayAdapter or a custom adapter to bind data to your ListView
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // Handle errors, such as network issues or incorrect URL
                }
            });

// Add the request to the request queue
queue.add(stringRequest);

    }
}