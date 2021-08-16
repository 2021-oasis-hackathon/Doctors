package com.example.hello.result;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hello.R;

public class benefits extends AppCompatActivity implements View.OnClickListener {


        TextView text;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_benefits);
            text = (TextView) findViewById(R.id.textView12);
            text.setOnClickListener((View.OnClickListener) this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.textView12:

                    TextView imageButton =  findViewById(R.id.textView12);
                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getApplicationContext(), benefit_detail.class);
                            startActivity(intent);
                        }


                    });




                    // TextView가 클릭될 시 할 코드작성
                    break;
            }

        }


    }
