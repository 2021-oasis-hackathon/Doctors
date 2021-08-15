package com.example.videochat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FindPeopleActivity extends AppCompatActivity {

    private RecyclerView findFirendList;
    private EditText searchEt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_people);

        searchEt = findViewById(R.id.search_user_text);
        findFirendList = findViewById(R.id.find_friends_list);

        findFirendList.setLayoutManager(new LinearLayoutManager(new LinearLayoutManager(getApplicationContext())));

    }

    public static class FindFriendsViewHolder extends RecyclerView.ViewHolder {

        TextView userNameTxt;
        Button videoCallBtn;
        ImageView profileImageView;
        RelativeLayout cardView;



        public FindFriendsViewHolder(@NonNull View itemView) {
            super(itemView);

            userNameTxt = itemView.findViewById(R.id.name_contact);
            videoCallBtn = itemView.findViewById(R.id.call_btn);
            profileImageView = itemView.findViewById(R.id.image_contact);
            cardView = itemView.findViewById(R.id.card_view1);
        }
    }
}