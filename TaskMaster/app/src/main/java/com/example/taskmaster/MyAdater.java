package com.example.taskmaster;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyAdater extends RecyclerView.Adapter<MyAdater.MyViewHolder> {
    Context context;
    ArrayList<taskClass> list;

    public MyAdater(Context context, ArrayList<taskClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.task_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        taskClass taskclass=list.get(position);
        holder.tvtitle.setText(taskclass.getTitle());
        holder.tvtask.setText(taskclass.getTask());

        String tid=taskclass.getTid();
        String username=taskclass.getUsername();

        holder.ivedit.setOnClickListener(View->{
            Intent intent=new Intent(context,editTaskActivity.class);

            intent.putExtra("title", taskclass.getTitle());
            intent.putExtra("task", taskclass.getTask());
            intent.putExtra("tid", tid);
            intent.putExtra("username", username);
            context.startActivity(intent);

        });

        holder.ivdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                DatabaseReference reference = database.child("Users").child(username).child("tasks").child(tid);
                reference.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Remove the item from your list
                        list.remove(position);

                        // Notify the adapter
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, list.size());

                        Toast.makeText(context,"Task deleted successfully...",Toast.LENGTH_LONG).show();
                    }
                });
            }



        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvtitle,tvtask;
        ImageView ivedit,ivdelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvtitle=itemView.findViewById(R.id.tvtitle);
            tvtask=itemView.findViewById(R.id.tvtask);
            ivedit=itemView.findViewById(R.id.ivedit);
            ivdelete=itemView.findViewById(R.id.ivdelete);



        }
    }
}
