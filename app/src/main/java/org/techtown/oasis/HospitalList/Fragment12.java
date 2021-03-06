package org.techtown.oasis.HospitalList;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import org.techtown.oasis.HospitalList.Child11.FragmentChild10;
import org.techtown.oasis.HospitalList.Child11.FragmentChild11;
import org.techtown.oasis.HospitalList.Child12.FragmentChild12;
import org.techtown.oasis.HospitalList.Child12.FragmentChild13;
import org.techtown.oasis.R;
import org.techtown.oasis.ViewPagerAdapter;

public class Fragment12 extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_12, container, false);

        addFragment(rootView);

        return rootView;
    }

    private void addFragment(View view) {
        tabLayout = view.findViewById(R.id.tabLayout7);
        viewPager = view.findViewById(R.id.viewPager7);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new FragmentChild12(), "내 주변");
        adapter.addFragment(new FragmentChild13(), "인기");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}