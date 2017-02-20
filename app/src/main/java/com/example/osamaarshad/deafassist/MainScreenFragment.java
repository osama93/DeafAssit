package com.example.osamaarshad.deafassist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class MainScreenFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_screen, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        ImageView view1=(ImageView)getView().findViewById(R.id.records);
        view1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//                Intent i= new Intent(getActivity(),CallRecordsFragment.class);//homescreen of your app.
//
//                startActivity(i);


            }
        });

        ImageView view2=(ImageView)getView().findViewById(R.id.speechtotext);
        view2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent i= new Intent(getActivity(),MainActivity.class);//homescreen of your app.

                startActivity(i);

            }
        });


        ImageView view3=(ImageView)getView().findViewById(R.id.speechtotexttest);
        view3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent i= new Intent(getActivity(),PopUp2withoutGoogleIntent.class);//homescreen of your app.

                startActivity(i);

            }
        });
    }



}
