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

import de.hdodenhof.circleimageview.CircleImageView;

public class TwitterActivity extends AppCompatActivity {

    private CircleImageView profile;
    private TextView name;
    private TextView email;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);

        profile = findViewById(R.id.profileImage);
        name = findViewById(R.id.profileName);
        email = findViewById(R.id.profileEmail);

        String username = getIntent().getStringExtra("username");
        String tvemail = getIntent().getStringExtra("email");
        String profileurl = getIntent().getStringExtra("picture");
        btnLogout = findViewById(R.id.btnLogout);

        name.setText(username);
        email.setText(tvemail);

        Glide.with(this).load(profileurl).into(profile);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

    }
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        startActivity(new Intent(TwitterActivity.this,MainActivity.class));
        finish();

    }
}
