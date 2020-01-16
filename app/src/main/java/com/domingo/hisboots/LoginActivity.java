package com.domingo.hisboots;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private static final String NONE = "";
    private static final String EMAIL_EXP = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";

    private Dialog needHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        needHelp = new Dialog(LoginActivity.this);
        TextView needHelpButton = findViewById(R.id.needHelpButton);
        needHelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNeedHelpDialog();
            }
        });

        MaterialButton loginButton =  findViewById(R.id.secondLoginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void showNeedHelpDialog() {
        needHelp.setContentView(R.layout.forget_password);

        // Implement the back button
        ImageView buttonBack = needHelp.findViewById(R.id.backButton);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                needHelp.dismiss();
            }
        });

        // Implement the reset password button
        Button buttonReset = needHelp.findViewById(R.id.resetPasswordButton);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText email = needHelp.findViewById(R.id.editTextEmailRecover);
                TextView advertisement = needHelp.findViewById(R.id.advertisementText);

                /* TODO Recover the password */

                if (checkInformation(email, advertisement)) {
                    Toast.makeText(LoginActivity.this, getString(R.string.confirmed_email), Toast.LENGTH_LONG).show();
                    needHelp.dismiss();
                }
            }
        });

        Objects.requireNonNull(needHelp.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        needHelp.show();

    }

    private boolean checkInformation(EditText email, TextView advertisement){
        // Check an empty fields
        if (email.getText().toString().equals(NONE)) {
            advertisement.setText(getString(R.string.emptyFieldsMessage));
            advertisement.setVisibility(View.VISIBLE);
            shakeAnimation(advertisement);
            return false;
        }

        // Check is the correct format on email
        if (!isEmailValid(email.getText().toString())) {
            advertisement.setText(getString(R.string.emailNotValid));
            advertisement.setVisibility(View.VISIBLE);
            shakeAnimation(advertisement);
            return false;
        }

        return true;
    }

    private static boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile(EMAIL_EXP, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void shakeAnimation(TextView advertisement){
        Animation shake = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.shake);
        advertisement.startAnimation(shake);
    }
}
