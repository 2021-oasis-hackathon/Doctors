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

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.techtown.oasis.DoctorList.Fragment15;
import org.techtown.oasis.DoctorList.Fragment16;
import org.techtown.oasis.HospitalList.Fragment10;
import org.techtown.oasis.HospitalList.Fragment11;
import org.techtown.oasis.HospitalList.Fragment12;
import org.techtown.oasis.HospitalList.Fragment13;
import org.techtown.oasis.HospitalList.Fragment14;
import org.techtown.oasis.HospitalList.Fragment8;
import org.techtown.oasis.HospitalList.Fragment9;
import org.techtown.oasis.HospitalList.OnPersonItemClickListener;
import org.techtown.oasis.HospitalList.Person;
import org.techtown.oasis.HospitalList.PersonAdapter;
import org.techtown.oasis.MapFragment.FragmentMap1;
import org.techtown.oasis.MapFragment.FragmentMap2;
import org.techtown.oasis.MapFragment.FragmentMap3;
import org.techtown.oasis.R;
import org.techtown.oasis.SearchAdapter;
import org.techtown.oasis.SpecialFragment;

import java.text.DecimalFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;

import static androidx.core.content.ContextCompat.getSystemService;

public class FragmentChild8 extends Fragment {

    // 거리 순 정렬을 위한 스위치
    int sw = 0;

    // 데이터를 넣은 리스트
    private List<String> list;
    // 검색 리스트
    private ListView listView;
    // 검색어를 입력할 입력창
    private EditText editSearch;
    // 리스트 뷰에 연결할 어댑터
    private SearchAdapter searchAdapter;
    private ArrayList<String> arraylist;

    RecyclerView recyclerView;
    PersonAdapter personAdapter;  // Person 객체를 담을 어댑터

    // 병원 거리
    double distance1, distance2, distance3, distance4, distance5; // 미터 단위
    double dk1, dk2, dk3, dk4, dk5;  // 킬로미터 단위

    // 내 위치
    Location locationA = new Location("point A");
    // 병원들의 위치
    Location locationB = new Location("point B");  // 현대의원 34.42462432864825, 126.16622182591342 (위도, 경도)
    Location locationC = new Location("point C");  // 효사랑의원 34.40860316749597, 126.19334483940517
    Location locationD = new Location("point D");  // 연합의원 34.409762679703995, 126.19118834320386
    Location locationE = new Location("point E");  // 세종의원 34.4384828462856, 126.28092445289826
    Location locationF = new Location("point F");  // 성심의원 34.48086634748008, 126.26346172591514

