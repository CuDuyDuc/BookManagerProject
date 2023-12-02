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

import com.example.bookmanagerproject.DAO.LoaiSachDAO;
import com.example.bookmanagerproject.Fragment.SachFragment;
import com.example.bookmanagerproject.Model.LoaiSach;
import com.example.bookmanagerproject.Model.Sach;
import com.example.bookmanagerproject.R;

import java.util.List;

public class SachAdapter extends ArrayAdapter<Sach> {
    private TextView txtMaSach, txtTenSach, txtGiaThue, txtLoaiSach;
    private ImageView imgDel;
    Context context;
    SachFragment sachFragment;
    List<Sach> list;

    public SachAdapter(@NonNull Context context, SachFragment sachFragment, List<Sach> list) {
        super(context, 0, list);
        this.context = context;
        this.sachFragment = sachFragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if(view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_sach, null);
        }

        final Sach sach = list.get(position);
        if(sach != null) {
            LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
            LoaiSach loaiSach = loaiSachDAO.getID(String.valueOf(sach.getMaLoai()));
            txtMaSach = view.findViewById(R.id.txtMaSach);
            txtMaSach.setText("Mã Sách: " + sach.getMaSach());
            txtTenSach = view.findViewById(R.id.txtTenSach);
            txtTenSach.setText("Tên Sách: " + sach.getTenSach());
            txtGiaThue = view.findViewById(R.id.txtGiaThue);
            txtGiaThue.setText("Giá Thuê: " + sach.getGiaThue());

            if (loaiSach != null) {
                txtLoaiSach = view.findViewById(R.id.txtLoaiSach);
                txtLoaiSach.setText("Loại Sách: " + loaiSach.getTenLoai());
            } else {
                // Xử lý trường hợp không tìm thấy LoạiSách tương ứng
                txtLoaiSach = view.findViewById(R.id.txtLoaiSach);
                txtLoaiSach.setText("Loại Sách: Không xác định"); // Hiển thị giá trị mặc định
            }

            imgDel = view.findViewById(R.id.imgDel);
        }

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sachFragment.Delete(String.valueOf(sach.getMaSach()));
            }
        });
        return view;
    }
}
