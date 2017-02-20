package com.example.osamaarshad.deafassist;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class CallRecordsFragment extends Fragment {
    RecyclerView recyclerView;
    Context context;
    RecyclerView.Adapter postAdapter;
    ArrayList<RecordData> recordDatas;
    //Firebase firebase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_call_records, container, false);
        View view=inflater.inflate(R.layout.recyclerview,container,false);
        return view;
//        Firebase.setAndroidContext(context);
//        firebase=new Firebase("https://deafassist-3920d.firebaseio.com/");
//        recyclerView=(RecyclerView)view.findViewById(R.id.cardList);
//        recyclerView.setHasFixedSize(true);
//        LinearLayoutManager llm = new LinearLayoutManager(context);
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(llm);
//        firebase.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//        postAdapter=new PostAdapter();
//        recyclerView.setAdapter(postAdapter);
//        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
        init();
    }

    public void init(){
        recyclerView=(RecyclerView) getView().findViewById(R.id.cardList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        PostAdapter postAdapter=new PostAdapter(recordDatas);
        recyclerView.setAdapter(postAdapter);


    }

    public void loadData(){
        recordDatas=new ArrayList<>();
        RecordData recordData=new RecordData("1","hello");
        RecordData recordData1=new RecordData("2","hello");
        RecordData recordData2=new RecordData("3","hello");
        RecordData recordData3=new RecordData("4","hello");
        RecordData recordData4=new RecordData("5","hello");
        recordDatas.add(recordData);
        recordDatas.add(recordData1);
        recordDatas.add(recordData2);
        recordDatas.add(recordData3);
        recordDatas.add(recordData4);

        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("DeafAssist",Context.MODE_PRIVATE);
        for(int i=0;i<=PopUp2withoutGoogleIntent.count;i++){
            String text=sharedPreferences.getString(String.valueOf(i),"");
            if(!text.equals("")) {
                RecordData recordData5 = new RecordData(String.valueOf(i), text);
                recordDatas.add(recordData5);
            }
        }



    }
}
