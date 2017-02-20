package com.example.osamaarshad.deafassist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        ImageView view1=(ImageView)findViewById(R.id.records);
        view1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent i= new Intent(MainScreen.this,MainActivity.class);//homescreen of your app.

                startActivity(i);
                finish();

            }
        });

        ImageView view2=(ImageView)findViewById(R.id.speechtotext);
        view2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent i= new Intent(MainScreen.this,MainActivity.class);//homescreen of your app.

                startActivity(i);
                finish();

            }
        });


        ImageView view3=(ImageView)findViewById(R.id.speechtotexttest);
        view3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent i= new Intent(MainScreen.this,PopUp2withoutGoogleIntent.class);//homescreen of your app.

                startActivity(i);

            }
        });
    }

}
