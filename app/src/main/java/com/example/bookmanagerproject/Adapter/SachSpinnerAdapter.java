package com.example.bookmanagerproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bookmanagerproject.Model.Sach;
import com.example.bookmanagerproject.R;

import java.util.ArrayList;

public class SachSpinnerAdapter extends ArrayAdapter<Sach> {
    Context context;
    private ArrayList<Sach> list;

    TextView txtMaSachSpinner, txtTenSachSpinner;

    public SachSpinnerAdapter(@NonNull Context context, ArrayList<Sach> list) {
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
            view = layoutInflater.inflate(R.layout.item_sach_spinner, null);
        }

        final Sach sach = list.get(position);
        if(sach != null) {
            txtMaSachSpinner = view.findViewById(R.id.txtMaSachSpinner);
            txtMaSachSpinner.setText(sach.getMaSach()+ ". ");
            txtTenSachSpinner = view.findViewById(R.id.txtTenSachSpinner);
            txtTenSachSpinner.setText(sach.getTenSach());
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_sach_spinner, null);
        }

        final Sach sach = list.get(position);
        if(sach != null) {
            txtMaSachSpinner = view.findViewById(R.id.txtMaSachSpinner);
            txtMaSachSpinner.setText(sach.getMaSach()+ ". ");
            txtTenSachSpinner = view.findViewById(R.id.txtTenSachSpinner);
            txtTenSachSpinner.setText(sach.getTenSach());
        }
        return view;
    }
}
