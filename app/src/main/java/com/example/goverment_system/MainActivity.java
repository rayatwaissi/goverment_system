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

        TextView tvlogin = findViewById(R.id.tvlogin);
        tvlogin.setOnClickListener(v -> showMainPage());

        EditText nameET = findViewById(R.id.name);
        EditText emailET = findViewById(R.id.email);
        EditText passwordET = findViewById(R.id.password);
        EditText confirmPasswordET = findViewById(R.id.ConfirmPass);
        EditText phoneET = findViewById(R.id.phone);
        Button registerBtn = findViewById(R.id.signup_btn);

        registerBtn.setOnClickListener(v -> {
            String name = nameET.getText().toString().trim();
            String email = emailET.getText().toString().trim();
            String password = passwordET.getText().toString().trim();
            String confirmPassword = confirmPasswordET.getText().toString().trim();
            String phone = phoneET.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || phone.isEmpty()) {
                Toast.makeText(MainActivity.this, "please ,fill all the fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(MainActivity.this, "The passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            String userId = mAuth.getCurrentUser().getUid();

                            // أنشئ مرجع لقاعدة البيانات
                            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Users");

                            // خزّن البيانات كمجموعة بيانات بسيطة
                            User user = new User(name, email, phone);
                            dbRef.child(userId).setValue(user)
                                    .addOnSuccessListener(aVoid -> {
                                        Toast.makeText(MainActivity.this, "Registration successful and data saved", Toast.LENGTH_SHORT).show();
                                        setContentView(R.layout.home);
                                    })
                                    .addOnFailureListener(e -> {
                                        Toast.makeText(MainActivity.this, "Data storage failed " + e.getMessage(), Toast.LENGTH_LONG).show();
                                    });

                        } else {
                            Toast.makeText(MainActivity.this, "Registration failed " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        });
    }

    private void showLoginLayout() {
        setContentView(R.layout.login);

        TextView tvsignup = findViewById(R.id.tvSignUp);
        tvsignup.setOnClickListener(v -> showMainPage());

        EditText emailET = findViewById(R.id.email_login);
        EditText passwordET = findViewById(R.id.password_login);
        Button loginBtn = findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(v -> {
            String email = emailET.getText().toString().trim();
            String password = passwordET.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(MainActivity.this, "please enter the password and email field", Toast.LENGTH_SHORT).show();
                return;
            }

            // التحقق من بيانات تسجيل الدخول باستخدام Firebase
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            setContentView(R.layout.home); // انتقال إلى الصفحة الرئيسية
                        } else {
                            Toast.makeText(MainActivity.this, "Failed ,inccorect password or email" , Toast.LENGTH_LONG).show();
                        }
                    });
        });
    }

}