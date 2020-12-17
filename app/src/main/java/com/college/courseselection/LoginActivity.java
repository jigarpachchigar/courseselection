package com.college.courseselection;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText eun,euid,epwd;
    Button btnlog,btnclr;
    TextView tv;
    Integer cnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eun = (EditText) findViewById(R.id.etsname);
        euid = (EditText) findViewById(R.id.etsuid);
        epwd = (EditText) findViewById(R.id.etupwd);

        btnlog = (Button) findViewById(R.id.btnlogin);
        btnclr = (Button) findViewById(R.id.btnclear);

        btnlog.setOnClickListener(this);

        btnclr.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if  (v  == btnlog)
        {

            String struname = eun.getText().toString();
            String struid = euid.getText().toString();
            String strpw = epwd.getText().toString();

            boolean blun = struid.equals(" ");
            boolean bluid = struid.equals("student1");
            boolean blpwd = strpw.equals("123456");

            if( bluid && blpwd && blun==false ) {


                 if ( struname.matches(" ")) {
                     eun.setError("Enter Student Name");
                     return;

                 }else {
                     Intent inlog = new Intent(getApplicationContext(),
                             MainActivity.class);
                     inlog.putExtra("name", struname);
                     cnt = 0;
                     startActivity(inlog);
                 }


            }


            else if( TextUtils.isEmpty(struname) || TextUtils.isEmpty(struid) || TextUtils.isEmpty(strpw)){

                eun.setError("Enter Student Name");
                epwd.setError("Enter Password");
                euid.setError("Enter Username");

                eun.setText(" ");
                euid.setText("");
                epwd.setText("");

                eun.requestFocus();
                return;

            }


            else if(bluid == false){

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Invalid UserID")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

                cnt++;
                euid.setText("");

            }
            else if (blpwd == false) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Invalid Password")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                cnt++;
                epwd.setText("");
                epwd.requestFocus();
            }

            else
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Enter Student Name")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things
                            }
                        });
            }


        }

        if(v == btnclr)
        {
            eun.setText("");
            euid.setText("");
            epwd.setText("");
            eun.requestFocus();
        }

    }
    }

