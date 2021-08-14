package org.techtown.oasis;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import org.techtown.oasis.ChildFragment.FragmentChild1;
import org.techtown.oasis.ChildFragment.FragmentChild2;
import org.techtown.oasis.ChildFragment.FragmentChild3;

public class Fragment6 extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_6, container, false);

        addFragment(rootView);

        return rootView;
    }

    private void addFragment(View view) {
        tabLayout = view.findViewById(R.id.tabLayout2);
        viewPager = view.findViewById(R.id.viewPager2);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new FragmentChild1(), "Child 1");
        adapter.addFragment(new FragmentChild2(), "Child 2");
        adapter.addFragment(new FragmentChild3(), "Child 3");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}