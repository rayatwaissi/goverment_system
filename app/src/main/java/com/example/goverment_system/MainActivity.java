package com.example.goverment_system;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
            signup.setOnClickListener(v -> showSignupLayout());
            login.setOnClickListener(v ->showLoginLayout());

        }, 2500);



















    }



//methods to move between sign up  and login page
    private void showMainPage () {
        setContentView(R.layout.activity_main);
        TextView tvlogin= findViewById(R.id.login);
        TextView tvsignup = findViewById(R.id.signup);
        tvlogin.setOnClickListener(v->showLoginLayout());
        tvsignup.setOnClickListener(v->showSignupLayout());
    }
    private void showSignupLayout() {
        setContentView(R.layout.signup);
// click on "Have an account ? login " leads to move login page
       TextView tvlogin= findViewById(R.id.tvlogin);
     //   tvlogin.setOnClickListener(v ->showLoginLayout());
        tvlogin.setOnClickListener(v->showMainPage());
    }

    private void showLoginLayout() {
        setContentView(R.layout.login);
// click on " Dont have an account ? sign up " leads to move signup  page
        TextView tvsignup = findViewById(R.id.tvSignUp);
     //   tvsignup.setOnClickListener(v -> showSignupLayout());
        tvsignup.setOnClickListener(v->showMainPage());

        Button redHome =findViewById(R.id.login_btn);
        redHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.home);
            }
        });

    }
}