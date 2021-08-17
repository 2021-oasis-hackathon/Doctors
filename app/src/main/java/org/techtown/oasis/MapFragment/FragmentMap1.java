package org.techtown.oasis.MapFragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.techtown.oasis.HospitalList.Child10.FragmentChild8;
import org.techtown.oasis.HospitalList.Child8.FragmentChild4;
import org.techtown.oasis.HospitalList.Child8.FragmentChild5;
import org.techtown.oasis.R;

public class FragmentMap1 extends Fragment implements OnMapReadyCallback {
    private ViewGroup rootView;
    private FragmentActivity myContext;
    FragmentManager fragmentManger;
    MapFragment mapFragment;
    Button checkButton;
    String fragmentName = null;

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_map1, container, false);

        fragmentManger = myContext.getFragmentManager();
        mapFragment = (MapFragment) fragmentManger.findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);

        // 어디에서 화면 전환을 했는지 알려주는 정보 받음(원래 위치로 돌아가기 위해!)
        Bundle bundle1 = getArguments(); // getArguments() 메소드로 번들 받기
        if (bundle1 != null) {
            fragmentName = bundle1.getString("fragmentName");  // ThirdFragment에서 받아온 값 넣기
        }

        checkButton = rootView.findViewById(R.id.checkButton);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 정보 전달
                Bundle bundle2 = new Bundle();  // bundle으로 값 전달
                bundle2.putInt("switch", 1); // bundle에 넘길 값 저장

                if (fragmentName.equals("fragmentChild4")) {
                    FragmentChild4 fragmentChild4 = new FragmentChild4();  // 피부과(가까움)
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentChild4.setArguments(bundle2);  // bundle을 SecondFragment로 보낼 준비
                    transaction.replace(R.id.container, fragmentChild4);
                    transaction.commit();
                }
                else if (fragmentName.equals("fragmentChild5")) {
                    FragmentChild5 fragmentChild5 = new FragmentChild5();  // 피부과(가까움)
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentChild5.setArguments(bundle2);  // bundle을 SecondFragment로 보낼 준비
                    transaction.replace(R.id.container, fragmentChild5);
                    transaction.commit();
                }
                else if (fragmentName.equals("fragmentChild8")) {
                    FragmentChild8 fragmentChild8 = new FragmentChild8();  // 피부과(가까움)
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentChild8.setArguments(bundle2);  // bundle을 SecondFragment로 보낼 준비
                    transaction.replace(R.id.container, fragmentChild8);
                    transaction.commit();
                }
                else { }
            }
        });
        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng location = new LatLng(34.48376633861544, 126.25693440719049);  // 진도 대림 아파트
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title("진도 대림아파트");
        markerOptions.snippet("아파트");
        markerOptions.position(location);
        googleMap.addMarker(markerOptions);

        //googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 16));
    }
}