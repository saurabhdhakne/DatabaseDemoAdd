package com.example.acer.vaibhavdatabase;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button Breg, Blogin, Bcancel, B4;
    EditText euser, epass;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openHelper = new DatabaseHelper(this);
        Breg = (Button) findViewById(R.id.btnReg);
        Blogin = (Button) findViewById(R.id.btnLogin);
        Bcancel = (Button) findViewById(R.id.btnCancel);
        euser = (EditText) findViewById(R.id.txtUser);
        epass = (EditText) findViewById(R.id.txtPass);
    }

    public void insertData(View v) {
        db = openHelper.getWritableDatabase();
        String _euser, _epss;

        ContentValues contentValues = new ContentValues();
        _euser = euser.getText().toString();
        _epss = epass.getText().toString();
        contentValues.put(DatabaseHelper.COL_2, _euser);
        contentValues.put(DatabaseHelper.COL_3, _epss);
        long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
        Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();
    }


    public void login(View v) {
        db = openHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        String _euser, _epss;

        _euser = euser.getText().toString();
        _epss = epass.getText().toString();
        cursor = db.rawQuery(String.format(" SELECT * FROM %s WHERE %s =? AND %s = ? ", DatabaseHelper.TABLE_NAME, DatabaseHelper.COL_2, DatabaseHelper.COL_3), new String[]{_euser, _epss});
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToNext();
                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
            }

        }

    }
}
