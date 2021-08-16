package com.example.hello;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hello.result.history;
import com.example.hello.result.prescrption_image;
import com.example.hello.result.videocall;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edittext;      // 메세지 입력 뷰
    TextView textView;      // 작성 글자 수 나타내는 뷰
    Button button1;         // 작성 버튼
    Button button2;         // 끝남 버튼
    Button button3;
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // 뷰 지정
        edittext = findViewById(R.id.edittext);
        textView = findViewById(R.id.textView);
        button1 =  findViewById(R.id.btn1);
        button3 =  findViewById(R.id.btn3);



        // 디자인 정의
        button3 =  findViewById(R.id.btn3);
        imageView =  findViewById(R.id.imageView1);
        button3.setOnClickListener(this);




        edittext.addTextChangedListener(new TextWatcher() {
            // addTextChangedListener 텍스트가 입력에 따라 변경될 때마다 확인하는 기능
            // TextWatcher 텍스트가 변경될 때마다 발생하는 이벤트 처리하는 인터페이스
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                InputFilter[] filter = new InputFilter[1];
                filter[0] = new InputFilter.LengthFilter(500);
                // 쓸 수 있는 글자 수 최대 500자로 제한
                edittext.setFilters(filter);

                int currentBytes = s.toString().getBytes().length;        // 텍스트 내용을 받아와서 바이트 수를 가져온다.
                String txt = currentBytes + " / 500 바이트";
                textView.setText(txt);                                           // 텍스트뷰에 현재 바이트수 표시
            }

            public void afterTextChanged(Editable s) {
            }
        });
    }



    @SuppressLint("NonConstantResourceId")
    public void onClick(View view) {
        ///버튼 ID호출
        switch (view.getId()) {
            case R.id.btn1:
                //btn1을 눌렀을 때의 이벤트 처리(화상진료)

                Button imageButton =  button1;
                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), videocall.class);
                        startActivity(intent);
                                                   }


                });
                break;


            case R.id.btn3:
                //btn3을 눌렀을 때의 이벤트 처리(증상을 보여주기 위해 사진촬영)
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(i, 0);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 카메라 촬영을 하면 이미지뷰에 사진 삽입
        if (requestCode == 0 && resultCode == RESULT_OK) {
            // Bundle로 데이터를 입력
         Bundle extras = data.getExtras();

         // Bitmap으로 컨버전
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            // 이미지뷰에 Bitmap으로 이미지를 입력
            imageView.setImageBitmap(imageBitmap);


    }

}


}
