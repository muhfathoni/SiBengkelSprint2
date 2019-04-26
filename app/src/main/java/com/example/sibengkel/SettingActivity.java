package com.example.sibengkel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sibengkel.utils.DatabaseHelper;

import java.util.Set;

public class SettingActivity extends AppCompatActivity {

    DatabaseHelper mydb;
    EditText txtNama, txtEmail, txtNoHp, txtAlamat, txtPassword;
    Button buttonSaveProfile, buttonLogout;
    SharedPreferences sharedpreferences;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_fragment);
        mydb = new DatabaseHelper(this);

        txtNama = (EditText)findViewById(R.id.txtNama);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtNoHp = (EditText)findViewById(R.id.txtNoHp);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        buttonSaveProfile = (Button) findViewById(R.id.buttonSaveProfile);
        buttonLogout = (Button) findViewById(R.id.button_logout);

        buttonLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                logout();
            }
        });

//
//        Cursor res = mydb.getUserData();
//        if (res.getCount() == 0){
//            return;
//        }
//
//        StringBuffer buffer = new StringBuffer();
//        while (res.moveToNext()){
//            buffer.append("id :"+ res.getString(0));
//            buffer.append("name :"+ res.getString(1));
//            buffer.append("email :"+ res.getString(2));
//            buffer.append("password :"+ res.getString(3));
//            buffer.append("phone :"+ res.getString(4));
//            buffer.append("address :"+ res.getString(5));
//        }

//

    }

    public void showMessage(String tittle, String Message){

    }

        private void logout() {
        sharedpreferences = getSharedPreferences(
                LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(LoginActivity.session_status, false);
        editor.putString(LoginActivity.TAG_ID, null);
        editor.putString(LoginActivity.TAG_EMAIL, null);
        editor.putString(LoginActivity.TAG_NAME, null);
        editor.putString(LoginActivity.TAG_PHONE, null);
        editor.putString(LoginActivity.TAG_ADDRESS, null);
        editor.commit();

        intent = new Intent(SettingActivity.this, LoginActivity.class);
        finish();
        startActivity(intent);
    }
}

