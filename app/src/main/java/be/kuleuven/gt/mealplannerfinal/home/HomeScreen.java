package be.kuleuven.gt.mealplannerfinal.home;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import be.kuleuven.gt.mealplannerfinal.R;
import be.kuleuven.gt.mealplannerfinal.createrecipe.CreateRecipe;
import be.kuleuven.gt.mealplannerfinal.menu.MenuPage;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        getSupportActionBar().hide();
    }
    public void onFabAddRecipe_Clicked(View Caller)
    {
        Intent intent = new Intent(this, CreateRecipe.class);
        startActivity(intent);
    }
    public void onBtnMenu_Clicked(View Caller)
    {
        Intent intent = new Intent(this, MenuPage.class);
        startActivity(intent);
    }
}