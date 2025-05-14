package com.example.goverment_system;



import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();


        setContentView(R.layout.logoshowpage);

        TextView INVISIBLETV =findViewById(R.id.tvNV);
        Button LOGINV=findViewById(R.id.loginNV);
        Button signINV=findViewById(R.id.signNV);
        INVISIBLETV.setVisibility(View.INVISIBLE);
        LOGINV.setVisibility(View.INVISIBLE);
        signINV.setVisibility(View.INVISIBLE);

//-------------------------go to anim folder -> bottom_to_top.xml
        /*
        ----Animation bottom_to_top = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top);----
        Loads an animation XML file called bottom_to_top.xml from the res/anim/ folder.
         this is the current context (usually an Activity).

       ----  logo.startAnimation(bottom_to_top);-------
         Starts the bottom_to_top animation on the logo ImageView.
        */

        ImageView logo = findViewById(R.id.logo);
        Animation bottom_to_top = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top);
        logo.startAnimation(bottom_to_top);

        //-------------------------show activity_main page after logoshowpage ---------------------------------------
        /*
        A Handler allows you to schedule tasks to run  run after 2.5 seconds.

        () -> { ... }  This is a lambda: a shorter way to write an anonymous function

         */
        new Handler().postDelayed(() -> {
            setContentView(R.layout.activity_main);

            Button signup= findViewById(R.id.signup);
            Button login= findViewById(R.id.login);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_main, new MainFragment())
                    .commit();

        }, 2500);



















    }




}