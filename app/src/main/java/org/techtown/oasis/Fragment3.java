package org.techtown.oasis;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Fragment3 extends Fragment {

    Context context;
    Button btnNext1;

    org.techtown.oasis.OnTabItemSelectedListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

        if (context instanceof org.techtown.oasis.OnTabItemSelectedListener) {
            listener = (org.techtown.oasis.OnTabItemSelectedListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (context != null) {
            context = null;
            listener = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_3, container, false);

        initUI(rootView);

        return rootView;
    }

    private void initUI(ViewGroup rootView) {
        EditText editText1;
        Spinner spinner1, spinner2;
        String[] items1 = {"40kg 미만", "40~50kg", "50~60kg", "70~80kg", "80~90kg", "90~100kg", "100kg 초과"};
        String[] items2 = {"140cm 미만", "140~150cm", "150~160cm", "160~170cm", "170~180cm", "180~190cm", "190cm 초과"};
        spinner1 = rootView.findViewById(R.id.spinner2);
        spinner2 = rootView.findViewById(R.id.addDiseaseBtn);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, items1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, items2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnNext1 = rootView.findViewById(R.id.btnNext1);
        btnNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // fragment_health_data로 전환
                FragmentHealthData fragmentHealthData = new FragmentHealthData();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragmentHealthData);
                transaction.commit();
            }
        });


        /*
        // 촬영 버튼
        btnCapture = rootView.findViewById(R.id.button1);
        imgCapture = rootView.findViewById(R.id.imageView);
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,Image_Capture_Code);
            }
        });

        // 저장 버튼
        btnSave = rootView.findViewById(R.id.btnNext1);
        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onTabSelected(0);  // fragment_2로 전환
                }
            }
        });
         */
    }
}