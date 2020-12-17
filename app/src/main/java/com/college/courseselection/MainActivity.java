package com.college.courseselection;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    String course[]={"Java","Swift","Ios","Android","Database"};
    ArrayList<Course> courseList = new ArrayList<>();
    ArrayList<String> tempCourseNames = new ArrayList<>();


    Spinner spco;
    Button btnadd;
    RadioButton rdb;
    CheckBox chbacc,chbmed;
    TextView tvfee,tvhour,tvtotfee,tvtothour,tverr;
    RadioGroup rdg;

    public static double reset = 0;

    public static double originalPrice =0;
    public static double totalfees =0;
    public static double totalhours =0;
    double totfee,tothour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fill the data of all courses

        fillData();
        fillTempCourse(course[0]);

        spco=findViewById(R.id.spcourse);
        btnadd=findViewById(R.id.btnaddcourse);
       // rdbgrad=findViewById(R.id.rbgrad);
       // rdbungrad=findViewById(R.id.rbungrad);
        chbacc=findViewById(R.id.cbaccod);
        chbmed=findViewById(R.id.cbmedical);
        tvfee=findViewById(R.id.tvcofee);
        tvhour=findViewById(R.id.tvchours);
        tvtothour=findViewById(R.id.tvtothour);
        tvtotfee=findViewById(R.id.tvtotfee);
        tvtotfee.setPaintFlags(tvtotfee.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        rdg = findViewById(R.id.radioGroup);
        rdg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                totfee = 0;
                tothour = 0;
                chbacc.setChecked(false);
                chbmed.setChecked(false);
                tvtothour.setText(String.valueOf(reset));
                tvtotfee.setText(String.valueOf(reset));
            }
        });


        tverr=findViewById(R.id.txterror);
        tverr.setText("Graduate Student Can Select Maximum Upto 21 Hours ");

        Intent inlog = getIntent();
        Bundle b = inlog.getExtras();
        String uid = b.getString("name");
        TextView tv = (TextView) findViewById(R.id.tvuname);
        tv.setText("Welcome "+uid + " For Course Selection");

        ArrayAdapter aa=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,course);
        spco.setAdapter(aa);
        tvfee.setText(String.valueOf(courseList.get(0).getCoursefee()));
        tvhour.setText(String.valueOf(courseList.get(0).getCoursehour()));

        spco.setOnItemSelectedListener(this);

        //rdbgrad.setOnClickListener(new RadioButtonsAction());
        //rdbungrad.setOnClickListener(new RadioButtonsAction());

        btnadd.setOnClickListener(this);


        chbacc.setOnCheckedChangeListener(new CheckBoxActions());
        chbmed.setOnCheckedChangeListener(new CheckBoxActions());

    }

    //this method is filling the temporary array list of dish names upon the cuisine
    public void fillTempCourse(String csname){
        for(int i=0;i<courseList.size();i++)
            if(csname.equals(courseList.get(i).getStdcourse()))
                tempCourseNames.add(courseList.get(i).getCoursename());

    }

    private void fillData() {

        courseList.add(new Course("Java",1300,6,course[0]));
        courseList.add(new Course("Swift",1500,5,course[1]));
        courseList.add(new Course("Ios",1350,5,course[2]));
        courseList.add(new Course("Android",1400,7,course[3]));
        courseList.add(new Course("Database",1000,4,course[4]));

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        tempCourseNames.clear();
        fillTempCourse(course[position]);
        //Course obj = getCourseObj(tempCourseNames.get(position));
        tvfee.setText(String.valueOf(courseList.get(position).getCoursefee()));
        tvhour.setText(String.valueOf(courseList.get(position).getCoursehour()));

        totalfees = Double.parseDouble(tvfee.getText().toString());
        totalhours = Double.parseDouble(tvhour.getText().toString());

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onClick(View v) {



        //  checkrdbtn(v);
        if (v == btnadd) {

            double currentfees = Double.parseDouble(tvfee.getText().toString());
            double currenthour = Double.parseDouble(tvhour.getText().toString());

            int selectedId = rdg.getCheckedRadioButtonId();
           //  Log.i("RID", String.valueOf(selectedId));
            //Log.i("RIDD", "grad");
           // double th = Double.parseDouble(tvtothour.getText().toString());

            //tothour = Double.parseDouble(tvtothour.getText().toString());

           if(selectedId == 2131230985) {

               tverr.setText("Graduate Student Can Select Maximum Upto 21 Hours ");

               if (tothour < 21) {

                   totfee += currentfees;
                   tothour += currenthour;

                   if (tothour > 21) {

                       totfee -= currentfees;
                       tothour -= currenthour;

                       tvtotfee.setText(String.valueOf(totfee));
                       tvtothour.setText(String.valueOf(tothour));

                       AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                       builder.setMessage("Sorry ! You Can't Add This Course")
                               .setCancelable(false)
                               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                   public void onClick(DialogInterface dialog, int id) {
                                       //do things
                                   }
                               });
                       AlertDialog alert = builder.create();
                       alert.show();
                   }
                   else
                   {
                       tvtotfee.setText(String.valueOf(totfee));
                       tvtothour.setText(String.valueOf(tothour));

                   }

               } else   {


                   AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                   builder.setMessage("Sorry ! You Can't Add This Course")
                           .setCancelable(false)
                           .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                               public void onClick(DialogInterface dialog, int id) {
                                   //do things
                               }
                           });
                   AlertDialog alert = builder.create();
                   alert.show();


               }
           }

            else if(selectedId == 2131230986) {

               tverr.setText("*Graduate Student Can Select Maximum Upto 21 Hours\n*Undergraduate Student Can Select Maximum Upto 19 Hours ");

               if (tothour < 19) {

                    totfee += currentfees;
                    tothour += currenthour;

                    if (tothour > 21) {

                        totfee -= currentfees;
                        tothour -= currenthour;

                        tvtotfee.setText(String.valueOf(totfee));
                        tvtothour.setText(String.valueOf(tothour));

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Sorry ! You Can't Add This Course")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //do things
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                    else
                    {
                        tvtotfee.setText(String.valueOf(totfee));
                        tvtothour.setText(String.valueOf(tothour));

                    }
                }

                else   {

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Sorry ! You Can't Add This Course")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //do things
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();

                }

            }


        }
        }



    private class CheckBoxActions implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            double currentfees = Double.parseDouble(tvtotfee.getText().toString());

            if(buttonView.getId()==R.id.cbaccod)
                if(chbacc.isChecked())
                    currentfees+=1000;
                else
                    currentfees-=1000;

            if(buttonView.getId()==R.id.cbmedical)
                if(chbmed.isChecked())
                    currentfees+=700;
                else
                    currentfees-=700;

            tvtotfee.setText(String.valueOf(currentfees));



        }
    }
}