package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class login extends AppCompatActivity {

    EditText edusername,edpassword;
    TextView tvsignup;
    Button btnlogin;

     private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edusername =findViewById(R.id.edusername);
        edpassword=findViewById(R.id.edpassword);
        tvsignup=findViewById(R.id.signupredirect);
        btnlogin=findViewById(R.id.btnlogin);

        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateUsername()||!validatePassword())
                {

                }else{
                    checkUser();
                    progressDialog.setMessage("Logging in...");
                    progressDialog.show();
                }
            }
        });

        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(login.this, activitySignup.class);
                startActivity(intent);
            }
        });

    }
    public Boolean validateUsername(){
        String val=edusername.getText().toString();
        if(val.isEmpty())
        {
            edusername.setError("Username cannot be empty");
            return false;
        }
        else{
            edusername.setError(null);
            return true;
        }
    }

    public Boolean validatePassword(){
        String val=edpassword.getText().toString();
        if(val.isEmpty())
        {
            edpassword.setError("Password cannot be empty");
            return false;
        }
        else{
            edpassword.setError(null);
            return true;
        }
    }

    public void checkUser()
    {
        String username=edusername.getText().toString();
        String password=edpassword.getText().toString();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Users");
        Query checkUserDatabase=ref.orderByChild("username").equalTo(username);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    edusername.setError(null);
                    String passwordfromdb=snapshot.child(username).child("password").getValue(String.class);


                    if(passwordfromdb.equals(password)){

                        ref.child(username).child("online").setValue("true");
                        edusername.setError(null);
                        Intent intent=new Intent(login.this,MainActivity.class);
                        intent.putExtra("uname",username);
                        startActivity(intent);
                    }
                    else {
                        edpassword.setError("Invalid password");
                        edpassword.requestFocus();
                    }
                    progressDialog.dismiss();

                }
                else{
                    edusername.setError("Invalid username");
                    edusername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}