package com.example.weatht;

import static com.example.weatht.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText et;

    Button button;
    TextView tv3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

        requestWindowFeature (Window.FEATURE_NO_TITLE);
        this.getWindow ( ).setFlags (WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar ( ).hide ( );
        setContentView (layout.activity_main);
        et = findViewById (R.id.editTextTextPersonName2);
        button = findViewById (id.button);


        tv3 = findViewById (id.textviewheader);


    }

    public void get(View v) {
        String apikey = "8538597e06760eb0d28a67dc989de09d";
        String city = et.getText ( ).toString ( );
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=8538597e06760eb0d28a67dc989de09d";
        RequestQueue queue = Volley.newRequestQueue (getApplicationContext ( ));
        JsonObjectRequest request = new JsonObjectRequest (Request.Method.GET, url, null, new Response.Listener<JSONObject> ( ) {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject object = response.getJSONObject ("main");
                    String temperature = object.getString ("temp");

                    float pressure = object.getInt ("pressure");
                    Double tempp = Double.parseDouble (temperature) - 273.15;
                    String humidity = object.getString ("humidity");
                    tv3.setText ("temp: " + tempp.toString ( ).substring (0, 5) + " celcious " +

                            "\n\n humidity: " + humidity + "%" +
                            "\n\n pressure: " + pressure + "hpa");


                } catch (JSONException e) {
                    Toast.makeText (getApplicationContext ( ), e.getMessage ( ), Toast.LENGTH_LONG).show ( );
                }
            }
        }, new Response.ErrorListener ( ) {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText (MainActivity.this, "error toString()", Toast.LENGTH_SHORT).show ( );
            }
        });
        queue.add (request);
    }


}