package org.techtown.oasis;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment17 extends Fragment {
    Button callButton;  // 전화 걸기 버튼
    Button photoButton;     // 촬영 버튼
    String name;  // 의사 이름
    EditText editText;      // 메세지 입력 뷰
    TextView byteNumber;  // 작성 글자 수 나타내는 뷰
    ImageView imageView1;
    private static final int Image_Capture_Code2 = 2;

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Capture_Code2) {
            if (resultCode == Activity.RESULT_OK) {
                Bitmap bp = (Bitmap) data.getExtras().get("data");
                imageView1.setImageBitmap(bp);
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getContext(), "Cancelled", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_17, container, false);

        Bundle bundle = getArguments();  // getArguments() 메소드로 번들 받기
        name = bundle.getString("Name");

        // 뷰 지정
        editText = rootView.findViewById(R.id.editText);
        byteNumber = rootView.findViewById(R.id.bytenumber);
        callButton = rootView.findViewById(R.id.callButton);
        photoButton = rootView.findViewById(R.id.photoButton);
        imageView1 = rootView.findViewById(R.id.imageView1);

        // 텍스트가 입력에 의해 변경될 때마다 확인
        editText.addTextChangedListener(new TextWatcher() {  // TextWatcher: 텍스트가 변경될 때마다 발생하는 이벤트 처리

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                InputFilter[] filter = new InputFilter[1];
                filter[0] = new InputFilter.LengthFilter(500);
                // 쓸 수 있는 글자 수 최대 500자로 제한
                editText.setFilters(filter);

                int currentBytes = s.toString().getBytes().length; // 텍스트 내용을 받아와서 바이트 수를 가져온다.
                String txt = currentBytes + " / 500 바이트";
                byteNumber.setText(txt);  // 텍스트뷰에 현재 바이트수 표시
            }

            public void afterTextChanged(Editable s) {
            }
        });

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name.equals("박기범")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:061-542-7727"));
                    startActivity(intent);
                }
                else if (name.equals("이민수")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:1577-0083"));
                    startActivity(intent);
                }

                FragmentPrescription fragmentPrescription = new FragmentPrescription();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragmentPrescription);
                transaction.commit();
            }
        });

        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,Image_Capture_Code2);
            }
        });

        org.techtown.oasis.OnTabItemSelectedListener listener;

        return rootView;
    }
}