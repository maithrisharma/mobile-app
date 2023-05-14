package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/* Main Activity */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         /* Finding the view from Layout XML */
        EditText userName=findViewById(R.id.userName);
        EditText password= findViewById(R.id.password);
        Button login=findViewById(R.id.login);
        Button register=findViewById(R.id.register);

        /* Database Connection */
        dbConnect db= new dbConnect(this);

        /* Setting onclick listener for login Button*/
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Getting the data from Edit Text Views */
                String name= userName.getText().toString();
                String pass= password.getText().toString();

                /* Checking if the fields and empty */
                if(name.equals("") || pass.equals("")){
                    Toast.makeText(MainActivity.this,"ALL FIELDS REQUIRED",Toast.LENGTH_SHORT).show();
                }
                else{
                    /* Check if the password matches */
                    int userId=db.checkPassword(name,pass);
                    if(userId!=0){
                        Users user = db.viewUser(userId);
                        if(user.getRank()==1){
                            /* Login for Admin and go to Home activity */
                            Toast.makeText(MainActivity.this,"LOGIN SUCCESSFULL",Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent(MainActivity.this,Home.class);
                            intent.putExtra("UserId",userId);
                            startActivity(intent);
                        }
                        else {
                            /* Login for normal user and go to Dashboard Activity*/
                            Toast.makeText(MainActivity.this, "LOGIN SUCCESSFULL", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, Dashboard.class);
                            intent.putExtra("UserId", userId);
                            startActivity(intent);
                        }

                    }
                    else{
                        /* Displaying Error Message in Toast */
                        Toast.makeText(MainActivity.this,"INVALID CREDENTIALS",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        /* Setting OnClick Listener for Register Button */
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Start Register Activity */
                Intent i= new Intent(MainActivity.this,register.class);
                startActivity(i);
            }
        });
    }
}