package org.techtown.oasis;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.techtown.oasis.HospitalList.Fragment14;
import org.techtown.oasis.HospitalList.Fragment8;
import org.w3c.dom.Text;

public class Fragment17 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_17, container, false);

        Button sendButton, callButton;
        String name;

        sendButton = rootView.findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "전송 완료", Toast.LENGTH_SHORT).show();
            }
        });
        Bundle bundle = getArguments();  // getArguments() 메소드로 번들 받기
        name = bundle.getString("Name");

        callButton = rootView.findViewById(R.id.callButton);
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

        return rootView;
    }
}