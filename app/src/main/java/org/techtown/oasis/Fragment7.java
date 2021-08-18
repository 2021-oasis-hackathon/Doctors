package org.techtown.oasis;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment7 extends Fragment {
    TextView text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_7, container, false);

        initUI(rootView);

        return rootView;
    }

    private void initUI(ViewGroup rootView) {
        text = rootView.findViewById(R.id.editTextAge);
        text.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 팝업 기능 구현
                FragmentBenefitsDetails fragmentBenefitsDetails = FragmentBenefitsDetails.getInstance();
                fragmentBenefitsDetails.show(getActivity().getSupportFragmentManager(), FragmentBenefitsDetails.TAG_EVENT_DIALOG);
            }
        });
    }
}