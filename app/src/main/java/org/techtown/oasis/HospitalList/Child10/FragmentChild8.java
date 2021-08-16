package org.techtown.oasis.HospitalList.Child10;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import org.techtown.oasis.DoctorList.Fragment15;
import org.techtown.oasis.DoctorList.Fragment16;
import org.techtown.oasis.HospitalList.Fragment8;
import org.techtown.oasis.HospitalList.OnPersonItemClickListener;
import org.techtown.oasis.HospitalList.Person;
import org.techtown.oasis.HospitalList.PersonAdapter;
import org.techtown.oasis.R;

import java.time.Instant;
import java.util.List;

import static androidx.core.content.ContextCompat.getSystemService;

public class FragmentChild8 extends Fragment {

    RecyclerView recyclerView;
    PersonAdapter adapter;

    String text1;  // 내 위치
    String dText1, dText2, dText3, dText4, dText5; // 병원 거리 텍스트
    // 병원 거리
    double distance1, distance2, distance3, distance4, distance5;

    // 내 위치
    Location locationA = new Location("point A");
    // 병원들의 위치
    Location locationB = new Location("point B");  // 현대의원 34.42462432864825, 126.16622182591342 (위도, 경도)
    Location locationC = new Location("point C");  // 효사랑의원 34.40860316749597, 126.19334483940517
    Location locationD = new Location("point D");  // 연합의원 34.409762679703995, 126.19118834320386
    Location locationE = new Location("point E");  // 세종의원 34.4384828462856, 126.28092445289826
    Location locationF = new Location("point F");  // 성심의원 34.48086634748008, 126.26346172591514


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_child8, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView5);
        // 레이아웃 매니저: recyclerView가 보일 기본 형태 설정(방향, 격자 모양 여부)
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        locationB.setLatitude(34.42462432864825);  // 위도
        locationB.setLongitude(126.16622182591342);  // 경도
        locationC.setLatitude(34.40860316749597);  // 위도
        locationC.setLongitude(126.19334483940517);  // 경도
        locationD.setLatitude(34.409762679703995);  // 위도
        locationD.setLongitude(126.19118834320386);  // 경도
        locationE.setLatitude(34.4384828462856);  // 위도
        locationE.setLongitude(126.28092445289826);  // 경도
        locationF.setLatitude(34.48086634748008);  // 위도
        locationF.setLongitude(126.26346172591514);  // 경도

        startLocationService();  // 위치 계산 서비스

        dText1 = Double.toString(distance1);
        dText2 = Double.toString(distance2);
        dText3 = Double.toString(distance3);
        dText4 = Double.toString(distance4);
        dText5 = Double.toString(distance5);

        // recyclerView에 어댑터 설정
        // 피부과
        adapter = new PersonAdapter();
        adapter.addItem(new Person(R.drawable.dermatology_parkkibum, "박기범", "현대의원", dText1, "대기 시간: 30분"));
        adapter.addItem(new Person(R.drawable.dermatology_kwonohsang, "권오상", "효사랑의원", dText2, "대기 시간: 1시간"));
        adapter.addItem(new Person(R.drawable.dermatology_yoonjaeil, "윤재일", "연합의원", dText3, "대기 시간: 40분"));
        adapter.addItem(new Person(R.drawable.dermatology_seoseongjun, "서성준", "세종의원", dText4, "대기 시간: 2시간"));
        adapter.addItem(new Person(R.drawable.dermatology_gowooseok, "고우석", "성심의원", dText5, "대기 시간: 3시간"));
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

    public void startLocationService() {
        // LocationManger 객체 참조
        LocationManager mgr = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        try {
            Location location = mgr.getLastKnownLocation(LocationManager.GPS_PROVIDER);  // 이전에 확인했던 위치 정보 가져오기
            if (location != null) {
                double longitude = location.getLongitude();  // 경도 가져오기
                double latitude = location.getLatitude();  // 위도 가져오기

                // A 위치에 위도, 경도 설정
                locationA.setLatitude(latitude);  // 위도
                locationA.setLongitude(longitude);  // 경도
            }

            GPSListener gpsListener = new GPSListener();
            long minTime = 10000;  // 최소 시간 10초
            float minDistance = 0;  // 최소 거리 0

            // 최소 시간, 최소 거리마다 위치 업데이트 요청
            mgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, gpsListener);
            Toast.makeText(getContext(), "내 위치 확인 요청함", Toast.LENGTH_SHORT).show();

            // distanceTo 메소드로 거리 계산, 저장
            distance1= locationA.distanceTo(locationB);
            distance2 = locationA.distanceTo(locationC);
            distance3 = locationA.distanceTo(locationD);
            distance4 = locationA.distanceTo(locationE);
            distance5 = locationA.distanceTo(locationF);

            /*
            String message = Double.toString(distance);
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
             */
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    class GPSListener implements LocationListener {
        public void onLocationChanged(Location location) {
            double longitude = location.getLongitude();  // 경도 가져오기
            double latitude = location.getLatitude();  // 위도 가져오기

            // A 위치에 위도, 경도 설정
            locationA.setLatitude(latitude);
            locationA.setLongitude(longitude);

            // distanceTo 메소드로 거리 계산, 갱신
            distance1= locationA.distanceTo(locationB);
            distance2 = locationA.distanceTo(locationC);
            distance3 = locationA.distanceTo(locationD);
            distance4 = locationA.distanceTo(locationE);
            distance5 = locationA.distanceTo(locationF);
        }
    }

    public void onProviderDisabled(String provider) { }

    public void onProviderEnabled(String provider) { }

    public void onStatusChanged(String provider, int status, Bundle extras) { }

}