package org.techtown.oasis.HospitalList;

import android.view.View;

public interface OnPersonItemClickListener {
    // 뷰홀더 객체와 뷰 객체, 뷰의 position 정보 전달
    public void onItemClick(PersonAdapter.ViewHolder holder, View view, int position);
}
