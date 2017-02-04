package com.luti.mylogin.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.luti.mylogin.R;
import com.luti.mylogin.Utils.Util;

public class LoginActivity extends AppCompatActivity {


    //declaracion de las sharedpreference
    private SharedPreferences prefs;


    private EditText editTextEmail;
    private EditText editTextPassword;
    private Switch swichRemember;
    private Button buttonLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindUI();
        //declaracion de las preferencias.
        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        setCredentialsIfExist();
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                if (login(email, password)) {
                    goToMain();
                    saveOfPreferences(email, password);
                }
            }
        });
    }


    //Elementos de la interface
    private void bindUI(){
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        swichRemember = (Switch) findViewById(R.id.switchRemember);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
    }

    private boolean login(String mail, String password){
        if(!isValidEmail(mail)){
            Toast.makeText(this, "Email is not valid, please try again", Toast.LENGTH_LONG).show();
            return false;
        }else if(!isValidPassword(password)){
            Toast.makeText(this, "Password is not valid, 4 characters or more,  please try again", Toast.LENGTH_LONG).show();
            return false;
        }else{
            return true;
        }
    }

    //para comprobar que el mail es correcto
    private boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword (String password){
        return password.length() >= 4;
    }

    private void goToMain(){
        Intent intent = new Intent(this, MainActivity.class);

        //Esto es para que no se vaya de nuevo al login una vez logado, si no que se salga de la pantalla.
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    //Para guardar lo que vamos a guardar como preferencias.
    private void saveOfPreferences(String mail, String password){
        if (swichRemember.isChecked()){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("email", mail);
            editor.putString("pass", password);
            //editor.commit();
            editor.apply();
        }


    }

    private void setCredentialsIfExist(){
        String email = Util.getUserMailPrefs(prefs);
        String pass = Util.getPassPrefs(prefs);
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)){
            editTextEmail.setText(email);
            editTextPassword.setText(pass);
            swichRemember.setChecked(true);
        }
    }


}
