package com.domingo.hisboots;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private static final String NONE = "";
    private static final String EMAIL_EXP = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";


    EditText email;
    EditText name;
    EditText password;
    EditText repeatPassword;

    TextView advertisement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.editTextEmailRegister);
        name = findViewById(R.id.editTextNameRegister);
        password = findViewById(R.id.editTextPasswordRegister);
        repeatPassword = findViewById(R.id.repeatPasswordRegister);

        advertisement = findViewById(R.id.advertisementText);

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        MaterialButton createAccountButton = findViewById(R.id.createAccountButton);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInformation())
                    finish();
            }
        });
    }

    private boolean checkInformation(){
        // Check an empty fields
        if (name.getText().toString().equals(NONE) || email.getText().toString().equals(NONE) ||
                password.getText().toString().equals(NONE) || repeatPassword.getText().toString().equals(NONE)) {
            advertisement.setText(getString(R.string.emptyFieldsMessage));
            advertisement.setVisibility(View.VISIBLE);
            shakeAnimation();
            return false;
        }

        // Check the same password
        if (!password.getText().toString().equals(repeatPassword.getText().toString())) {
            advertisement.setText(getString(R.string.passwordsNotEquals));
            advertisement.setVisibility(View.VISIBLE);
            shakeAnimation();
            return false;
        }

        /* Check the passwords more than 6 characters */
        if (password.getText().toString().length() < 6) {
            advertisement.setText(getString(R.string.ShortPassword));
            advertisement.setVisibility(View.VISIBLE);
            shakeAnimation();
            return false;
        }

        // Check is the correct format on email
        if (!isEmailValid(email.getText().toString())) {
            advertisement.setText(getString(R.string.emailNotValid));
            advertisement.setVisibility(View.VISIBLE);
            shakeAnimation();
            return false;
        }

        return true;
    }

    private static boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile(EMAIL_EXP, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void shakeAnimation(){
        Animation shake = AnimationUtils.loadAnimation(RegisterActivity.this, R.anim.shake);
        advertisement.startAnimation(shake);
    }
}
