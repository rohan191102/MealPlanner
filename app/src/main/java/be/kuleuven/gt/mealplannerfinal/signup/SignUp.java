package be.kuleuven.gt.mealplannerfinal.signup;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import be.kuleuven.gt.mealplannerfinal.R;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
    }
}