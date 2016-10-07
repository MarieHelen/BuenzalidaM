package com.example.localad.buenzalidam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.String;

public class SignUp extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        Context context = this;
        //get Instance of DataBase Adapter
        final DataBaseAdapter db = new DataBaseAdapter(this);
        db.open();

        //get reference views
        final EditText EmailSignUp = (EditText) findViewById(R.id.EmailReg);
        final EditText PasswordSignUp = (EditText) findViewById(R.id.PasswordReg);
        final EditText PasswordCon = (EditText) findViewById(R.id.PasswordConfirm);

        final EditText Firstname = (EditText) findViewById(R.id.FirstName);
        final EditText Lastname = (EditText) findViewById(R.id.LastName);
        final EditText Username = (EditText) findViewById(R.id.UsernameReg);

        final Button bReg = (Button) findViewById(R.id.bReg);

        bReg.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String Email = EmailSignUp.getText().toString();
                String Password = PasswordSignUp.getText().toString();
                String confirmPassword = PasswordCon.getText().toString();
                String TakenUsername = db.getData();
                String FN = Firstname.getText().toString();
                String LN = Lastname.getText().toString();
                String UN = Username.getText().toString();

                //check if any of the field are vacant
                if (Email.equals("") ||UN.equals("") || Password.equals("")
                        || confirmPassword.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fill-Up required fields", Toast.LENGTH_LONG).show();
                    return;
                }

                // Check if both passwords matches
                if (!Password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(), "Password does not match",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if (UN.equals(TakenUsername)) {
                    Toast.makeText(getApplicationContext(), "Username Already Taken",
                            Toast.LENGTH_LONG).show();
                    return;
                }
               /* else {
                   // Save data in database
                   db.insertData(UN, Password, Email);
                   Toast.makeText(getApplicationContext(), "Account Successfully Created ",
                           Toast.LENGTH_LONG).show();
                   Intent i = new Intent(SignUp.this, NextScreen.class);
                   startActivity(i);
               }*/

               /* if (!PwSignUp.equals(ConPwSignUp.getText().toString())) {
                    ConPwSignUp.setError("Password does not match!");
                }*/
                else  if (!validateName(Firstname.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Invalid First Name", Toast.LENGTH_LONG).show();
                    //Firstname.setError("Invalid First Name");
                }
                else if (!validateName(Lastname.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Invalid Last Name", Toast.LENGTH_LONG).show();
                    //Lastname.setError("Invalid Last Name");
                }
                else  if (!validateUsername(Username.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Invalid Username", Toast.LENGTH_LONG).show();
                    //Username.setError("Invalid Username");
                }
                else if (!validateEmail(EmailSignUp.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_LONG).show();
                    //EmailSignUp.setError("Invalid Email");
                }
                else if (!validatePassword(PasswordSignUp.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Invalid Password", Toast.LENGTH_LONG).show();
                    //PasswordSignUp.setError("Invalid Password!");
                }


                else {
                    boolean login = db.registerUser(FN,LN,UN,Email,Password,GetCurrentDateAndTime());

                    if (login == true) {
                        Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(SignUp.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                }

            }
        });
    }

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static final String Name = "^[a-zA-Z ]+$";
    private Pattern pattern1 = Pattern.compile(Name);
    private Matcher matcher1;

    public boolean validateName(String name) {
        matcher1 = pattern1.matcher(name);
        return matcher1.matches();
    }

    private static final String USERNAME = "^[a-z0-9_-]{3,15}$";
    private Pattern pattern2 = Pattern.compile(USERNAME);
    private Matcher matcher2;

    public boolean validateUsername(String username) {
        matcher2 = pattern2.matcher(username);
        return matcher2.matches();
    }

    public boolean validatePassword(String pass) {
        return pass.length() >=6;
    }

    public String GetCurrentDateAndTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //get current date time with Date()
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        return dateFormat.format(date).toString();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

    }

}

