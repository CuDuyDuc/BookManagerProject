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
import android.widget.ListView;
import android.widget.Toast;

import com.example.bookmanagerproject.Adapter.ThanhVienAdapter;
import com.example.bookmanagerproject.DAO.ThanhVienDAO;
import com.example.bookmanagerproject.Model.ThanhVien;
import com.example.bookmanagerproject.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class ThanhVienFragment extends Fragment {
    View view;
    FloatingActionButton btnFloatAdd;

    TextInputEditText edtMaTV, edtTenTV, edtNamSinh;

    Button btnSaveTV, btnCancelTV;

    Dialog dialog;
    ListView listThanhVien;
    ArrayList<ThanhVien> list;
    ThanhVienDAO thanhVienDAO;
    ThanhVienAdapter thanhVienAdapter;
    ThanhVien thanhVien;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_thanh_vien, container, false);

        listThanhVien = view.findViewById(R.id.listThanhVien);
        thanhVienDAO = new ThanhVienDAO(getActivity());
        UpdateListThanhVien();
        btnFloatAdd = view.findViewById(R.id.btnFloatAdd);
        btnFloatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });

        listThanhVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                thanhVien = list.get(position);
                openDialog(getActivity(), 1);
                return false;
            }
        });
        return view;
    }

    public void UpdateListThanhVien() {
        list = (ArrayList<ThanhVien>) thanhVienDAO.getAll();
        thanhVienAdapter = new ThanhVienAdapter(getActivity(), this, list);
        listThanhVien.setAdapter(thanhVienAdapter);
    }

    public void Delete(final String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có chắc muốn xóa thành viên này không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                thanhVienDAO.delete(id);
                // cập nhật ListView
                UpdateListThanhVien();
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

    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.thanhvien_dialog);
        edtMaTV = dialog.findViewById(R.id.edtMaTV);
        edtTenTV = dialog.findViewById(R.id.edtTenTV);
        edtNamSinh = dialog.findViewById(R.id.edtNamSinh);
        btnSaveTV = dialog.findViewById(R.id.btnSaveTV);
        btnCancelTV = dialog.findViewById(R.id.btnCancelTV);

        edtMaTV.setEnabled(false);
        if(type != 0) {
            edtMaTV.setText(String.valueOf(thanhVien.getMaTV()));
            edtTenTV.setText(thanhVien.getHoTen());
            edtNamSinh.setText(thanhVien.getNamSinh());
        }
        btnSaveTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thanhVien = new ThanhVien();
                thanhVien.setHoTen(edtTenTV.getText().toString());
                thanhVien.setNamSinh(edtNamSinh.getText().toString());

                if(validate() > 0) {
                    if(type == 0) {
                        if(thanhVienDAO.insert(thanhVien) > 0) {
                            Toast.makeText(context, "Thêm thành viên thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm thành viên thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        thanhVien.setMaTV(Integer.parseInt(edtMaTV.getText().toString()));
                        if(thanhVienDAO.updateTV(thanhVien) > 0) {
                            Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Update thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    UpdateListThanhVien();
                    dialog.dismiss();
                }
            }
        });

        btnCancelTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public int validate() {
        int check = 1;
        if(edtTenTV.getText().length() == 0 || edtNamSinh.getText().length() == 0) {
            Toast.makeText(getContext(), "Thông tin phải nhập đầy đủ ", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}