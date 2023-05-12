package be.kuleuven.gt.mealplannerfinal;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private static final String QUEUE_URL = "https://studev.groept.be/api/a22pt207/Users";
    private TextView enteredUsername;
    private TextView enteredPassword;
    private JSONArray credentialsList = new JSONArray();
    private Map<String, String> users = new HashMap<>();
    private String storedPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActivityBackgroundColor(Color.parseColor("#FFA500"));
        getSupportActionBar().hide();
        enteredUsername = (TextView) findViewById(R.id.txtUsername);
        enteredPassword = (TextView) findViewById(R.id.txtPassword);
        request();
    }
    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }
    public void onBtnLogin_Clicked(View Caller){

        if(validateCredentials(enteredUsername.getText().toString(), enteredPassword.getText().toString()))
        {
            Intent intent = new Intent(this, HomeScreen.class);
            startActivity(intent);
        }
        if(!validateCredentials(enteredUsername.getText().toString(), enteredPassword.getText().toString()))
        {
            Toast.makeText(
                    MainActivity.this,
                    "Invalid Credentials",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void onTxtSignUp_Clicked(View Caller)
    {
        //start an intent
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
    public void onTxtFP_Clicked(View Caller)
    {
        //start an intent
        Intent intent = new Intent(this, ForgotPassword.class);
        startActivity(intent);
    }
    /////////////////////////////////////////////////////////////////////////
    private void request() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest queueRequest = new JsonArrayRequest(
                Request.Method.GET,
                QUEUE_URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        credentialsList = response;
                        users = parseJsonToHashMap(response.toString());
                        System.out.println(users);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(
                                MainActivity.this,
                                "Unable to communicate with the server",
                                Toast.LENGTH_LONG).show();
                    }
                });
        requestQueue.add(queueRequest);
    }
    public static Map<String, String> parseJsonToHashMap(String jsonString) {
        Map<String, String> userMap = new HashMap<>();

        jsonString = jsonString.replaceAll("\\[|\\]", "");

        String[] userObjects = jsonString.split("\\},\\{");

        for (String userObject : userObjects) {
            String[] keyValuePairs = userObject.replace("{", "").replace("}", "").split(",");

            String username = null;
            String password = null;

            for (String keyValuePair : keyValuePairs) {
                String[] pair = keyValuePair.split(":");
                String key = pair[0].replace("\"", "").trim();
                String value = pair[1].replace("\"", "").trim();

                if (key.equals("username")) {
                    username = value;
                } else if (key.equals("password")) {
                    password = value;
                }
            }
            if (username != null && password != null) {
                userMap.put(username, password);
            }
        }

        return userMap;
    }
    public boolean validateCredentials(String username, String password) {
        if (users.containsKey(username)) {
            storedPassword = users.get(username);
            return storedPassword.equals(password);
        }
        return false;
    }

}