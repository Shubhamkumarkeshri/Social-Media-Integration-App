package com.tech.appsocialmedia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;


public class FacebookActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String name;
    private String email;
    private String photo_url;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);

        mAuth = FirebaseAuth.getInstance();
        CircleImageView profileDP = findViewById(R.id.profileImage);
        TextView profileName = findViewById(R.id.profileName);
        TextView profileEmail = findViewById(R.id.profileEmail);
        btnLogout = findViewById(R.id.btnLogout);

        FirebaseUser currentUser = mAuth.getCurrentUser();

        name = currentUser.getDisplayName();
        email = currentUser.getEmail();
        photo_url = currentUser.getPhotoUrl().toString();

        Glide.with(this).load(photo_url).into(profileDP);
        profileName.setText(name);
        profileEmail.setText(email);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null)
            logout();

    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        startActivity(new Intent(FacebookActivity.this,MainActivity.class));
        finish();

    }
}