    FragmentMap1 fragmentMap1 = new FragmentMap1();
    FragmentMap2 fragmentMap2 = new FragmentMap2();
    FragmentMap3 fragmentMap3 = new FragmentMap3();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_child8, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView5);
        // 레이아웃 매니저: recyclerView가 보일 기본 형태 설정(방향, 격자 모양 여부)
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // 검색 기능
        editSearch = rootView.findViewById(R.id.editSearch);
        listView = rootView.findViewById(R.id.listView);
        // listView.setVisibility(View.INVISIBLE); // 리스트 뷰 숨김

        // 리스트 생성
        list = new ArrayList<String>();

        // 검색에 사용할 데이터을 검색 전에 저장
        settingList();

        arraylist = new ArrayList<String>();
        // 리스트의 모든 데이터를 arraylist에 넣기
        arraylist.addAll(list);

        // 리스트에 연동될 어댑터 생성
        searchAdapter = new SearchAdapter(list, getContext());

        // 리스트 뷰에 어댑터 연결
        listView.setAdapter(searchAdapter);

        // 리스트의 아이템 클릭시 동작
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editSearch.setText(list.get(position));  // editText에 아이템의 텍스트 저장
            }
        });

        // addTextChangedListener
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int a, int b, int c) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int a, int b, int c) {
            }

            @Override
            // input창에 문자를 입력할 때마다 호출
            public void afterTextChanged(Editable editable) {
                String text = editSearch.getText().toString();
                search(text);  // search 메소드 호출
            }
        });

        // 검색 버튼
        Button searchButton = rootView.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i;
                boolean b = false;
                String searchedText = editSearch.getText().toString();

                for (i = 0; i < arraylist.size(); i++) {
                    //Toast.makeText(getContext(), arraylist.get(i), Toast.LENGTH_SHORT).show();
                    // 리스트에 있는 단어를 검색했으면
                    if (searchedText.equals(arraylist.get(i))) {
                        b = true;
                        break;
                    }
                }
                if (b) {  // 지도 화면으로 전환(fragment_map1~3)

                    if (searchedText.equals("진도 대림 아파트")) {
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.container, fragmentMap1);
                        transaction.commit();
                    }
                    else if (searchedText.equals("가사도 마을회관")) {
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.container, fragmentMap2);
                        transaction.commit();
                    }
                    else if (searchedText.equals("진도 계명아파트")) {
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.container, fragmentMap3);
                        transaction.commit();
                    }
                }
                else {
                    Toast.makeText(getContext(), "검색어를 다시 입력해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 위도 경도 셋팅
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

        // getArguments() 메소드로 번들 받기
        Bundle bundle = getArguments();  // getArguments() 메소드로 번들 받기
        if (bundle != null) {
            sw = bundle.getInt("switch");  // 번들에서 int 받기
        }

        startLocationService();  // 위치 계산 서비스
        if (sw == 0) {  // 변경 x
        }
        else if (sw == 1) {
            // A 위치에 위도, 경도 설정
            locationA.setLatitude(34.48376633861544);  // 위도
            locationA.setLongitude(126.25693440719049);  // 경도
        }
        else if (sw == 2) {
            // A 위치에 위도, 경도 설정
            locationA.setLatitude(34.47908458409323);  // 위도
            locationA.setLongitude(126.05103752603114);  // 경도
        }
        else if (sw == 3) {
            // A 위치에 위도, 경도 설정
            locationA.setLatitude(34.482203846073475);  // 위도
            locationA.setLongitude(126.26682461686529);  // 경도
        }
        // distanceTo 메소드로 거리 계산, 저장
        distance1= locationA.distanceTo(locationB);
        distance2 = locationA.distanceTo(locationC);
        distance3 = locationA.distanceTo(locationD);
        distance4 = locationA.distanceTo(locationE);
        distance5 = locationA.distanceTo(locationF);

        // distanceTo는 미터 단위로 값을 리턴하므로 1000으로 나누어 km로 변환
        dk1 = Math.round((distance1 / 1000));
        dk2 = Math.round((distance2 / 1000));
        dk3 = Math.round((distance3 / 1000));
        dk4 = Math.round((distance4 / 1000));
        dk5 = Math.round((distance5 / 1000));

        personAdapter = new PersonAdapter();

        // recyclerView에 어댑터 설정
        // 피부과
        Person person1 = new Person(R.drawable.dermatology_parkkibum, "박기범", "현대의원", dk1, "대기 시간: 30분");
        Person person2 = new Person(R.drawable.dermatology_kwonohsang, "권오상", "효사랑의원", dk2, "대기 시간: 1시간");
        Person person3 = new Person(R.drawable.dermatology_yoonjaeil, "윤재일", "연합의원", dk3, "대기 시간: 40분");
        Person person4 = new Person(R.drawable.dermatology_seoseongjun, "서성준", "세종의원", dk4, "대기 시간: 2시간");
        Person person5 = new Person(R.drawable.dermatology_gowooseok, "고우석", "성심의원", dk5, "대기 시간: 3시간");

        ArrayList<Person> personArrayList = new ArrayList<Person>();
        personArrayList.add(person1);
        personArrayList.add(person2);
        personArrayList.add(person3);
        personArrayList.add(person4);
        personArrayList.add(person5);
        // 거리가 가까운 순서대로 정렬
        Collections.sort(personArrayList, sortByTotalCall);
        personAdapter.addItem(personArrayList);
        recyclerView.setAdapter(personAdapter);

        // 어댑터에 리스너 설정
        personAdapter.setOnItemClickListener(new OnPersonItemClickListener() {
            @Override
            public void onItemClick(PersonAdapter.ViewHolder holder, View view, int position) {
                Person item = personAdapter.getItem(position);  // 어댑터에 리스너 설정
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
        }
    }

    public void onProviderDisabled(String provider) { }

    public void onProviderEnabled(String provider) { }

    public void onStatusChanged(String provider, int status, Bundle extras) { }


    // PersonArrayList 정렬을 위한 Comparator
    public static Comparator<Person> sortByTotalCall = new Comparator<Person>() {

        @Override
        public int compare(Person o1, Person o2) {
            return Double.compare(o1.getDistance(), o2.getDistance());
        }
    };

    // 검색을 수행하는 메소드
    public void search(String charText) {
        // 문자 입력시마다 리스트를 지움
        list.clear();

        if (charText.length() == 0) {
            list.addAll(arraylist);
        }
        else {
            // 문자 입력을 할 때 리스트의 모든 데이터를 검색
            for(int i = 0; i < arraylist.size(); i++) {
                // arraylist의 data 중에서 입력받은 단어가 포함되어 있으면 검색된 데이터를 리스트에 추가
                if (arraylist.get(i).toLowerCase().contains(charText)) {
                    list.add(arraylist.get(i));
                }
            }
        }

        // 리스트 데이터가 변경되었으므로 어댑터를 갱신해 검색된 데이터를 화면에 보여줌
        searchAdapter.notifyDataSetChanged();
    }

    // 검색에 사용될 데이터를 리스트에 추가
    private void settingList(){
        String[] stringArray = {"진도 대림 아파트", "가사도 마을회관", "진도 계명아파트"};

        for (int i = 0; i < stringArray.length; i++) {
            list.add(stringArray[i]);
        }
    }
}