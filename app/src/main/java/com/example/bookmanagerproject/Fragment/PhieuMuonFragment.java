package com.example.bookmanagerproject.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bookmanagerproject.Adapter.PhieuMuonAdapter;
import com.example.bookmanagerproject.Adapter.SachSpinnerAdapter;
import com.example.bookmanagerproject.Adapter.ThanhVienSpinnerAdapter;
import com.example.bookmanagerproject.DAO.PhieuMuonDAO;
import com.example.bookmanagerproject.DAO.SachDAO;
import com.example.bookmanagerproject.DAO.ThanhVienDAO;
import com.example.bookmanagerproject.Model.PhieuMuon;
import com.example.bookmanagerproject.Model.Sach;
import com.example.bookmanagerproject.Model.ThanhVien;
import com.example.bookmanagerproject.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PhieuMuonFragment extends Fragment {
    View view;

    ListView listPhieuMuon;
    ArrayList<PhieuMuon> list;
    PhieuMuonDAO phieuMuonDAO;
    PhieuMuonAdapter phieuMuonAdapter;
    PhieuMuon phieuMuon;

    FloatingActionButton btnFloatAddPM;
    Dialog dialog;
    TextInputEditText edtMaPM, edtNgay, edtTienThue;
    Spinner spinnerMaTV, spinnerMaSach;
    CheckBox chkTraSach;
    Button btnSavePM, btnCancelPM;

    ThanhVienSpinnerAdapter thanhVienSpinnerAdapter;
    ArrayList<ThanhVien> listThanhVien;
    ThanhVienDAO thanhVienDAO;
    ThanhVien thanhVien;
    int maThanhVien;


    SachSpinnerAdapter sachSpinnerAdapter;
    ArrayList<Sach> listSach;
    SachDAO sachDAO;
    Sach sach;
    int maSach, tienThue;
    int positionTV, positionSach;

    SimpleDateFormat simpleDateFormat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        listPhieuMuon = view.findViewById(R.id.listPhieuMuon);
        phieuMuonDAO = new PhieuMuonDAO(getActivity());
        btnFloatAddPM = view.findViewById(R.id.btnFloatAddPM);
        phieuMuonDAO = new PhieuMuonDAO(getActivity());
        btnFloatAddPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        listPhieuMuon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                phieuMuon = list.get(position);
                openDialog(getActivity(), 1);
                return false;
            }
        });
        UpdateListPhieuMuon();

        return view;
    }
    public void UpdateListPhieuMuon() {
        list = (ArrayList<PhieuMuon>) phieuMuonDAO.getAll();
        phieuMuonAdapter = new PhieuMuonAdapter(getActivity(), this, list);
        listPhieuMuon.setAdapter(phieuMuonAdapter);
    }

    protected void openDialog(final Context context, final int type){
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.phieumuon_dialog);
        edtMaPM = dialog.findViewById(R.id.edtMaPM);
        edtMaPM.setEnabled(false);
        edtNgay = dialog.findViewById(R.id.edtNgay);
        edtTienThue = dialog.findViewById(R.id.edtTienThue);
        spinnerMaTV = dialog.findViewById(R.id.spinnerMaTV);
        spinnerMaSach = dialog.findViewById(R.id.spinnerMaSach);
        chkTraSach = dialog.findViewById(R.id.chkTraSach);
        btnSavePM = dialog.findViewById(R.id.btnSavePM);
        btnCancelPM = dialog.findViewById(R.id.btnCancelPM);

        edtNgay.setText("Ngày Thuê: "+simpleDateFormat.format(new Date()));

        thanhVienDAO = new ThanhVienDAO(context);
        listThanhVien = new ArrayList<>();
        listThanhVien = (ArrayList<ThanhVien>) thanhVienDAO.getAll();
        thanhVienSpinnerAdapter = new ThanhVienSpinnerAdapter(context, listThanhVien);
        spinnerMaTV.setAdapter(thanhVienSpinnerAdapter);
        spinnerMaTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maThanhVien = listThanhVien.get(position).getMaTV();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        sachDAO = new SachDAO(context);
        listSach = new ArrayList<>();
        listSach = (ArrayList<Sach>) sachDAO.getAll();
        sachSpinnerAdapter = new SachSpinnerAdapter(context, listSach);
        spinnerMaSach.setAdapter(sachSpinnerAdapter);
        spinnerMaSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSach = listSach.get(position).getMaSach();
                tienThue = listSach.get(position).getGiaThue();
                edtTienThue.setText("Tiền Thuê: "+ tienThue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if(type != 0) {
            edtMaPM.setText(String.valueOf(phieuMuon.getMaPM()));
            for(int i = 0; i < listThanhVien.size(); i++) {
                if(phieuMuon.getMaTV() == (listThanhVien.get(i).getMaTV())) {
                    positionTV = i;
                }
                spinnerMaTV.setSelection(positionTV);
            }

            for(int i = 0; i < listSach.size(); i++) {
                if(phieuMuon.getMaSach() == (listSach.get(i).getMaSach())) {
                    positionSach = i;
                }
                spinnerMaSach.setSelection(positionSach);
            }
            edtNgay.setText("Ngày Thuê: "+simpleDateFormat.format(phieuMuon.getNgay()));
            edtTienThue.setText("Tiền Thuê: "+phieuMuon.getTienThue());

            if(phieuMuon.getTraSach() == 1) {
                chkTraSach.setChecked(true);
            } else {
                chkTraSach.setChecked(false);
            }
        }

        btnCancelPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSavePM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phieuMuon = new PhieuMuon();
                phieuMuon.setMaSach(maSach);
                phieuMuon.setMaTV(maThanhVien);
                phieuMuon.setNgay(new Date());
                phieuMuon.setTienThue(tienThue);
                if(chkTraSach.isChecked()) {
                    phieuMuon.setTraSach(1);
                } else {
                    phieuMuon.setTraSach(0);
                }

                if(type == 0) {
                    if(phieuMuonDAO.insert(phieuMuon) > 0) {
                        Toast.makeText(context, "Thêm Phiếu Mượn Thành Công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Thêm Phiếu Mượn Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    phieuMuon.setMaPM(Integer.parseInt(edtMaPM.getText().toString()));
                    if(phieuMuonDAO.updatePhieuMuon(phieuMuon) > 0) {
                        Toast.makeText(context, "Update Phiếu Mượn Thành Công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Update Phiếu Mượn Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                }
                UpdateListPhieuMuon();
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void Delete(final String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có chắc muốn xóa phiếu mượn này không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                phieuMuonDAO.delete(id);
                // cập nhật ListView
                UpdateListPhieuMuon();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}