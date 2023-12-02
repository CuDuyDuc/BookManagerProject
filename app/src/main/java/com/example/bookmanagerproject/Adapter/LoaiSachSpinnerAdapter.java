package com.example.bookmanagerproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bookmanagerproject.Model.LoaiSach;
import com.example.bookmanagerproject.R;

import java.util.ArrayList;

public class LoaiSachSpinnerAdapter extends ArrayAdapter<LoaiSach> {
    Context context;
    ArrayList<LoaiSach> list;

    private TextView txtMaLoaiSachSP, txtTenLoaiSachSP;

    public LoaiSachSpinnerAdapter(@NonNull Context context, ArrayList<LoaiSach> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.loaisach_spinner, null);
        }

        final LoaiSach loaiSach = list.get(position);
        if(loaiSach != null) {
            txtMaLoaiSachSP = view.findViewById(R.id.txtMaLoaiSachSP);
            txtMaLoaiSachSP.setText(loaiSach.getMaLoai()+".  ");

            txtTenLoaiSachSP = view.findViewById(R.id.txtTenLoaiSachSP);
            txtTenLoaiSachSP.setText(loaiSach.getTenLoai());
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.loaisach_spinner, null);
        }

        final LoaiSach loaiSach = list.get(position);
        if(loaiSach != null) {
            txtMaLoaiSachSP = view.findViewById(R.id.txtMaLoaiSachSP);
            txtMaLoaiSachSP.setText(loaiSach.getMaLoai()+".  ");

            txtTenLoaiSachSP = view.findViewById(R.id.txtTenLoaiSachSP);
            txtTenLoaiSachSP.setText(loaiSach.getTenLoai());
        }
        return view;
    }
}
