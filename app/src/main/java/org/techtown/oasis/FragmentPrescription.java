package org.techtown.oasis;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentPrescription extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_prescription, container, false);

        Button btn8, btn9, btn100;
        // 약 배송으로
        btn8 = rootView.findViewById(R.id.btn8);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentDelivery fragmentDelivery = new FragmentDelivery();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragmentDelivery);
                transaction.commit();
            }
        });
        // 병원 예약으로
        btn9 = rootView.findViewById(R.id.btn9);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentReservation fragment_reservation = new FragmentReservation();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment_reservation);
                transaction.commit();
            }
        });
        // 처방전 보기
        btn100 = rootView.findViewById(R.id.btn100);
        btn100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 팝업 기능 구현
                FragmentPrescriptionImage fragmentPrescriptionImage = FragmentPrescriptionImage.getInstance();
                fragmentPrescriptionImage.show(getActivity().getSupportFragmentManager(), FragmentPrescriptionImage.TAG_EVENT_DIALOG);
            }
        });

        return rootView;
    }
}