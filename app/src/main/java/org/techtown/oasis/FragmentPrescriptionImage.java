package org.techtown.oasis;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class FragmentPrescriptionImage extends DialogFragment implements View.OnClickListener {

    public static final String TAG_EVENT_DIALOG = "dialog_event";
    public FragmentPrescriptionImage() {}

    public static FragmentPrescriptionImage getInstance() {
        FragmentPrescriptionImage e = new FragmentPrescriptionImage();
        return e;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prescription_image, container);
        Button closeButton = view.findViewById(R.id.closeButton);
        closeButton.setOnClickListener(this);
        setCancelable(false);  // 화면의 검은 부분 클릭시 화면이 dismiss 되는 것 방지

        return view;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), "pdf로 저장 완료", Toast.LENGTH_SHORT).show();
        dismiss();
    }
}