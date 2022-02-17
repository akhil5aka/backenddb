package com.example.course;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText e1,e2,e3,e4,e5;
    AppCompatButton b1;
    String gettitle,getdiscrip,getve,getdt,getdu;
    String apiUrl="https://mountzioncollege.herokuapp.com/addcourse";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText) findViewById(R.id.ct);
        e2=(EditText) findViewById(R.id.cd);
        e3=(EditText) findViewById(R.id.ve);
        e4=(EditText) findViewById(R.id.dt);
        e5=(EditText) findViewById(R.id.du);
        b1=(AppCompatButton) findViewById(R.id.sub);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gettitle=e1.getText().toString();
                getdiscrip=e2.getText().toString();
                getve=e3.getText().toString();
                getdt=e4.getText().toString();
                getdu=e5.getText().toString();
                StringRequest sr=new StringRequest(Request.Method.POST,
                        apiUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        })
                {


                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        HashMap<String,String> params=new HashMap<>();
                        params.put("courseDuration",getdu);
                        params.put("courseTitle",gettitle);
                        params.put("courseVenue",getve);
                        params.put("courseDate",getdt);
                        params.put("courseDescription",getdiscrip);
                        return params;
                    }
                };
                RequestQueue rq= Volley.newRequestQueue(getApplicationContext());
                rq.add(sr);


            }
        });
    }
}