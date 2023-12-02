package com.example.bookmanagerproject.SplashScreen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bookmanagerproject.Adapter.ViewPagerAdapter;
import com.example.bookmanagerproject.R;

import me.relex.circleindicator.CircleIndicator;

public class SplashScreen extends AppCompatActivity {
    private TextView txtSkip;
    private ViewPager viewPager;
    private RelativeLayout layout_bottom;
    private CircleIndicator circleIndicator;
    private LinearLayout layout_Next;

    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Mapping();
        // tạo một cờ đánh dấu cho việc cập nhật các fragment trạng thái được duyệt.
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);

        // gắn viewPager vào CircleIndicator
        circleIndicator.setViewPager(viewPager);

        txtSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
            }
        });

        layout_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager.getCurrentItem() < 2) { // nếu viewPager chưa tới cuối trang thì sẽ + 1 qua trang mới đến khi tới cuối trang
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 2) {
                    txtSkip.setVisibility(View.GONE);
                    layout_bottom.setVisibility(View.GONE);
                } else {
                    txtSkip.setVisibility(View.VISIBLE);
                    layout_bottom.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void Mapping() {
        txtSkip = findViewById(R.id.txtSkip);
        viewPager = findViewById(R.id.view_pager);
        layout_bottom = findViewById(R.id.layout_bottom);
        circleIndicator = findViewById(R.id.circle_Indicator);
        layout_Next = findViewById(R.id.layout_Next);
    }
}