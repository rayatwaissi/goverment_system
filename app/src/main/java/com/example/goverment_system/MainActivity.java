package com.example.goverment_system;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
 // ----------------------------------------------------------------------------
        //To quickly view the image

        //  a system service that takes( picture_show.xml)file and turns it into actual View objects in memory.
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.picture_show,null);

        /*  Creating a new Toast object using the app's context.
            Setting the display duration of the toast
           telling the Toast to use the custom layout view (picture_show.xml).
           showing the Toast on screen
         */
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();



    }
}