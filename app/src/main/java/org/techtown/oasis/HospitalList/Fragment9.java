package org.techtown.oasis.HospitalList;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import org.techtown.oasis.HospitalList.Child9.FragmentChild6;
import org.techtown.oasis.HospitalList.Child9.FragmentChild7;
import org.techtown.oasis.R;
import org.techtown.oasis.ViewPagerAdapter;

public class Fragment9 extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_9, container, false);

        addFragment(rootView);

        return rootView;
    }

    private void addFragment(View view) {
        tabLayout = view.findViewById(R.id.tabLayout4);
        viewPager = view.findViewById(R.id.viewPager4);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new FragmentChild6(), "내 주변");
        adapter.addFragment(new FragmentChild7(), "인기");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}