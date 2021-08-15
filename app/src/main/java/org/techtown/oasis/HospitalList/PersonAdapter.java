package org.techtown.oasis.HospitalList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.oasis.R;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder>
        implements OnPersonItemClickListener{
    // Person 객체를 담는 ArrayList 자료형의 items 변수 만듦
    ArrayList<Person> items = new ArrayList<Person>();
    OnPersonItemClickListener listener;

    @NonNull
    @Override
    // 뷰홀더 객체가 만들어질 때 자동 호출
    // person_item.xml을 이용해 뷰 객체를 만들고 이를 새로운 뷰홀더 객체에 담아 반환
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        // 인플레이션을 통해 뷰 객체 만듦
        View itemView = inflater.inflate(R.layout.person_item, viewGroup, false);
        // 뷰홀더 객체를 생성하며 뷰 객체(itemView)를 전달하고, 뷰홀더 객체 반환
        return new ViewHolder(itemView, (OnPersonItemClickListener) this);
    }

    @Override
    // 뷰홀더 객체가 재사용될 때 자동으로 호출
    // (메모리 절약을 위해 사용자가 스크롤하여 보이지 않게 된 뷰 객체를 새로 보이는 쪽에 재사용)
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Person item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    // recyclerView에서 어댑터가 관리하는 아이템의 개수를 반환
    public int getItemCount() {
        return items.size();
    }

    // arrayList에 객체 추가
    public void addItem(Person item) {
        items.add(item);
    }

    // arrayList 전체를 설정
    public void setItems(ArrayList<Person> items) {
        this.items = items;
    }

    // arrayList에서 position 위치에 있는 객체를 반환
    public Person getItem(int position) {
        return items.get(position);
    }

    // 특정 위치의 객체 설정
    public void setItem(int position, Person item) {
        items.set(position, item);
    }

    // 어댑터 클래스 밖에서 listener 객체를 변수에 할당
    // onItemClick 함수가 호출되었을 때 다시 외부에서 설정된 함수가 호출되도록 만들 수 있다.
    public void setOnItemClickListener(OnPersonItemClickListener listener) {
        this.listener = listener;
    }

    // 뷰홀더 클래스 안에서 뷰가 클릭되었을 때 호출
    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;

        public ViewHolder(View itemView, final OnPersonItemClickListener listener) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }

        public void setItem(Person item) {
            textView.setText(item.getName());
            textView2.setText(item.getMobile());
        }
    }
}
