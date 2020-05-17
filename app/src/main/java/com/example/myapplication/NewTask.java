package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class NewTask extends AppCompatActivity {
    TextView addtitle,adddesc,adddate;
    EditText titletodo,desc,dates;
    Button btnsave,btncancel;
    DatabaseReference reference;
    Integer donum=new Random().nextInt();
    String key=Integer.toString(donum);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        addtitle=findViewById(R.id.addtitle);
        adddesc=findViewById(R.id.adddesc);
        adddate=findViewById(R.id.adddate);
        titletodo=findViewById(R.id.titletodo);
        desc=findViewById(R.id.desc);
        dates=findViewById(R.id.dates);

        btnsave=findViewById(R.id.btnSaveTask);
        btncancel=findViewById(R.id.btnCancel);

        btnsave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                reference= FirebaseDatabase.getInstance().getReference().child("NewData").child("Does"+donum);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("titletodo").setValue(titletodo.getText().toString());
                        dataSnapshot.getRef().child("desc").setValue(desc.getText().toString());
                        dataSnapshot.getRef().child("dates").setValue(dates.getText().toString());
                        dataSnapshot.getRef().child("key").setValue(key);

                        Intent a=new Intent(NewTask.this,MainActivity.class);
                        startActivity(a);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


    }
}
