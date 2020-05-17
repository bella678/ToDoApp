package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView titlepage,endpage;
    DatabaseReference reference;
    RecyclerView recycle;
    ArrayList<Do> list;
    DoAdapter doAdapter;
    Button btnAddNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titlepage=findViewById(R.id.titlepage);
        endpage=findViewById(R.id.endpage);
        btnAddNew=findViewById(R.id.btnAddNew);

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(MainActivity.this,NewTask.class);
                startActivity(a);
            }
        });

        recycle=findViewById(R.id.recycle);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<Do>();

        reference= FirebaseDatabase.getInstance().getReference().child("NewData");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Do d=dataSnapshot1.getValue(Do.class);
                    list.add(d);
                }
                doAdapter=new DoAdapter(MainActivity.this,list);
                recycle.setAdapter(doAdapter);
                doAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "NoData", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
