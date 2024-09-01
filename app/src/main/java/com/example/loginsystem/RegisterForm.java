package com.example.loginsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterForm extends AppCompatActivity {

    private TextView errorMsg;

    private void showErrMsg(String message){
        errorMsg.setText(message);
        errorMsg.setVisibility(View.VISIBLE);
    }
    private void hideErrMsg(){
        errorMsg.setVisibility(View.GONE);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_form);

        errorMsg = findViewById(R.id.errorMsg);

        EditText lastNameInp, midNameInp, firstNameInp, emailAddInp, userNameInp, pswInp;
        Button submitBtn, loginBtn;

        lastNameInp = findViewById(R.id.lastNameField);
        midNameInp = findViewById(R.id.middleNameField);
        firstNameInp = findViewById(R.id.dateOfBirth);
        emailAddInp = findViewById(R.id.phoneNumber);
        userNameInp = findViewById(R.id.createUserNameField);
        pswInp = findViewById(R.id.passwordField);

        submitBtn = findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToLoginPage = new Intent(RegisterForm.this, MainActivity.class);

                String lastNameStr = lastNameInp.getText().toString().trim();

                if(lastNameStr.isEmpty()){
                    showErrMsg("All fields are required*");
                }
                else{
                    hideErrMsg();
                    startActivity(goToLoginPage);
                }
            }
        });




    }
}