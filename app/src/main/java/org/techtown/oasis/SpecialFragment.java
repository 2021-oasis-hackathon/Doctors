package org.techtown.oasis;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import org.techtown.oasis.HospitalList.Fragment12;
import org.techtown.oasis.HospitalList.Fragment8;

public class SpecialFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_special, container, false);

        initUI(rootView);

        return rootView;
    }

    private void initUI(ViewGroup rootView) {
        ImageButton imageButton1, imageButton2;

        imageButton1 = rootView.findViewById(R.id.imageButton2);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment8 fragment8 = new Fragment8();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment8);
                transaction.commit();
            }
        });

        imageButton2 = rootView.findViewById(R.id.imageButton3);
        imageButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Fragment12 fragment12 = new Fragment12();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment12);
                transaction.commit();
            }
        });
    }
}