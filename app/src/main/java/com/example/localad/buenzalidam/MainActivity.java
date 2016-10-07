package com.example.localad.buenzalidam;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bLogin = (Button) findViewById(R.id.bLogin);
        final Button bShow = (Button) findViewById(R.id.bShow);
        final EditText Username = (EditText) findViewById(R.id.etUserEmail);
        final EditText Password = (EditText) findViewById(R.id.etPassword);
        final EditText Email = (EditText) findViewById(R.id.etUserEmail);
        final Button bRegister = (Button) findViewById(R.id.bRegister);

        Context context = this;
        final DataBaseAdapter db = new DataBaseAdapter(context);

        bShow.setOnTouchListener(new View.OnTouchListener() {
            @Override

            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        Password.setTransformationMethod(null);
                        Password.setSelection(Password.getText().length());
                        return true;
                    }
                    case MotionEvent.ACTION_UP: {
                        Password.setTransformationMethod(new PasswordTransformationMethod());
                        Password.setSelection(Password.getText().length());
                        return true;
                    }
                }
                return false;
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (Email.equals("") || Password.equals("")){
                    Toast.makeText(getApplicationContext(), "Fill-Up Required Fields", Toast.LENGTH_LONG).show();
                    return;
                }
                if(!validateEmail(Email.getText().toString())){
                    //Email.setError("Invalid Username or Email!");
                    Toast.makeText(getApplication(), "Invalid Username or Email", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!validateEmail(Username.getText().toString())) {
                    //Username.setError("Invalid Username or Email!");
                    Toast.makeText(getApplication(), "Invalid Username or Email", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!validatePassword(Password.getText().toString())) {
                    //Password.setError("Not a valid password!");
                    Toast.makeText(getApplication(), "Invalid Password", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    //boolean check = db.validateUser(Email.getText().toString(),/*Username.getText().toString(),*/Pw.getText().toString());

                    //if (check==true) {
                    //   Toast.makeText(getApplicationContext(), "Invalid Username or Email", Toast.LENGTH_LONG).show();
                    // return;

                    // }
                    // else {
                    Email.setError(null);
                    Password.setError(null);
                    dobLogin();
                    //}
                }
            }
        });

        bRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this,SignUp.class);
                startActivity(i);
            }
        });
    }
        private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
        private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        private Matcher matcher;

        public boolean validateEmail(String Email){
            matcher = pattern.matcher(Email);
            return matcher.matches();
        }

        private static final String USERNAME = "^[a-zA-Z0-9_-]{3,15}$";
        private Pattern pattern1 = Pattern.compile(USERNAME);
        private Matcher matcher1;

        public boolean validateUsername(String username) {
        matcher1 = pattern1.matcher(username);
        return matcher1.matches();
        }

    public boolean validatePassword(String etPassword) {return etPassword.length() >=6; }

        public void dobLogin() {
            Toast.makeText(getApplicationContext(), "Successfully LOG-IN!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, NextScreen.class);

            startActivity(intent);
        }

        protected void onPause() {
            super.onPause();
            finish();

           /* float A =0;
            float AA = 0;
            float B = 0;
            float BB = 0;

            public boolean OnTouch(View view, MotionEvent motionEvent) {
                int event1 = motionEvent.getAction();

                switch(event1) {
                    case MotionEvent.ACTION_DOWN:
                        A = motionEvent.getX();
                        B = motionEvent.getY();
                        Toast.makeText(MainActivity.this, "Coordinate X: " +A+ " Coordinate Y: "+ B, 	Toast.LENGTH_SHORT).show();

                        break;

                    case MotionEvent.ACTION_UP:
                        AA= motionEvent.getX();
                        BB = motionEvent.getY();

                        if(A > AA) {
                            Toast.makeText(MainActivity.this, "Coordinate X2: " +AA+ " Coordinate Y2: "+ BB + "Swiped RIGHT?", Toast.LENGTH_SHORT).show();
                        }

                        if(B > BB) {
                            Toast.makeText(MainActivity.this, "Coordinate X2: " +AA+ " Coordinate Y2: "+ BB  + "Swiped UP?", Toast.LENGTH_SHORT).show();
                        }

                        if (B < BB){
                            Toast.makeText(MainActivity.this, "Coordinate X2: " +AA+ " Coordinate Y2: "+ BB + "Swiped Down?", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                return true;
            }
        }); */
    }
}
