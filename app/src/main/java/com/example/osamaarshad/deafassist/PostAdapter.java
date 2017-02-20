package com.example.osamaarshad.deafassist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by OsamaArshad on 19-Feb-17.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ContactViewHolder> {

    ArrayList<RecordData> data;

    public PostAdapter(ArrayList<RecordData> d){
        this.data=d;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        contactViewHolder.name.setText((data.get(i)).getText());
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.cardview, viewGroup, false);

        return new ContactViewHolder(itemView);
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        protected TextView name;

        public ContactViewHolder(View v) {
            super(v);
            name =  (TextView) v.findViewById(R.id.RecordCardView);
        }
    }
}
