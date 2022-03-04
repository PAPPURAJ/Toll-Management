package com.blogspot.rajbtc.tollmanagement;


import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    String TAG="===Main Activity===";
    TextView shamimBalance,shamimCount,tamimBalance,tamimCount;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shamimBalance=findViewById(R.id.balShamimTv);
        shamimCount=findViewById(R.id.countShamimTv);
        tamimBalance=findViewById(R.id.balTamimTv);
        tamimCount=findViewById(R.id.countTamimTv);




        mainCarChange();
        loadShamim();
        loadTamim();

    }






    void loadShamim(){
        DatabaseReference nituRef = database.getReference("Shamim");
        nituRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.e("=====",snapshot.getValue().toString());

                if(snapshot.getKey().equals("Balance")){
                    shamimBalance.setText(("Balance: "+snapshot.getValue().toString()));
                }else if(snapshot.getKey().equals("Count")){
                    shamimCount.setText("Total Count: "+snapshot.getValue().toString());
                }


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.e("=====",snapshot.getValue().toString());

                if(snapshot.getKey().equals("Balance")){
                    shamimBalance.setText(("Balance: "+snapshot.getValue().toString()));
                }else if(snapshot.getKey().equals("Count")){
                    shamimCount.setText("Total Count: "+snapshot.getValue().toString());
                }

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }




    void loadTamim(){
        DatabaseReference nituRef = database.getReference("Tamim");
        nituRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.e("=====",snapshot.getValue().toString());

                if(snapshot.getKey().equals("Balance")){
                    tamimBalance.setText(("Balance: "+snapshot.getValue().toString()));
                }else if(snapshot.getKey().equals("Count")){
                    tamimCount.setText("Total Count: "+snapshot.getValue().toString());
                }


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.e("=====",snapshot.getValue().toString());

                if(snapshot.getKey().equals("Balance")){
                    tamimBalance.setText(("Balance: "+snapshot.getValue().toString()));
                }else if(snapshot.getKey().equals("Count")){
                    tamimCount.setText("Total Count: "+snapshot.getValue().toString());
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }




    void mainCarChange(){
        DatabaseReference myRef = database.getReference("Bar");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                if(value.equals("1")){
                    ((ImageView) findViewById(R.id.mainIv)).setImageResource(R.drawable.car);
                }else{
                    ((ImageView) findViewById(R.id.mainIv)).setImageResource(R.drawable.no_car);
                }
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }
}