package org.techtown.oasis.HospitalList;

import android.location.Location;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import org.techtown.oasis.ChildFragment.FragmentChild1;
import org.techtown.oasis.ChildFragment.FragmentChild2;
import org.techtown.oasis.HospitalList.Child10.FragmentChild8;
import org.techtown.oasis.HospitalList.Child10.FragmentChild9;
import org.techtown.oasis.HospitalList.Child8.FragmentChild5;
import org.techtown.oasis.HospitalList.Child9.FragmentChild6;
import org.techtown.oasis.R;
import org.techtown.oasis.ViewPagerAdapter;

public class Fragment10 extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_10, container, false);

        addFragment(rootView);

        return rootView;
    }

    private void addFragment(View view) {
        tabLayout = view.findViewById(R.id.tabLayout5);
        viewPager = view.findViewById(R.id.viewPager5);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new FragmentChild8(), "내 주변");
        adapter.addFragment(new FragmentChild9(), "인기");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}