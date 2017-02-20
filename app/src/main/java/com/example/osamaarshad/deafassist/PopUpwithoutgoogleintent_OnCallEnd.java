package com.example.osamaarshad.deafassist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PopUpwithoutgoogleintent_OnCallEnd extends Activity {
    private TextView returnedText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup2oncallend);


        /*returnedText = (TextView) findViewById(R.id.textView1);

        Intent intent = this.getIntent();
        String data = intent.getStringExtra("value");
        returnedText.setText(data);*/
        TextView textView1=(TextView) findViewById(R.id.textView1);
        returnedText = (TextView) findViewById(R.id.textView1);
        try {
            RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.popup2layout, null);
            TextView textView = (TextView) relativeLayout.findViewById(R.id.textView1);
            String result = textView.getText().toString();
            textView1.setText(result);
            Log.d("textinintent", result);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        ImageView view=(ImageView)findViewById(R.id.close);
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent i= new Intent("package.homescreenactivity");//homescreen of your app.
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY);
                //startActivity(i);
                finish();

            }
        });
    }
}
