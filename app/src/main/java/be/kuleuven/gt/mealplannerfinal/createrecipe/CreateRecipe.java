package be.kuleuven.gt.mealplannerfinal.createrecipe;

import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;

import android.text.InputType;
import android.view.View;

import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import be.kuleuven.gt.mealplannerfinal.R;

public class CreateRecipe extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView mImageView;
    private View imageButton;
    private List<String> ingriList = new ArrayList<>(); // has all the ingredients list
    private String[] options = {"Breakfast", "Lunch", "Dinner", "Dessert", "Vegetarian", "Halal", "Vegan", "Fast Food"};
    private TextView tagsList;
    private boolean[] checkedOptions = {false, false, false, false,false, false, false, false};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        mImageView = findViewById(R.id.imageView);
        imageButton = findViewById(R.id.imageButton);
        tagsList = findViewById(R.id.editTextTags);
        getSupportActionBar().hide();
        setActivityBackgroundColor(Color.parseColor("#FFA500"));
    }
    public void setActivityBackgroundColor(int color)
    {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }

    public void onbtnImage_Clicked(View view)
    {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
        imageButton.setVisibility(View.GONE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            Picasso.get().load(imageUri).resize(600, 800).into(mImageView);
        }
    }
    public void onBtnAddIngridients_Clicked(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Ingredients");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String ingredients = input.getText().toString();
                ingriList.addAll(Arrays.asList(ingredients.split(",")));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Set long click listener to show the ingredients list
        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                View customLayout = getLayoutInflater().inflate(R.layout.ingredients_dialog_layout, null);
                TextView ingredientsTextView = customLayout.findViewById(R.id.txtIngredients);

                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < ingriList.size(); i++) {
                    String ingredient = ingriList.get(i);
                    stringBuilder.append((i + 1)).append(". ").append(ingredient).append("\n");
                }
                String string = stringBuilder.toString().trim();

                ingredientsTextView.setText(string);

                AlertDialog.Builder ingredientsDialogBuilder = new AlertDialog.Builder(CreateRecipe.this);
                ingredientsDialogBuilder.setView(customLayout);
                AlertDialog ingredientsDialog = ingredientsDialogBuilder.create();

                Window dialogWindow = ingredientsDialog.getWindow();
                dialogWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFA500"))); // Orange background color
                ingredientsTextView.setTextSize(16);
                ingredientsTextView.setTypeface(null, Typeface.BOLD);
                ingredientsDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                ingredientsDialog.show();
                return true;
            }
        });

        builder.show();
    }
    public void onBtnCategory_Clicked(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Options")
                .setMultiChoiceItems(options, checkedOptions, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkedOptions[which] = isChecked;
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Process selected options
                        StringBuilder selectedOptions = new StringBuilder();
                        for (int i = 0; i < options.length; i++) {
                            if (checkedOptions[i]) {
                                selectedOptions.append(options[i]).append(", ");
                            }
                        }
                        //Toast.makeText(CreateRecipe.this, "Selected options: " + selectedOptions.toString(), Toast.LENGTH_SHORT).show();
                        tagsList.setText("Your Tags are: " + selectedOptions.toString());
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}


