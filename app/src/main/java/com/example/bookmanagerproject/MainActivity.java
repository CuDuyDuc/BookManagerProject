package com.example.bookmanagerproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmanagerproject.DAO.ThuThuDAO;
import com.example.bookmanagerproject.Fragment.AddAccountFragment;
import com.example.bookmanagerproject.Fragment.ChangePassFragment;
import com.example.bookmanagerproject.Fragment.DoanhThuFragment;
import com.example.bookmanagerproject.Fragment.LoaiSachFragment;
import com.example.bookmanagerproject.Fragment.PhieuMuonFragment;
import com.example.bookmanagerproject.Fragment.SachFragment;
import com.example.bookmanagerproject.Fragment.ThanhVienFragment;
import com.example.bookmanagerproject.Fragment.TopFragment;
import com.example.bookmanagerproject.Model.ThuThu;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private View mHeaderView;
    private TextView edUser;
    private NavigationView navigationView;
    ThuThuDAO thuThuDAO;

    String test = "test";
    String test1="oooo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Mapping();

        // Thiết lập thanh công cụ trên menu
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        // Cho phép hiển thị nút back trên thanh menu
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Setup drawerToggle(dùng để thay đổi hình ảnh của nút toggle trên thanh toolbar)
        // Khi người dùng mở và đóng thanh điều hướng(navigation drawer)
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,
                drawerLayout, toolbar, R.string.open, R.string.close);

        // Bật chức năng hiển thị hình ảnh của DrawerToggle trên toolbar
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        // đồng bộ trạng thái của actionBarDrawerToggle vs trạng thái của drawerLayout
        actionBarDrawerToggle.syncState();

        // gắn kết actionBarDrawerToggle vs drawerLayout
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        // show User in header
        mHeaderView = navigationView.getHeaderView(0);
        edUser = mHeaderView.findViewById(R.id.txtUser);
        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        thuThuDAO = new ThuThuDAO(this);
        ThuThu thuThu = thuThuDAO.getID(user);
        String username = "";
        if (thuThu != null) {
            username = thuThu.getHoTen();
        }
        edUser.setText("Xin Chào! "+username+"");

        // set fragment mặc định khi chạy lên
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_Content, new PhieuMuonFragment()).commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                if(item.getItemId() == R.id.PhieuMuon) {
                    fragment = new PhieuMuonFragment();
                } else if (item.getItemId() == R.id.LoaiSach) {
                   fragment = new LoaiSachFragment();
                } else if (item.getItemId() == R.id.Sach) {
                    fragment = new SachFragment();
                } else if (item.getItemId() == R.id.ThanhVien) {
                    fragment = new ThanhVienFragment();
                } else if (item.getItemId() == R.id.sub_Top) {
                    fragment = new TopFragment();
                } else if (item.getItemId() == R.id.DoanhThu) {
                    fragment = new DoanhThuFragment();
                } else if (item.getItemId() == R.id.add_Acount) {
                    fragment = new AddAccountFragment();
                } else if (item.getItemId() == R.id.changePass) {
                    fragment = new ChangePassFragment();
                } else {
                    startActivity(new Intent(MainActivity.this, Login.class));
                    Toast.makeText(MainActivity.this, "Đăng Xuất Thành Công", Toast.LENGTH_SHORT).show();
                    finish();
                }

                // set title cho toolbar
                getSupportActionBar().setTitle(item.getTitle());
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_Content, fragment).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    private void Mapping() {
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
    }
}