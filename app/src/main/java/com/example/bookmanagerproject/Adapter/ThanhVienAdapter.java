package com.example.bookmanagerproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bookmanagerproject.Fragment.ThanhVienFragment;
import com.example.bookmanagerproject.Model.ThanhVien;
import com.example.bookmanagerproject.R;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    ThanhVienFragment fragment;
    private ArrayList<ThanhVien> list;

    private TextView txtMaTV, txtTenTV, txtNamSinh;
    private ImageView imgDel;

    public ThanhVienAdapter(@NonNull Context context, ThanhVienFragment fragment, ArrayList<ThanhVien> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_thanhvien, null);
        }
        final ThanhVien itemThanhVien = list.get(position);
        if(itemThanhVien != null) {
            txtMaTV = view.findViewById(R.id.txtMaTV);
            txtMaTV.setText("Mã TV: " + itemThanhVien.getMaTV());

            txtTenTV = view.findViewById(R.id.txtTenTV);
            txtTenTV.setText("Tên TV: "+ itemThanhVien.getHoTen());

            txtNamSinh = view.findViewById(R.id.txtNamSinh);
            txtNamSinh.setText("Năm Sinh: "+ itemThanhVien.getNamSinh());

            imgDel = view.findViewById(R.id.imgDel);
        }

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // gọi phương thức xóa
                fragment.Delete(String.valueOf(itemThanhVien.getMaTV()));
            }
        });

        return view;
    }
}
