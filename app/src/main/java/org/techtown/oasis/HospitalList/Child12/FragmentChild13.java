package org.techtown.oasis.HospitalList.Child12;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.techtown.oasis.HospitalList.Person;
import org.techtown.oasis.HospitalList.PersonAdapter;
import org.techtown.oasis.R;


public class FragmentChild13 extends Fragment {

    RecyclerView recyclerView;
    PersonAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_child13, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView10);
        // 레이아웃 매니저: recyclerView가 보일 기본 형태 설정(방향, 격자 모양 여부)
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // recyclerView에 어댑터 설정
        adapter = new PersonAdapter();
        adapter.addItem(new Person(R.drawable.km_parkhoyoung, "박호영", "경희궁전한의원", "", "대기 시간: 30분"));
        adapter.addItem(new Person(R.drawable.km_kimsohyung, "김소형", "김소형한의원", "", "대기 시간: 1시간"));
        adapter.addItem(new Person(R.drawable.km_leekyungjae, "이경제", "이경제한의원", "", "대기 시간: 40분"));
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}