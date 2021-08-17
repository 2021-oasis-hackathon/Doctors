package org.techtown.oasis;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class FragmentCheckPopup extends DialogFragment implements View.OnClickListener {

    public static final String TAG_EVENT_DIALOG = "dialog_event";
    public FragmentCheckPopup() {}

    public static FragmentCheckPopup getInstance() {
        FragmentCheckPopup e = new FragmentCheckPopup();
        return e;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check_popup, container);
        Button closeButton = view.findViewById(R.id.button);
        closeButton.setOnClickListener(this);
        setCancelable(false);  // 화면의 검은 부분 클릭시 화면이 dismiss 되는 것 방지

        return view;
    }

    @Override
    public void onClick(View v) {
        //Toast.makeText(getContext(), "완료", Toast.LENGTH_SHORT).show();
        dismiss();
    }
}