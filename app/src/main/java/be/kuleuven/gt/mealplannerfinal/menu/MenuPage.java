package be.kuleuven.gt.mealplannerfinal.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import be.kuleuven.gt.mealplannerfinal.R;
import be.kuleuven.gt.mealplannerfinal.mealplans.MealPlans;
import be.kuleuven.gt.mealplannerfinal.home.HomeScreen;
import be.kuleuven.gt.mealplannerfinal.myrecipes.MyRecipes;
import be.kuleuven.gt.mealplannerfinal.profile.Profile;
import be.kuleuven.gt.mealplannerfinal.settings.Settings;
import be.kuleuven.gt.mealplannerfinal.shopping.ShoppingList;

public class MenuPage extends AppCompatActivity {
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);
        getSupportActionBar().hide();
    }

    public void onBtnHome_Clicked(View Caller)
    {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }

    public void onBtnRecipes_Clicked(View Caller)
    {
        Intent intent = new Intent(this, MyRecipes.class);
        startActivity(intent);
    }

    public void onBtnMealPlans_Clicked(View Caller)
    {
        Intent intent = new Intent(this, MealPlans.class);
        startActivity(intent);
    }

    public void onBtnShopping_Clicked(View Caller)
    {
        Intent intent = new Intent(this, ShoppingList.class);
        startActivity(intent);
    }

    public void onBtnProfile_Clicked(View Caller)
    {
        username = getIntent().getStringExtra("username");
        Intent intent = new Intent(this, Profile.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
    public void onBtnSettings_Clicked(View Caller)
    {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}