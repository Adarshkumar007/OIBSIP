package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView logout,addtask;
    private ProgressDialog progressDialog;
    ArrayList<taskClass> list;

    MyAdater adater;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        logout=findViewById(R.id.logout);
        addtask=findViewById(R.id.addtask);
        recyclerView=findViewById(R.id.recycleView);

        Intent intent = getIntent();
        String username = intent.getStringExtra("uname");

        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);

        addtask.setOnClickListener(View->{
            Intent intent1=new Intent(MainActivity.this, addTask.class);
            intent1.putExtra("un",username);
            startActivity(intent1);

        });

        logout.setOnClickListener(View->{

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Do you want to logout?");
                    builder.setTitle("Alert!");
                    builder.setIcon(R.drawable.alert);
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            progressDialog.setMessage("Logging out...");
                            progressDialog.show();

                            DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Users");
                            Query checkUserDatabase=ref.orderByChild("username").equalTo(username);

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    ref.child(username).child("online").setValue("false");
                                    onBackPressed();
                                }
                            }, 1000);  // Delay in milliseconds


                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();


        });


        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Users").child(username).child("tasks");
//        Query checkTask=ref.orderByChild("tasks");
        list=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adater=new MyAdater(this,list);
        recyclerView.setAdapter(adater);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    taskClass taskclass=dataSnapshot.getValue(taskClass.class);
                    list.add(taskclass);
                }
                adater.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }


}