package org.techtown.oasis;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentBenefitsDetails extends DialogFragment implements View.OnClickListener {
    public static final String TAG_EVENT_DIALOG = "dialog_event2";

    public FragmentBenefitsDetails() { }

    public static FragmentBenefitsDetails getInstance() {
        FragmentBenefitsDetails e = new FragmentBenefitsDetails();
        return e;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_benefits_details, container);
        Button closeButton = view.findViewById(R.id.closeBtn_benefits);
        closeButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}