package com.example.listaprisutnosti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import android.graphics.Color;

/**
 * Created by: Andrea
 *
 * This class is a crucial part of the application's user authentication flow,
 * integrating Firebase Authentication for secure login processes and providing a user-friendly interface
 * for handling login attempts.
 */
public class LoginPage extends AppCompatActivity {
    private FirebaseAuth mAuth;
    String usernamestr;
    String passwordstr;
    TextView username;
    TextView password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout for the activity
        setContentView(R.layout.activity_login_page);
        // Set status bar and navigation bar colors
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.status_bar_color3));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.custom_navigation_bar_color_login_screen2));

        // Find UI elements by their IDs
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);



        //  username.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        username.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);


        TextView forgotenpassword = findViewById(R.id.forgotpass);
        Button loginButton = findViewById(R.id.loginbtn);

        // Set click listener for the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get the entered username and password
                usernamestr = username.getText().toString().trim();
                passwordstr = password.getText().toString().trim();
                // Initialize Firebase Auth
                mAuth = FirebaseAuth.getInstance();

                // Check if username or password is empty
                if (TextUtils.isEmpty(usernamestr)) {
                    Toast.makeText(LoginPage.this, "Унеси мејл!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(passwordstr)) {
                    Toast.makeText(LoginPage.this, "Унеси шифру!", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Sign in with Firebase Auth
                mAuth.signInWithEmailAndPassword(usernamestr, passwordstr)
                        .addOnCompleteListener(LoginPage.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginPage.this, "Успешно!", Toast.LENGTH_SHORT).show();

                                    // If login is successful, navigate to ListItem activity
                                    Intent intent = new Intent(LoginPage.this, ListItem.class);
                                    startActivity(intent);
                                    username.setText("");
                                    password.setText("");
                                } else {
                                    // Display error message if login fails
                                    Toast.makeText(LoginPage.this, "Погршан мејл или шифра!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }


        });
        // Set touch listener for the "Forgotten Password?" TextView
        forgotenpassword.setOnTouchListener(new View.OnTouchListener() {


            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        forgotenpassword.setTextColor(Color.WHITE);
                        break;
                    case MotionEvent.ACTION_UP:
                        forgotenpassword.setTextColor(getResources().getColor(R.color.blue2));
                        break;
                }
                return false; // Returning false allows the button to continue handling click events.
            }
        });
        // Set click listener for the "Forgotten Password?" TextView
        forgotenpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event to navigate to ForgottenPasswordView activity
                Intent intent = new Intent(LoginPage.this, ForgotenPasswordView.class);
                startActivity(intent);
            }
        });

    }
}







