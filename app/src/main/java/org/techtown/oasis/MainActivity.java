package org.techtown.oasis;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements org.techtown.oasis.OnTabItemSelectedListener {

    org.techtown.oasis.Fragment1 fragment1;
    org.techtown.oasis.Fragment2 fragment2;
    org.techtown.oasis.Fragment3 fragment3;
    org.techtown.oasis.Fragment4 fragment4;
    org.techtown.oasis.Fragment5 fragment5;
    org.techtown.oasis.Fragment6 fragment6;
    org.techtown.oasis.Fragment7 fragment7;

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new org.techtown.oasis.Fragment1();
        fragment2 = new org.techtown.oasis.Fragment2();
        fragment3 = new org.techtown.oasis.Fragment3();
        fragment4 = new org.techtown.oasis.Fragment4();
        fragment5 = new org.techtown.oasis.Fragment5();
        fragment6 = new org.techtown.oasis.Fragment6();
        fragment7 = new org.techtown.oasis.Fragment7();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.tab1:
                                Toast.makeText(getApplicationContext(), "첫 번째 탭 선택됨", Toast.LENGTH_LONG).show();
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();

                                return true;
                            case R.id.tab2:
                                Toast.makeText(getApplicationContext(), "두 번째 탭 선택됨", Toast.LENGTH_LONG).show();
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment6).commit();

                                return true;
                            case R.id.tab3:
                                Toast.makeText(getApplicationContext(), "세 번째 탭 선택됨", Toast.LENGTH_LONG).show();
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment5).commit();

                                return true;
                            case R.id.tab4:
                                Toast.makeText(getApplicationContext(), "네 번째 탭 선택됨", Toast.LENGTH_LONG).show();
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment4).commit();

                                return true;
                        }
                        return false;
                    }
                });
    }

    public void onTabSelected(int position) {
        if (position == 0) {
            bottomNavigation.setSelectedItemId(R.id.tab1);
        } else if (position == 1) {
            bottomNavigation.setSelectedItemId(R.id.tab2);
        } else if (position == 2) {
            bottomNavigation.setSelectedItemId(R.id.tab3);
        } else if (position == 3) {
            bottomNavigation.setSelectedItemId(R.id.tab4);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}