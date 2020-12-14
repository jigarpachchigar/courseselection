package com.college.courseselection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    String course[]={"Java","Swift","Ios","Android","Database"};
    ArrayList<Course> courseList = new ArrayList<>();
    ArrayList<String> tempCourseNames = new ArrayList<>();


    Spinner spco;
    Button btnadd;
    RadioButton rdbgrad,rdbungrad;
    CheckBox chbacc,chbmed;
    TextView tvfee,tvhour,tvtotfee,tvtothour,tverr;

    public static double originalPrice =0;
    public static double totalfees =0;
    public static double totalhours =0;
    double totfee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fill the data of all courses

        fillData();
        fillTempCourse(course[0]);

        spco=findViewById(R.id.spcourse);
        btnadd=findViewById(R.id.btnaddcourse);
        rdbgrad=findViewById(R.id.rbgrad);
        rdbungrad=findViewById(R.id.rbungrad);
        chbacc=findViewById(R.id.cbaccod);
        chbmed=findViewById(R.id.cbmedical);
        tvfee=findViewById(R.id.tvcofee);
        tvhour=findViewById(R.id.tvchours);
        tvtothour=findViewById(R.id.tvtothour);
        tvtotfee=findViewById(R.id.tvtotfee);

        tverr=findViewById(R.id.txterror);

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

        rdbgrad.setOnClickListener(new RadioButtonsAction());
        rdbungrad.setOnClickListener(new RadioButtonsAction());

        btnadd.setOnClickListener(this);


    }

    //this method is filling the temporary array list of dish names upon the cuisine
    public void fillTempCourse(String csname){
        for(int i=0;i<courseList.size();i++)
            if(csname.equals(courseList.get(i).getStdcourse()))
                tempCourseNames.add(courseList.get(i).getCoursename());

    }

    private void fillData() {

        courseList.add(new Course("Java",800,25,course[0]));
        courseList.add(new Course("Swift",700,30,course[1]));
        courseList.add(new Course("Ios",1000,45,course[2]));
        courseList.add(new Course("Android",1200,55,course[3]));
        courseList.add(new Course("Database",900,20,course[4]));

    }


    private Course getCourseObj(String coursename) {

        for(int i=0;i<courseList.size();i++)
        {
            if(coursename.equals(courseList.get(i).getCoursename()))
                return courseList.get(i);
        }
        return null;
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

        if (v == btnadd)
        {
            double currentfees = Double.parseDouble(tvfee.getText().toString());
            double currenthour = Double.parseDouble(tvhour.getText().toString());


            if(v.getId()==R.id.rbgrad)
            {

                if (totfee<=21) {
                    totfee += currentfees;
                    tvtotfee.setText(String.valueOf(totfee));
                }
                else
                {
                    tverr.setText("Sorry");
                }
            }


           /* if(rdbgrad.isSelected())
            {
                if(currenthour <= 21)
                {
                    tvtotfee.setText(String.valueOf(currentfees));
                    tvtothour.setText(String.valueOf(currenthour));
                }
                else
                {
                    tverr.setText("Sorry!Can't Select");
                }
            }

            else if (rdbungrad.isSelected())
            {
                if(currenthour <= 19 )
                {
                    tvtotfee.setText(String.valueOf(currentfees));
                    tvtothour.setText(String.valueOf(currenthour));
                }
                else
                {
                    tverr.setText("Sorry!Can't Select");
                }
            }*/


        }

    }

    private class RadioButtonsAction implements View.OnClickListener{

        @Override
        public void onClick(View view) {



           // else if(view.getId()==R.id.rbungrad)
              //  price.setText(String.format("%.2f",originalPrice*1.5));*/




        }
    }

}