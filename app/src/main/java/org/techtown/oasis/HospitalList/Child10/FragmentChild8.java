package org.techtown.oasis.HospitalList.Child10;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import org.techtown.oasis.DoctorList.Fragment15;
import org.techtown.oasis.DoctorList.Fragment16;
import org.techtown.oasis.HospitalList.Fragment8;
import org.techtown.oasis.HospitalList.OnPersonItemClickListener;
import org.techtown.oasis.HospitalList.Person;
import org.techtown.oasis.HospitalList.PersonAdapter;
import org.techtown.oasis.R;

public class FragmentChild8 extends Fragment {

    RecyclerView recyclerView;
    PersonAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_child8, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView5);
        // 레이아웃 매니저: recyclerView가 보일 기본 형태 설정(방향, 격자 모양 여부)
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // recyclerView에 어댑터 설정
        // 피부과
        adapter = new PersonAdapter();
        adapter.addItem(new Person(R.drawable.dermatology_parkkibum, "박기범", "현대의원", "", "대기 시간: 30분"));
        adapter.addItem(new Person(R.drawable.dermatology_kwonohsang, "권오상", "효사랑의원", "", "대기 시간: 1시간"));
        adapter.addItem(new Person(R.drawable.dermatology_yoonjaeil, "윤재일", "연합의원", "", "대기 시간: 40분"));
        adapter.addItem(new Person(R.drawable.dermatology_seoseongjun, "서성준", "세종의원", "", "대기 시간: 2시간"));
        adapter.addItem(new Person(R.drawable.dermatology_gowooseok, "고우석", "성심의원", "", "대기 시간: 3시간"));
        recyclerView.setAdapter(adapter);

        // 어댑터에 리스너 설정
        adapter.setOnItemClickListener(new OnPersonItemClickListener() {
            @Override
            public void onItemClick(PersonAdapter.ViewHolder holder, View view, int position) {
                Person item = adapter.getItem(position);  // 어댑터에 리스너 설정
                Toast.makeText(getContext(), "의사 선택됨: " + item.getName(), Toast.LENGTH_SHORT).show();
                if (position == 0) {
                    Fragment15 fragment15 = new Fragment15();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.container, fragment15);
                    transaction.commit();
                }
            }
        });

        return rootView;
    }
}