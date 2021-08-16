package org.techtown.oasis.HospitalList.Child10;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.techtown.oasis.DoctorList.Fragment15;
import org.techtown.oasis.DoctorList.Fragment16;
import org.techtown.oasis.HospitalList.OnPersonItemClickListener;
import org.techtown.oasis.HospitalList.Person;
import org.techtown.oasis.HospitalList.PersonAdapter;
import org.techtown.oasis.R;

public class FragmentChild9 extends Fragment {

    RecyclerView recyclerView;
    PersonAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_child9, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView6);
        // 레이아웃 매니저: recyclerView가 보일 기본 형태 설정(방향, 격자 모양 여부)
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // recyclerView에 어댑터 설정
        adapter = new PersonAdapter();
        adapter.addItem(new Person(R.drawable.dermatology_leekwangjun, "이광준", "클린업피부과 대구범어점", 1, "대기 시간: 30분"));
        adapter.addItem(new Person(R.drawable.dermatology_kimhoonsoo, "김훈수", "예스병원", 3, "대기 시간: 1시간"));
        adapter.addItem(new Person(R.drawable.dermatology_leeseokjong, "이석종", "경북대학교병원", 2, "대기 시간: 40분"));
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}