package com.example.bookmanagerproject.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bookmanagerproject.DAO.SachDAO;
import com.example.bookmanagerproject.DAO.ThanhVienDAO;
import com.example.bookmanagerproject.Fragment.PhieuMuonFragment;
import com.example.bookmanagerproject.Model.PhieuMuon;
import com.example.bookmanagerproject.Model.Sach;
import com.example.bookmanagerproject.Model.ThanhVien;
import com.example.bookmanagerproject.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {
    private final Context context;
    PhieuMuonFragment phieuMuonFragment;
    private final ArrayList<PhieuMuon> list;

    TextView txtMaPM, txtTenTVPM, txtTenSachPM, txtTraSach, txtTienThue, txtNgayPM;

    ImageView imgDelPM;
    SachDAO sachDAO;
    ThanhVienDAO thanhVienDAO;
    protected SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public PhieuMuonAdapter(@NonNull Context context, PhieuMuonFragment phieuMuonFragment, ArrayList<PhieuMuon> list) {
        super(context, 0, list);
        this.context = context;
        this.phieuMuonFragment = phieuMuonFragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_phieumuon, null);
        }

        final PhieuMuon phieuMuon = list.get(position);

        if(phieuMuon != null) {
            txtMaPM = view.findViewById(R.id.txtMaPM);
            txtMaPM.setText("Mã Phiếu: "+phieuMuon.getMaPM());

            sachDAO = new SachDAO(context);
            Sach sach = sachDAO.getID(String.valueOf(phieuMuon.getMaSach()));
            txtTenSachPM = view.findViewById(R.id.txtTenSachPM);
            txtTenSachPM.setText("Tên Sách: "+ sach.getTenSach());
            thanhVienDAO = new ThanhVienDAO(context);
            ThanhVien thanhVien = thanhVienDAO.getID(String.valueOf(phieuMuon.getMaTV()));
            txtTenTVPM = view.findViewById(R.id.txtTenTVPM);
            if (thanhVien != null) {
                txtTenTVPM.setText("Thành Viên: " + thanhVien.getHoTen());
            } else {
                // Xử lý trường hợp thanhVien là null
                txtTenTVPM.setText("Thành Viên: Người dùng không tồn tại");
            }
            txtTienThue = view.findViewById(R.id.txtTienThue);
            txtTienThue.setText("Tiền Thuê: "+phieuMuon.getTienThue());
            txtNgayPM = view.findViewById(R.id.txtNgayPM);
            if (phieuMuon != null && phieuMuon.getNgay() != null) {
                txtNgayPM.setText("Ngày Thuê: "+simpleDateFormat.format(phieuMuon.getNgay()));
            } else {
                // Xử lý trường hợp phieuMuon hoặc ngay là null
                txtNgayPM.setText("Ngày Thuê: Ngày không xác định");
            }
            txtTraSach = view.findViewById(R.id.txtTraSach);

            if(phieuMuon.getTraSach() == 1) {
                txtTraSach.setTextColor(Color.WHITE);
                txtTraSach.setText("Đã Trả Sách");
            } else {
                txtTraSach.setTextColor(Color.RED);
                txtTraSach.setText("Chưa Trả Sách");
            }

            imgDelPM = view.findViewById(R.id.imgDelPM);

            imgDelPM.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    phieuMuonFragment.Delete(String.valueOf(phieuMuon.getMaPM()));
                }
            });
        }
        return view;
    }
}
