package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activitySignup extends AppCompatActivity {

    EditText edname,edemail,edusername,edpassword;
    TextView login;
    Button signup;
    FirebaseDatabase database;
    DatabaseReference reference;

    String online="false";

    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
       edname=findViewById(R.id.edname);
       edemail=findViewById(R.id.edmail);
       edusername=findViewById(R.id.edusername);
       edpassword=findViewById(R.id.edpassword);
       signup=findViewById(R.id.btnsign);
       login=findViewById(R.id.loginredirect);

        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateUsername() && validatePassword() && validateEmail() && validateName()) {
                    progressDialog.setMessage("Signing up...");
                    progressDialog.show();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            database = FirebaseDatabase.getInstance();
                            reference = database.getReference("Users");
                            String name = edname.getText().toString();
                            String email = edemail.getText().toString();
                            String username = edusername.getText().toString();
                            String password = edpassword.getText().toString();

                            HelperClass helperClass = new HelperClass(name, email, username, password, online);

                            reference.child(username).setValue(helperClass);
                            Toast.makeText(activitySignup.this, "You have signup sucessfully...", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(activitySignup.this, login.class);
                            startActivity(intent);
                        }
                    }, 1000);  // Delay in milliseconds


                }

            }

        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activitySignup.this, login.class);
                startActivity(intent);
            }
        });
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

    public Boolean validateEmail(){
        String val=edemail.getText().toString();
        if(val.isEmpty())
        {
            edemail.setError("Email cannot be empty");
            return false;
        }
        else{
            edemail.setError(null);
            return true;
        }
    }

    public Boolean validateName(){
        String val=edname.getText().toString();
        if(val.isEmpty())
        {
            edname.setError("Name cannot be empty");
            return false;
        }
        else{
            edname.setError(null);
            return true;
        }
    }
}