package org.techtown.oasis;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {

    Context context;

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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_1, container, false);

        initUI(rootView);

        return rootView;
    }

    private void initUI(ViewGroup rootView) {
        EditText editText1, editText2;
        editText1 = rootView.findViewById(R.id.editText1);
        editText2 = rootView.findViewById(R.id.editText2);

        final String[] id = new String[1];
        final String[] pw = new String[1];

        Button LoginButton = rootView.findViewById(R.id.myButton);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id[0] = editText1.getText().toString();
                pw[0] = editText2.getText().toString();

                if (id[0].length() == 0) {
                    Toast.makeText(getContext(), "ID를 다시 입력해주세요", Toast.LENGTH_LONG).show();
                }
                else if (pw[0].length() == 0) {
                    Toast.makeText(getContext(), "PW를 다시 입력해주세요", Toast.LENGTH_LONG).show();
                }
                else {
                    if (listener != null) {
                        listener.onTabSelected(0);  // fragment_2로 전환
                    }
                }
            }
        });
    }
}