package com.example.videochat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {

    private String receiverUserID="",receiverUserImage="",receiverUserName="";

    private ImageView backround_profile_view;
    private TextView name_profile;
    private Button add_friend,decline_friend_request;

    private FirebaseAuth mAuth;
    private String senderUserId;

    private String currentStatus = "new";

    private DatabaseReference friendRequestRef, contactsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();

        senderUserId = mAuth.getCurrentUser().getUid();

        friendRequestRef = FirebaseDatabase.getInstance().getReference().child("Friend Requests");
        contactsRef = FirebaseDatabase.getInstance().getReference().child("Contacts");

        receiverUserID = getIntent().getExtras().get("visit_user_id").toString();
        receiverUserImage = getIntent().getExtras().get("profile_image").toString();
        receiverUserName = getIntent().getExtras().get("profile_name").toString();

        backround_profile_view = findViewById(R.id.backround_profile_view);
        name_profile = findViewById(R.id.name_profile);
        add_friend = findViewById(R.id.add_friend);
        decline_friend_request = findViewById(R.id.decline_friend_request);

        Picasso.get().load(receiverUserImage).into(backround_profile_view);
        name_profile.setText(receiverUserName);

        manageClickEvents();

    }

    private void manageClickEvents() {

        friendRequestRef.child(senderUserId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        if(dataSnapshot.hasChild(receiverUserID)){

                            String requestType = dataSnapshot.child(receiverUserID).child("request_type").getValue().toString();

                            if(requestType.equals("sent")){

                                currentStatus = "request_sent";

                                add_friend.setText("Cancel Friend Request");
                            }

                            else if(requestType.equals("received")){

                                currentStatus = "request_received";
                                add_friend.setText("Accept Friend Request");

                                decline_friend_request.setVisibility(View.VISIBLE);

                                decline_friend_request.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        CanceFriendRequest();
                                    }
                                });
                            }
                        }
                        else {

                            contactsRef.child(senderUserId)
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                            if (dataSnapshot.hasChild(receiverUserID))
                                            {

                                                currentStatus = "friends";
                                                add_friend.setText("Delete Contact");
                                            }
                                            else {

                                                currentStatus = "new";
                                            }


                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        if(senderUserId.equals(receiverUserID))
        {
            add_friend.setVisibility(View.GONE);
        }

        else
        {
            add_friend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(currentStatus.equals("new")){
                        SendFriendRequest();

                    }
                    if (currentStatus.equals("request_sent"))
                    {

                        CanceFriendRequest();



                    }
                    if (currentStatus.equals("request_received"))
                    {
                        AcceptfriendRequest();

                    }

                    if (currentStatus.equals("request_sent"))
                    {
                        CanceFriendRequest();

                    }
                }
            });
        }
    }

    private void AcceptfriendRequest() {
        contactsRef.child(senderUserId).child(receiverUserID)
                .child("Contact").setValue("Saved")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){
                            contactsRef.child(receiverUserID).child(senderUserId)
                                    .child("Contact").setValue("Saved")
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()){

                                                friendRequestRef.child(senderUserId).child(receiverUserID)
                                                        .removeValue()
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {

                                                                if(task.isSuccessful()){

                                                                    friendRequestRef.child(receiverUserID).child(senderUserId)
                                                                            .removeValue()
                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                @Override
                                                                                public void onComplete(@NonNull Task<Void> task) {

                                                                                    if (task.isSuccessful()){

                                                                                        currentStatus = "friends";
                                                                                        add_friend.setText("Delete Contact");

                                                                                        decline_friend_request.setVisibility(View.GONE);


                                                                                    }

                                                                                }
                                                                            });
                                                                }
                                                            }
                                                        });


                                            }
                                        }
                                    });

                        }
                    }
                });

    }

    private void CanceFriendRequest() {

        friendRequestRef.child(senderUserId).child(receiverUserID)
                .removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){

                            friendRequestRef.child(receiverUserID).child(senderUserId)
                                    .removeValue()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()){

                                                currentStatus = "new";
                                                add_friend.setText("Add Friend");


                                            }

                                        }
                                    });
                        }
                    }
                });
    }

    private void SendFriendRequest() {

        friendRequestRef.child(senderUserId).child(receiverUserID)
                .child("request_type").setValue("sent")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){

                            friendRequestRef.child(receiverUserID).child(senderUserId)
                                    .child("request_type").setValue("received")
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if(task.isSuccessful()){

                                                currentStatus = "request_sent";

                                                add_friend.setText("Cancel Friend Request");

                                                Toast.makeText(ProfileActivity.this, "Friend Request Sent...", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });

                        }
                    }
                });
    }
}
