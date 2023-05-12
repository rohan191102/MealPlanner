package be.kuleuven.gt.mealplannerfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toolbar;

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

}