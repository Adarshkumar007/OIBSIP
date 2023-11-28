package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class editTaskActivity extends AppCompatActivity {
    EditText edtitle,edtask;
    Button button;

    String username,tid,title,task;


    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        edtitle = findViewById(R.id.edtitle);
        edtask = findViewById(R.id.edtask);
        button = findViewById(R.id.btnsave);

        Intent intent = getIntent();
         username = intent.getStringExtra("username");
         tid = intent.getStringExtra("tid");
         title = intent.getStringExtra("title");
        task = intent.getStringExtra("task");

        edtitle.setText(title);
        edtask.setText(task);

        button.setOnClickListener(View->{
            database = FirebaseDatabase.getInstance();
            reference = database.getReference("Users");




             title=edtitle.getText().toString();
           task=edtask.getText().toString();

            taskClass taskClass = new taskClass(tid,title,task,username);

            reference.child(username).child("tasks").child(tid).setValue(taskClass);


            Toast.makeText(editTaskActivity.this,"task saved successfully",Toast.LENGTH_SHORT).show();


            Intent i=new Intent(editTaskActivity.this,MainActivity.class);
            startActivity(i);
            finish();
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