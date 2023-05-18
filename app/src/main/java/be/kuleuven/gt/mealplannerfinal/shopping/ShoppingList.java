package be.kuleuven.gt.mealplannerfinal.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import be.kuleuven.gt.mealplannerfinal.R;

public class ShoppingList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        getSupportActionBar().hide();
    }
}