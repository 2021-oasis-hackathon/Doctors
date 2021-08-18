package org.techtown.oasis;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class FragmentHealthData2 extends Fragment {

    Context context;
    Button btnNext3;
    Button btnCapture;
    ImageButton addDiseaseBtn2;
    ImageView imgCapture;

    org.techtown.oasis.OnTabItemSelectedListener listener;

    private static final int Image_Capture_Code = 3;

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

    // 촬영 시 필요한 메서드
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Capture_Code) {
            if (resultCode == Activity.RESULT_OK) {
                Bitmap bp = (Bitmap) data.getExtras().get("data");
                imgCapture.setImageBitmap(bp);
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getContext(), "Cancelled", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_health_data2, container, false);

        initUI(rootView);

        return rootView;
    }

    private void initUI(ViewGroup rootView) {
        // 약 추가
        addDiseaseBtn2 = rootView.findViewById(R.id.addDiseaseBtn2);
        addDiseaseBtn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"추가 완료!", Toast.LENGTH_SHORT).show();
            }
        });
        // 촬영 버튼
        btnCapture = rootView.findViewById(R.id.btnCapture2);
        imgCapture = rootView.findViewById(R.id.imgCapture2);
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,Image_Capture_Code);
            }
        });

        // 다음 버튼
        btnNext3 = rootView.findViewById(R.id.btnNext3);
        btnNext3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "저장 완료", Toast.LENGTH_SHORT).show();

                // fragment 2로 전환(메인)
                if (listener != null) {
                    listener.onTabSelected(0);
                }
            }
        });
    }
}