package be.kuleuven.gt.mealplannerfinal.forgotpass;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import be.kuleuven.gt.mealplannerfinal.R;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
            getSupportActionBar().hide();
    }
}