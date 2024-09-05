package com.example.loginsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterForm extends AppCompatActivity {

    private TextView errorMsg, passwordNotMatch;
    private EditText lastNameInp, midNameInp, firstNameInp, dob, emailAddInp, phoneNumber, userNameInp, pswInp, confirmpsw;
    private Button submitBtn;
    private String lastNameStr, midNameStr, firstNameStr, dobStr, emailAddressStr, phoneNumberStr, usernameStr, pswStr, confirmPswStr;
    private dbConnect myDb;

    private void handleEmptyFields(String message) {
        errorMsg.setText(message);
        errorMsg.setVisibility(View.VISIBLE);
    }

    private void hideEmptyFieldsHandler() {
        errorMsg.setVisibility(View.GONE);
    }

    private void clearAllFields(){
        lastNameInp.setText("");
        midNameInp.setText("");
        firstNameInp.setText("");
        dob.setText("");
        emailAddInp.setText("");
        phoneNumber.setText("");
        userNameInp.setText("");
        pswInp.setText("");
        confirmpsw.setText("");
    }

    private void submitUserData(){
        Users user1 = new Users(1, firstNameStr, midNameStr, lastNameStr, emailAddressStr, dobStr, usernameStr, pswStr, phoneNumberStr);
        myDb = new dbConnect(RegisterForm.this);
        try {
            myDb.addUser(user1);
            Toast.makeText(RegisterForm.this, "Submitted successfully", Toast.LENGTH_SHORT).show();
            clearAllFields();
        } catch (Exception e) {
            Toast.makeText(RegisterForm.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private boolean isPasswordMismatch(){
        boolean passwordMismatched = true;

        if(!pswStr.equals(confirmPswStr)){
            passwordNotMatch.setText("Password does not match");
            passwordNotMatch.setVisibility(View.VISIBLE);
        }
        else{
            passwordNotMatch.setVisibility(View.GONE);
            passwordMismatched = false;
        }
        return passwordMismatched;
    }


    private boolean areFieldsEmpty(){
        return lastNameStr.isEmpty() || midNameStr.isEmpty() || firstNameStr.isEmpty() ||
                dobStr.isEmpty() || emailAddressStr.isEmpty() || phoneNumberStr.isEmpty() ||
                usernameStr.isEmpty() || pswStr.isEmpty() || confirmPswStr.isEmpty();
    }

    private boolean isUsernameExists() {
        myDb = new dbConnect(RegisterForm.this);
        boolean usernameExists = myDb.isUsernameExist(usernameStr);

        if (usernameExists) {
            errorMsg.setText("Username already exists!");
            errorMsg.setVisibility(View.VISIBLE);
        } else {
            errorMsg.setVisibility(View.GONE);
        }

        return usernameExists;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        errorMsg = findViewById(R.id.errorMsg);
        passwordNotMatch = findViewById(R.id.passwordNotMatchLabelErr);

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
                lastNameStr = lastNameInp.getText().toString().trim();
                midNameStr = midNameInp.getText().toString().trim();
                firstNameStr = firstNameInp.getText().toString().trim();
                dobStr = dob.getText().toString().trim();
                emailAddressStr = emailAddInp.getText().toString().trim();
                phoneNumberStr = phoneNumber.getText().toString().trim();
                usernameStr = userNameInp.getText().toString().trim();
                pswStr = pswInp.getText().toString().trim();
                confirmPswStr = confirmpsw.getText().toString().trim();

                if (areFieldsEmpty()) {
                    handleEmptyFields("All fields are required*");
                }
                else {
                    hideEmptyFieldsHandler();
                    try {
                        if (!isPasswordMismatch() && !isUsernameExists()){
                            submitUserData();
                        }
                    }
                    catch (Exception e){
                        Toast.makeText(RegisterForm.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
