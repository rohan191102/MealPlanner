package be.kuleuven.gt.mealplannerfinal.myrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import be.kuleuven.gt.mealplannerfinal.R;

public class MyRecipes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipes);
        getSupportActionBar().hide();
    }
}