package org.techtown.oasis;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class Fragment2 extends Fragment {

    private List<String> list;          // 데이터를 넣은 리스트 변수
    private ListView listView;          // 검색을 보여줄 리스트 변수
    private EditText editSearch;        // 검색어를 입력할 Input 창
    private SearchAdapter adapter;      // 리스트 뷰에 연결할 어댑터
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

    private void initUI(ViewGroup rootView) {

        editSearch = (EditText) rootView.findViewById(R.id.editSearch);
        listView = (ListView) rootView.findViewById(R.id.listView);

        //listView.setVisibility(View.INVISIBLE); // 리스트 뷰 숨김

        // 리스트 생성
        list = new ArrayList<String>();

        // 검색에 사용할 데이터을 미리 저장한다.
        settingList();

        // 리스트의 모든 데이터를 arraylist에 복사
        arraylist = new ArrayList<String>();
        arraylist.addAll(list);

        // 리스트에 연동될 어댑터를 생성한다.
        adapter = new SearchAdapter(list, getContext());

        // 리스트뷰에 아답터를 연결한다.
        listView.setAdapter(adapter);

        // input창에 검색어를 입력시 "addTextChangedListener" 이벤트 리스너를 정의한다.
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // input창에 문자를 입력할때마다 호출된다.
                // search 메소드를 호출한다.
                String text = editSearch.getText().toString();
                search(text);
            }
        });

        // 내 정보 입력하러 가기
        ImageButton myInfoButton;

        myInfoButton = rootView.findViewById(R.id.imageButton);
        myInfoButton.setOnClickListener(new View.OnClickListener() {
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
        // 문자 입력시마다 리스트를 지운 후 다시 나타냄
        list.clear();

        if (charText.length() == 0) {
            list.addAll(arraylist);
        }
        else {
            // 문자 입력을 할 때 리스트의 모든 데이터를 검색
            for(int i = 0; i < arraylist.size(); i++) {
                // arraylist의 데이터 중 입력받은 단어(charText)가 포함되어 있으면 검색된 데이터를 리스트에 추가
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
        list.add("배가 아파요");
        list.add("복통");
        list.add("두통");
        list.add("머리가 아파요");
        list.add("열이 나요");
        list.add("어지러워요");
    }
}