package org.techtown.oasis.HospitalList;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import org.techtown.oasis.HospitalList.Child13.FragmentChild14;
import org.techtown.oasis.HospitalList.Child13.FragmentChild15;
import org.techtown.oasis.HospitalList.Child14.FragmentChild16;
import org.techtown.oasis.HospitalList.Child14.FragmentChild17;
import org.techtown.oasis.R;
import org.techtown.oasis.ViewPagerAdapter;

public class Fragment14 extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_14, container, false);

        addFragment(rootView);

        return rootView;
    }

    private void addFragment(View view) {
        tabLayout = view.findViewById(R.id.tabLayout9);
        viewPager = view.findViewById(R.id.viewPager9);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new FragmentChild16(), "내 주변");
        adapter.addFragment(new FragmentChild17(), "인기");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}