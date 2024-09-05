package com.example.loginsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView loginErrMsg;
    private EditText userNameField, pswField;
    private Button loginBtn, registerBtn;
    private String usernameStr, passwordStr;
    private dbConnect DB;

    private void showLoginErrMsg(String message){
        loginErrMsg.setText(message);
        loginErrMsg.setVisibility(View.VISIBLE);
    }
    private void hideLoginErrMsg(){
        loginErrMsg.setVisibility(View.GONE);
    }

    private boolean areCredentialsValid(){
        boolean isUserExists = DB.checkUsernameAndPassword(usernameStr, passwordStr);

        return isUserExists;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        userNameField = findViewById(R.id.userNameField);
        pswField = findViewById(R.id.pswField);
        loginBtn = findViewById(R.id.loginBtn);
        registerBtn = findViewById(R.id.registerBtn);
        loginErrMsg = findViewById(R.id.loginErrorLabel);
        DB = new dbConnect(this);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RegisterForm.class);
                startActivity(i);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameStr = userNameField.getText().toString().trim();
                passwordStr = pswField.getText().toString().trim();
                if(usernameStr.equals("") || passwordStr.equals("")){
                    showLoginErrMsg("Fields cannot be empty!");
                }
                else{
                    hideLoginErrMsg();
                    if(!areCredentialsValid()){
                        showLoginErrMsg("Username or password is incorrect");
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });
    }
}