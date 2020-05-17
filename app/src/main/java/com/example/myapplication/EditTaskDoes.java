package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditTaskDoes extends AppCompatActivity {
    EditText titletodo,desc,dates;
    Button btnSaveUpdate,btnDelete;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task_does);
        titletodo=findViewById(R.id.titletodo);
        desc=findViewById(R.id.desc);
        dates=findViewById(R.id.dates);
        btnSaveUpdate=findViewById(R.id.btnSaveUpdate);
        btnDelete=findViewById(R.id.btnDelete);
        titletodo.setText(getIntent().getStringExtra("titletodo"));
        desc.setText(getIntent().getStringExtra("desc"));
        dates.setText(getIntent().getStringExtra("dates"));
        final String keyDoes=getIntent().getStringExtra("key");
        reference= FirebaseDatabase.getInstance().getReference().child("NewData").child("Does"+keyDoes);
         btnDelete.setOnClickListener(new View.OnClickListener(){

             @Override
             public void onClick(View v) {
                 reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                     @Override
                     public void onComplete(@NonNull Task<Void> task) {
                         if(task.isSuccessful()){
                             Intent a=new Intent(EditTaskDoes.this,MainActivity.class);
                             startActivity(a);
                         }else{
                             Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();
                         }

                     }
                 });
             }
         });

        btnSaveUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("titletodo").setValue(titletodo.getText().toString());
                        dataSnapshot.getRef().child("desc").setValue(desc.getText().toString());
                        dataSnapshot.getRef().child("dates").setValue(dates.getText().toString());
                        dataSnapshot.getRef().child("key").setValue(keyDoes);
                        Intent a=new Intent(EditTaskDoes.this,MainActivity.class);
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
