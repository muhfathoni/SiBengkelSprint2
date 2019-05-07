package com.example.sibengkel;

import android.content.ContentValues;
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
import android.widget.Toast;

import com.example.sibengkel.controllers.BookController;
import com.example.sibengkel.controllers.UserController;
import com.example.sibengkel.utils.Database;
import com.example.sibengkel.utils.DatabaseHelper;

import java.util.Set;

public class SettingActivity extends AppCompatActivity {

    DatabaseHelper mydb;
    EditText txtNama, txtEmail, txtNoHp, txtAlamat, txtPassword;
    Button buttonSaveProfile, buttonLogout;
    SharedPreferences sharedpreferences;
    Intent intent;
    ContentValues content;

    public final static String TAG_EMAIL = "email";

    public static final String my_shared_preferences = "my_shared_preferences";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_fragment);
        mydb = new DatabaseHelper(this);

        initiateCoreApp();

        txtNama = (EditText)findViewById(R.id.txtNama);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtNoHp = (EditText)findViewById(R.id.txtNoHp);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        txtAlamat = (EditText)findViewById(R.id.txtAlamat);
        buttonSaveProfile = (Button) findViewById(R.id.buttonSaveProfile);
        buttonLogout = (Button) findViewById(R.id.button_logout);

        buttonLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                logout();
            }
        });



        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        String email = sharedpreferences.getString(TAG_EMAIL, null);
        content = UserController.getInstance().getDataByEmail(email);

        txtNama.setText(content.getAsString("name"));
        txtEmail.setText(content.getAsString("email"));
        txtPassword.setText(content.getAsString("password"));
        txtNoHp.setText(content.getAsString("phone"));
        txtAlamat.setText(content.getAsString("address"));

        buttonSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues c = new ContentValues();
                c.put("_id",content.getAsString("_id"));
                c.put("email", txtEmail.getText().toString());
                c.put("name", txtNama.getText().toString());
                c.put("password", txtPassword.getText().toString());
                c.put("phone", txtNoHp.getText().toString());
                c.put("address", txtAlamat.getText().toString());

                mydb.update("tbl_users",c);
                Toast.makeText(SettingActivity.this, "Profile Updated",Toast.LENGTH_SHORT).show();
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

    private void initiateCoreApp() {
        Database database = new DatabaseHelper(this);
        BookController.setDatabase(database);
    }
}

