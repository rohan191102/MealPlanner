package be.kuleuven.gt.mealplannerfinal.profile;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import be.kuleuven.gt.mealplannerfinal.MainActivity;
import be.kuleuven.gt.mealplannerfinal.R;
import be.kuleuven.gt.mealplannerfinal.signup.SignUp;

public class Profile extends AppCompatActivity {
    private static final String QUEUE_URL = "https://studev.groept.be/api/a22pt207/Users";
    private String username;
    private static final String POST_URL_NAME = "https://studev.groept.be/api/a22pt207/Email";
    private static final String POST_URL_PASSWORD = "https://studev.groept.be/api/a22pt207/Password";
    private static final String POST_URL_PROFILE = "https://studev.groept.be/api/a22pt207/Profile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        username = getIntent().getStringExtra("username");
        TextView editTxtProfUsername = findViewById(R.id.editTxtProfUsername);
        editTxtProfUsername.setTextSize(24);
        editTxtProfUsername.setText(username);
        String POST_URL_NAME2 = POST_URL_NAME + "/" + username;
        String POST_URL_PASSWORD2 = POST_URL_PASSWORD+"/" +username;

        // Create a ProgressDialog to show loading while making the request
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        // Create a RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Create a GET request
        StringRequest getRequest = new StringRequest(
                Request.Method.GET,
                POST_URL_NAME2,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            if (jsonArray.length() > 0) {
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String email = jsonObject.getString("email");
                                TextView editTxtProfEmailId = findViewById(R.id.editTxtProfEmailId);
                                editTxtProfEmailId.setText(email);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(
                                Profile.this,
                                "Unable to retrieve data: " + error.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                }
        );
        /////////////////////////// FIXME: FOR THE PASSWORD
        RequestQueue requestQueue1 = Volley.newRequestQueue(this);
        StringRequest getRequest1 = new StringRequest(
                Request.Method.GET,
                POST_URL_PASSWORD2,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            if (jsonArray.length() > 0) {
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String password = jsonObject.getString("password");
                                TextView editTxtProfPassword = findViewById(R.id.editTxtProfPassword);
                                editTxtProfPassword.setText(password);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(
                                Profile.this,
                                "Unable to retrieve data: " + error.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                }
        );

        // Show the progress dialog
        progressDialog.show();

        // Add the request to the RequestQueue
        requestQueue.add(getRequest);
        requestQueue1.add(getRequest1);
    }

    public void onBtnProfile_Clicked(View caller)
    {
        EditText emailEditText = findViewById(R.id.editTxtProfEmailId);
        EditText passwordEditText = findViewById(R.id.editTxtProfPassword);
        String newEmail = emailEditText.getText().toString();
        String newPassword = passwordEditText.getText().toString();
        String POST_URL_PROFILE2 = POST_URL_PROFILE+"/" +newEmail+"/"+newPassword+"/"+username;
        System.out.println(POST_URL_PROFILE2);
        RequestQueue requestQueue1 = Volley.newRequestQueue(this);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        StringRequest getRequest2 = new StringRequest(
                Request.Method.GET,
                POST_URL_PROFILE2,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            Toast.makeText(
                                    Profile.this,
                                    "New Credentials Updated",
                                    Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(
                                Profile.this,
                                "Unable to retrieve data: " + error.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                }
        );
        // Show the progress dialog
        progressDialog.show();

        // Add the request to the RequestQueue
        requestQueue1.add(getRequest2);
    }
}