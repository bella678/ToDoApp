package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DoAdapter extends RecyclerView.Adapter<DoAdapter.MyViewHolder>{
    Context context;
    ArrayList<Do> Do;
    public DoAdapter(Context c,ArrayList<Do> d){
        context=c;
        Do=d;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_to_do,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
     holder.titletodo.setText(Do.get(i).getTitletodo());
     holder.desc.setText(Do.get(i).getDesc());
     holder.dates.setText(Do.get(i).getDates());

     final String getTitleToDo=Do.get(i).getTitletodo();
     final String getDesc=Do.get(i).getDesc();
        final String getDates=Do.get(i).getDates();
        final String getKey=Do.get(i).getKey();


        holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent aa=new Intent(context,EditTaskDoes.class);
             aa.putExtra("titletodo",getTitleToDo);
             aa.putExtra("desc",getDesc);
             aa.putExtra("dates",getDates);
             aa.putExtra("key",getKey);
             context.startActivity(aa);
         }
     });

    }

    @Override
    public int getItemCount() {

        return Do.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titletodo,desc,dates,key;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titletodo=(TextView)itemView.findViewById(R.id.titletodo);
            desc=(TextView)itemView.findViewById(R.id.desc);
            dates=(TextView)itemView.findViewById(R.id.dates);

        }
    }
}
