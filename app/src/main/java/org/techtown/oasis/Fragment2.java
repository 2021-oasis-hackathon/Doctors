package org.techtown.oasis;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import org.techtown.oasis.HospitalList.Fragment10;
import org.techtown.oasis.HospitalList.Fragment11;
import org.techtown.oasis.HospitalList.Fragment12;
import org.techtown.oasis.HospitalList.Fragment13;
import org.techtown.oasis.HospitalList.Fragment14;
import org.techtown.oasis.HospitalList.Fragment8;
import org.techtown.oasis.HospitalList.Fragment9;

import java.util.ArrayList;
import java.util.List;

public class Fragment2 extends Fragment {
    // 데이터를 넣은 리스트
    private List<String> list;
    // 검색 리스트
    private ListView listView;
    // 검색어를 입력할 입력창
    private EditText editSearch;
    // 리스트 뷰에 연결할 어댑터
    private SearchAdapter adapter;
    private ArrayList<String> arraylist;
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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_2, container, false);

        initUI(rootView);

        return rootView;
    }
    // 검색 창 구현
    private void initUI(ViewGroup rootView) {
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
        adapter = new SearchAdapter(list, getContext());

        // 리스트 뷰에 어댑터 연결
        listView.setAdapter(adapter);

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
                if (b) {  // 의사 리스트 화면으로 전환(fragment_8~14)

                    if (searchedText.equals("수면장애")) {
                        SpecialFragment specialFragment = new SpecialFragment();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.container, specialFragment);
                        transaction.commit();
                    }
                    else if (i < 50) {
                        Fragment8 fragment8 = new Fragment8();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.container, fragment8);
                        transaction.commit();
                    }
                    else if (i < 100) {
                        Fragment9 fragment9 = new Fragment9();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.container, fragment9);
                        transaction.commit();
                    }
                    else if (i < 130) {
                        Fragment10 fragment10 = new Fragment10();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.container, fragment10);
                        transaction.commit();
                    }
                    else if (i < 180) {
                        Fragment11 fragment11 = new Fragment11();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.container, fragment11);
                        transaction.commit();
                    }
                    else if (i < 230) {
                        Fragment12 fragment12 = new Fragment12();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.container, fragment12);
                        transaction.commit();
                    }
                    else if (i < 245) {
                        Fragment13 fragment13 = new Fragment13();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.container, fragment13);
                        transaction.commit();
                    }
                    else {
                        Fragment14 fragment14 = new Fragment14();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.container, fragment14);
                        transaction.commit();
                    }
                }
                else {
                    Toast.makeText(getContext(), "검색어를 다시 입력해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 내 정보 입력하러 가기
        ImageButton imageButton;
        imageButton = rootView.findViewById(R.id.imageButton123);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // fragment_3로 전환
                Fragment3 fragment3 = new Fragment3();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment3);
                transaction.commit();
            }
        });
    }

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
        adapter.notifyDataSetChanged();
    }

    // 검색에 사용될 데이터를 리스트에 추가
    private void settingList(){
        String[][] stringArray = {
                {"공황", "난독", "가위눌림", "거식증", "두근거림", "기억상실", "결벽","도벽"
                ,"ADHD","몽유병","기면","무기력","몽상","공포","분노조절","망상","강박","먹토"
                ,"발표공포증","만성피로","불면","불안","성욕","손떨림","수면장애","스트레스","식이장애",
                        "악몽","알츠하이머","알코올 중독","우울","입면장애","자폐","자해","잠꼬대","정신병",
                        "정신분열","조울","조증","조현병","중독","치매","트라우마","포비아","폭식","피로","피해망상","환각", "환청","후유증"},
                {
                        "귓볼", 	"난청", 	"갑상선", "구강", "목", 	"기침", 	"구내", 	"냄새", 	"가래", 	"부비동", 	"기관지", 	"비염", 	"부비", 	"귀", 	"설암", 	"미열", 	"고막", 	"볼거리", 	"비중격만곡증", 	"목젓", 	"성대", 	"수면무호흡증", 	"숨막힘", 	"악취", 	"안면마비", 	"알레르기", 	"염증", 	"외이", 	"이명", 	"이석증", 	"인두", 	"인후", 	"임파선", 	"임파선", 	"입냄새", 	"재채기", 	"중이염", 	"천식", 	"청각", 	"축농증", "치주", 	"침", 	"코", 	"코피", 	"콧구멍", "콧물", "편도", 	"혀", "후각", 	"후두"
                },
                {
                        "따가움", 	"모포염", 	"간지러움", 	"내성발톱", 	"반점", 	"모낭염", 	"두드러기", 	"무좀", 	"가려움", 	"습진", 	"모공", 	"여드름", 	"수족다한증", 	"등드름", 	"제모", 	"뾰루지", 	"기미", 	"사마귀", 	"점", 	"블랙헤드", 	"주근깨", 	"착색", 	"켈로이드", 	"튼살", 	"티눈", 	"피부", 	"홍반", 	"홍조", 	"화이트헤드", 	"흉터",
                },
                {
                        "고혈압", 	"구역질", 	"가슴", 	"감기", 	"기관지", 	"관절염", 	"갑상선", 	"구토", 	"가래", 	"두근거림", 	"과민성대장증후군", 	"두통", 	"대상포진", 	"결핵", 	"맥박", 	"기흉", 	"간", 	"당뇨", 	"류마티스", 	"기침", 	"맹장", 	"메스꺼움", 	"몸살", 	"방광", 	"배", 	"복통", 	"부정맥", 	"붓기", 	"빈혈", 	"설사", 	"소화", 	"수두", 	"심박수", 	"심장", 	"어지러움", 	"에이즈", 	"역류성식도염", 	"열", 	"위", 	"자궁", 	"자반증", 	"장염", 	"저혈압", 	"종양", 	"척추염", 	"통풍", 	"파상풍", 	"폐", 	"피", 	"호흡",
                },
                {
                        "다한증", 	"두통", 	"구안와사", 	"귀", 	"마비", 	"두근거림", 	"기력", 	"땀", 	"가스", 	"발목", 	"담", 	"배", 	"미각", 	"눈", 	"복통", 	"목", 	"구토", 	"무릎", 	"변비", 	"만성피로", 	"불면", 	"비대칭", 	"비염", 	"빈혈", 	"생리", 	"설사", 	"소화", 	"수면장애", 	"시큰거림", 	"식도", 	"식은땀", 	"알레르기", 	"어깨", 	"어지럼증", 	"열", 	"염증", 	"이명", 	"인후", 	"재채기", 	"저림", 	"천식", 	"체", 	"체질", 	"코", 	"통증", 	"편도", 	"피로", 	"항문", 	"허리", 	"홍조",
                },
                {
                        "교정", 	"덧니", 	"돌출입", 	"매복", 	"발치", 	"부정교합", 	"사랑니", 	"앞니", 	"이빨", 	"임플란트", 	"잇몸", 	"충치", 	"치아", 	"치통", 	"크라운",
                },
                {
                        "각막", 	"결막염", 	"근시", 	"난시", 	"노안", 	"녹내장", 	"눈", 	"눈물", 	"다래끼", 	"망막", 	"백내장", 	"시력", 	"시야", 	"안구", 	"안구건조증", 	"안압", 	"원시", 	"충열", 	"포도막염", 	"황반",
                }
        };
        for (int i = 0; i < 7; i++) {
            if (i == 0 || i == 1 || i == 3 || i == 4) {
                for (int j = 0; j < 50; j++) {
                    list.add(stringArray[i][j]);
                }
            }
            else if (i == 2) {
                for (int j = 0; j < 30; j++) {
                    list.add(stringArray[i][j]);
                }
            }
            else if (i == 5) {
                for (int j = 0; j < 15; j++) {
                    list.add(stringArray[i][j]);
                }
            }
            else if (i == 6) {
                for (int j = 0; j < 20; j++) {
                    list.add(stringArray[i][j]);
                }
            }
        }
    }


}