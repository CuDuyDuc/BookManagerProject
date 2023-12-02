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

import com.example.bookmanagerproject.Adapter.LoaiSachAdapter;
import com.example.bookmanagerproject.DAO.LoaiSachDAO;
import com.example.bookmanagerproject.Model.LoaiSach;
import com.example.bookmanagerproject.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class LoaiSachFragment extends Fragment {
    View view;

    private FloatingActionButton btnFloatAdd;

    private TextInputEditText edtMaLoaiSach, edtTenLoaiSach;

    private Button btnSaveLS, btnCancelLS;

    private Dialog dialog;
    private ListView listLoaiSach;

    ArrayList<LoaiSach> list;
    LoaiSachDAO loaiSachDAO;
    LoaiSachAdapter loaiSachAdapter;
    LoaiSach loaiSach;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_loai_sach, container, false);

        listLoaiSach = view.findViewById(R.id.listLoaiSach);
        loaiSachDAO = new LoaiSachDAO(getActivity());
        UpdateListLoaiSach();
        btnFloatAdd = view.findViewById(R.id.btnFloatAdd);
        btnFloatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        listLoaiSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                loaiSach = list.get(position);
                openDialog(getActivity(), 1);
                return false;
            }
        });

        return view;
    }

    public void UpdateListLoaiSach() {
        list = (ArrayList<LoaiSach>) loaiSachDAO.getAll();
        loaiSachAdapter = new LoaiSachAdapter(getActivity(), this, list);
        listLoaiSach.setAdapter(loaiSachAdapter);
    }

    public void Delete(final String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có chắc muốn xóa thành viên này không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                loaiSachDAO.Delete(id);
                // cập nhật ListView
                UpdateListLoaiSach();
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
        dialog.setContentView(R.layout.loaisach_dialog);
        edtMaLoaiSach = dialog.findViewById(R.id.edtMaLoaiSach);
        edtTenLoaiSach = dialog.findViewById(R.id.edtTenLoaiSach);
        btnSaveLS = dialog.findViewById(R.id.btnSaveLS);
        btnCancelLS = dialog.findViewById(R.id.btnCancelLS);

        edtMaLoaiSach.setEnabled(false);
        if(type != 0) {
            edtMaLoaiSach.setText(String.valueOf(loaiSach.getMaLoai()));
            edtTenLoaiSach.setText(loaiSach.getTenLoai());
        }

        btnSaveLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaiSach = new LoaiSach();
                loaiSach.setTenLoai(edtTenLoaiSach.getText().toString());

                if(validate() > 0) {
                    if(type == 0) {
                        if(loaiSachDAO.insert(loaiSach) > 0) {
                            Toast.makeText(context, "Thêm loại sách thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm loại sách thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        loaiSach.setMaLoai(Integer.parseInt(edtMaLoaiSach.getText().toString()));
                        if(loaiSachDAO.updateLS(loaiSach) > 0) {
                            Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Update thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    UpdateListLoaiSach();
                    dialog.dismiss();
                }
            }
        });

        btnCancelLS.setOnClickListener(new View.OnClickListener() {
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
        if(edtTenLoaiSach.getText().length() == 0) {
            Toast.makeText(getContext(), "Thông tin phải nhập đầy đủ ", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}