package com.example.listaprisutnosti;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by: Andrea
 * This class provides a user interface for entering an email address and triggers the Firebase authentication mechanism
 * to send a password reset email, enhancing the user experience in the password recovery process
 */

public class ForgotenPasswordView extends AppCompatActivity {

    // Firebase authentication instance
    FirebaseAuth mAuth;
    // Email address for password reset
    String mail; // User email

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout for the activity
        setContentView(R.layout.activity_forgoten_password_view);

        // Set the status bar and navigation bar colors
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.status_bar_color3));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.custom_navigation_bar_color_login_screen2));

        // Initialize Firebase
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        // Find UI elements by their IDs
        Button posalji = findViewById(R.id.posalji);
        TextView unesiMail = findViewById(R.id.unesiMail);

        // Set input type for the email input field
        unesiMail.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

        // Set click listener for the "posalji" (send) button
        posalji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get email from the input field
                mail = unesiMail.getText().toString().trim();
                // Check if the email is not empty
                if (!TextUtils.isEmpty(mail)) {
                    // Call the method to reset the password
                    ResetPassword();
                } else {
                    // Display a toast if the email is empty
                    Toast.makeText(ForgotenPasswordView.this, "Неуспешно", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Method to reset the password using Firebase authentication
    private void ResetPassword() {
        // Send a password reset email
        mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                // Display a toast and navigate to the login page if successful
                Toast.makeText(ForgotenPasswordView.this, "Mejл је послат.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ForgotenPasswordView.this, LoginPage.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Display a toast with an error message if the reset fails
                Toast.makeText(ForgotenPasswordView.this, "Неисправна мејл адреса!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
