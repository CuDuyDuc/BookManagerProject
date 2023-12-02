package com.example.bookmanagerproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bookmanagerproject.Model.ThanhVien;
import com.example.bookmanagerproject.R;

import java.util.ArrayList;

public class ThanhVienSpinnerAdapter extends ArrayAdapter<ThanhVien> {
    Context context;
    ArrayList<ThanhVien> list;
    TextView txtMaTVSpinner, txtTenTVSpinner;

    public ThanhVienSpinnerAdapter(@NonNull Context context, ArrayList<ThanhVien> list) {
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
            view = layoutInflater.inflate(R.layout.item_thanhvien_spinner, null);
        }

        final ThanhVien thanhVien = list.get(position);
        if(thanhVien != null) {
            txtMaTVSpinner = view.findViewById(R.id.txtMaTVSpinner);
            txtMaTVSpinner.setText(thanhVien.getMaTV()+". ");
            txtTenTVSpinner = view.findViewById(R.id.txtTenTVSpinner);
            txtTenTVSpinner.setText(thanhVien.getHoTen());
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_thanhvien_spinner, null);
        }

        final ThanhVien thanhVien = list.get(position);
        if(thanhVien != null) {
            txtMaTVSpinner = view.findViewById(R.id.txtMaTVSpinner);
            txtMaTVSpinner.setText(thanhVien.getMaTV()+". ");
            txtTenTVSpinner = view.findViewById(R.id.txtTenTVSpinner);
            txtTenTVSpinner.setText(thanhVien.getHoTen());
        }
        return view;
    }
}
