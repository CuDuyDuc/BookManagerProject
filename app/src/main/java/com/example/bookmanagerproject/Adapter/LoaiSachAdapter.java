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

import com.example.bookmanagerproject.Fragment.LoaiSachFragment;
import com.example.bookmanagerproject.Model.LoaiSach;
import com.example.bookmanagerproject.R;

import java.util.List;

public class LoaiSachAdapter extends ArrayAdapter<LoaiSach> {

    private TextView txtMaLoai, txtTenLoaiSach;
    private ImageView imgDel;
    Context context;
    LoaiSachFragment loaiSachFragment;
    List<LoaiSach> list;

    public LoaiSachAdapter(@NonNull Context context, LoaiSachFragment loaiSachFragment, List<LoaiSach> list) {
        super(context, 0, list);
        this.context = context;
        this.loaiSachFragment = loaiSachFragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_loaisach, null);
        }
        final LoaiSach itemLoaiSach = list.get(position);
        if(itemLoaiSach != null) {
            txtMaLoai = view.findViewById(R.id.txtMaLoai);
            txtMaLoai.setText("Mã Loại Sách: " +itemLoaiSach.getMaLoai());

            txtTenLoaiSach = view.findViewById(R.id.txtTenLoaiSach);
            txtTenLoaiSach.setText("Tên Loạị Sách: "+itemLoaiSach.getTenLoai());

            imgDel = view.findViewById(R.id.imgDel);
        }

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // gọi phương thức xóa
                loaiSachFragment.Delete(String.valueOf(itemLoaiSach.getMaLoai()));
            }
        });
        return view;
    }
}
