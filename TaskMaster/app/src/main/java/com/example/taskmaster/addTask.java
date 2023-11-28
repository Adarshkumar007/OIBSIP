package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;
import java.util.UUID;

public class addTask extends AppCompatActivity {

    EditText edtitle,edtask;
    Button button;


    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        edtitle=findViewById(R.id.edtitle);
        edtask=findViewById(R.id.edtask);
        button=findViewById(R.id.btnsave);


        Intent intent = getIntent();
        String username = intent.getStringExtra("un");

        button.setOnClickListener(View->{
            if(validateTask()&& validateTitle() && validateTitleLength()) {



                        database = FirebaseDatabase.getInstance();
                        reference = database.getReference("Users");


                        String uniqueID = UUID.randomUUID().toString();
                        String tid = username + uniqueID;

                        String title=edtitle.getText().toString();
                        String task=edtask.getText().toString();

                        taskClass taskClass = new taskClass(tid,title,task,username);

                        reference.child(username).child("tasks").child(tid).setValue(taskClass);


                Toast.makeText(addTask.this,"task saved successfully",Toast.LENGTH_SHORT).show();

                edtask.setText("");
                edtitle.setText("");
                Intent i=new Intent(addTask.this,MainActivity.class);
                startActivity(i);
                finish();




            }

        });

    }

    public Boolean validateTitle(){
        String val=edtitle.getText().toString();
        if(val.isEmpty())
        {
            edtitle.setError("Title cannot be empty");
            return false;
        }
        else{
            edtitle.setError(null);
            return true;
        }
    }

    public Boolean validateTitleLength(){
        String val=edtitle.getText().toString();
        if(val.length()>10)
        {
            edtitle.setError("Title length greater than 10 characters");
            return false;
        }
        else{
            edtitle.setError(null);
            return true;
        }
    }
    public Boolean validateTask(){
        String val=edtask.getText().toString();
        if(val.isEmpty())
        {
            edtask.setError("Task cannot be empty");
            return false;
        }
        else{
            edtask.setError(null);
            return true;
        }
    }
}