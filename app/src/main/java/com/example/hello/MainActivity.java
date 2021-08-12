package com.example.hello;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    EditText edittext;      // 메세지 입력 뷰
    TextView textView;      // 작성 글자 수 나타내는 뷰
    Button button1;         // 작성 버튼
    Button button2;         // 끝남 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 뷰 지정
        edittext = (EditText) findViewById(R.id.edittext);
        textView = (TextView) findViewById(R.id.textView);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        edittext.addTextChangedListener(new TextWatcher() {
            // addTextChangedListener 텍스트가 입력에 따라 변경될 때마다 확인하는 기능
            // TextWatcher 텍스트가 변경될 때마다 발생하는 이벤트 처리하는 인터페이스
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                InputFilter[] filter = new InputFilter[1];
                filter[0] = new InputFilter.LengthFilter(500);
                // 쓸 수 있는 글자 수 최대 500자로 제한
                edittext.setFilters(filter);

                int currentBytes = s.toString().getBytes().length;        // 텍스트 내용을 받아와서 바이트 수를 가져온다.
                String txt = String.valueOf(currentBytes) + " / 500 바이트";
                textView.setText(txt);                                           // 텍스트뷰에 현재 바이트수 표시
            }

            public void afterTextChanged(Editable s) {}
        });
    }

    public void onButton1Clicked(View v) {
        Toast.makeText(getApplicationContext(), (CharSequence) edittext.getText(), Toast.LENGTH_LONG).show();
    }   // 전송 버튼을 클릭하면 작성된 메시지를 토스트로 띄어준다.
    // editText.getText()의 반환형은 editable 이므로 CharSequence 타입으로 형변환 해준다.

    public void onButton2Clicked(View v) {
        finish();
    }
}