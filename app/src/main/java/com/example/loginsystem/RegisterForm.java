package com.example.loginsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterForm extends AppCompatActivity {

    private TextView errorMsg;

    private void showErrMsg(String message) {
        errorMsg.setText(message);
        errorMsg.setVisibility(View.VISIBLE);
    }

    private void hideErrMsg() {
        errorMsg.setVisibility(View.GONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        errorMsg = findViewById(R.id.errorMsg);

        EditText lastNameInp, midNameInp, firstNameInp, dob, emailAddInp, phoneNumber, userNameInp, pswInp, confirmpsw;
        Button submitBtn;

        lastNameInp = findViewById(R.id.lastNameField);
        midNameInp = findViewById(R.id.middleNameField);
        firstNameInp = findViewById(R.id.firstNameField);
        dob = findViewById(R.id.dateOfBirth);
        emailAddInp = findViewById(R.id.emailAddressField);
        phoneNumber = findViewById(R.id.phoneNumber);
        userNameInp = findViewById(R.id.createUserNameField);
        pswInp = findViewById(R.id.passwordField);
        confirmpsw = findViewById(R.id.confirmPass);

        submitBtn = findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lastNameStr = lastNameInp.getText().toString().trim();
                String midNameStr = midNameInp.getText().toString().trim();
                String firstNameStr = firstNameInp.getText().toString().trim();
                String dobStr = dob.getText().toString().trim();
                String emailAddressStr = emailAddInp.getText().toString().trim();
                String phoneNumberStr = phoneNumber.getText().toString().trim();
                String usernameStr = userNameInp.getText().toString().trim();
                String pswStr = pswInp.getText().toString().trim();

                // Check for empty fields
                if (lastNameStr.isEmpty() || midNameStr.isEmpty() || firstNameStr.isEmpty() ||
                        dobStr.isEmpty() || emailAddressStr.isEmpty() || phoneNumberStr.isEmpty() ||
                        usernameStr.isEmpty() || pswStr.isEmpty()) {
                    showErrMsg("All fields are required*");
                } else {
                    hideErrMsg();
                    // Create user and add to database
                    Users user1 = new Users(1, firstNameStr, midNameStr, lastNameStr, emailAddressStr, dobStr, usernameStr, pswStr, phoneNumberStr);
                    dbConnect myDb = new dbConnect(RegisterForm.this);
                    try {
                        myDb.addUser(user1);
                        Toast.makeText(RegisterForm.this, "Submitted successfully", Toast.LENGTH_SHORT).show();

                        lastNameInp.setText("");
                        midNameInp.setText("");
                        firstNameInp.setText("");
                        dob.setText("");
                        emailAddInp.setText("");
                        phoneNumber.setText("");
                        userNameInp.setText("");
                        pswInp.setText("");
                        confirmpsw.setText("");
                    } catch (Exception e) {
                        Toast.makeText(RegisterForm.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
