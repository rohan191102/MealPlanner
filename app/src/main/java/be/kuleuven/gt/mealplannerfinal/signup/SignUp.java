package be.kuleuven.gt.mealplannerfinal.signup;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import be.kuleuven.gt.mealplannerfinal.MainActivity;
import be.kuleuven.gt.mealplannerfinal.R;

public class SignUp extends AppCompatActivity {
    private TextView password;
    private TextView password1;
    private TextView username;
    private TextView firstname;
    private TextView lastname;
    private TextView email;
    private static final String POST_URL = "https://studev.groept.be/api/a22pt207/insertusers";
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        password = findViewById(R.id.editTxtPassword);
        password1 = findViewById(R.id.editTxtPasswordConfirm);
        username = findViewById(R.id.editTxtUsername);
        firstname = findViewById(R.id.editTxtFirstName);
        lastname = findViewById(R.id.editTxtLastName);
        email = findViewById(R.id.editTxtEmail);

        requestQueue = Volley.newRequestQueue(this);
    }
    public Map<String, String> getPostParameters() {
        Map<String, String> params = new HashMap<>();
        params.put("username", username.getText().toString());
        params.put("email", email.getText().toString());
        params.put("firstname", firstname.getText().toString());
        params.put("lastname", lastname.getText().toString());
        params.put("password", password.getText().toString());
        return params;
    }
    public void onBtnCreateAccount_Clicked(View Caller) {
        String user = username.getText().toString();
        String pass = password.getText().toString();
        String passConfirm = password1.getText().toString();
        String firstName = firstname.getText().toString();
        String lastName = lastname.getText().toString();
        String emailAddr = email.getText().toString();
        if (user.isEmpty() || pass.isEmpty() || passConfirm.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || emailAddr.isEmpty()) {
            Toast.makeText(SignUp.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!pass.equals(passConfirm)) {
            Toast.makeText(SignUp.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        String POST_URL2 = POST_URL + "/" +user+ "/" +emailAddr+ "/" +firstName +"/" +lastName+ "/" +pass;

        StringRequest submitRequest = new StringRequest(
                Request.Method.GET,
                POST_URL2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle the response if the post is successful
                        Toast.makeText(SignUp.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error if the post fails
                        Toast.makeText(SignUp.this, "Account creation failed", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                return getPostParameters();
            }
        };
        requestQueue.add(submitRequest);
    }
}