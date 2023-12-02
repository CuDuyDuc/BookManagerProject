package com.example.bookmanagerproject.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bookmanagerproject.Adapter.LoaiSachSpinnerAdapter;
import com.example.bookmanagerproject.Adapter.SachAdapter;
import com.example.bookmanagerproject.DAO.LoaiSachDAO;
import com.example.bookmanagerproject.DAO.SachDAO;
import com.example.bookmanagerproject.Model.LoaiSach;
import com.example.bookmanagerproject.Model.Sach;
import com.example.bookmanagerproject.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class SachFragment extends Fragment {
    ListView listSach;
    SachDAO sachDAO;

    FloatingActionButton btnFloatAdd;
    Dialog dialog;
    TextInputEditText edtMaSach, edtTenSach, edtGiaThue;
    Spinner spinnerLoaiSach;
    Button btnSaveS, btnCancelS;
    SachAdapter sachAdapter;
    Sach sach;
    List<Sach> list;

    LoaiSachSpinnerAdapter loaiSachSpinnerAdapter;
    ArrayList<LoaiSach> listLoaiSach;
    LoaiSachDAO loaiSachDAO;
    int maLoaiSach, position;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sach, container, false);
        listSach = view.findViewById(R.id.listSach);
        sachDAO = new SachDAO(getActivity());
        UpdateListSach();
        btnFloatAdd = view.findViewById(R.id.btnFloatAdd);
        btnFloatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });

        listSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                sach = list.get(position);
                openDialog(getActivity(), 1);
                return false;
            }
        });
        return view;
    }

    public void UpdateListSach() {
        list = (ArrayList<Sach>) sachDAO.getAll();
        sachAdapter = new SachAdapter(getActivity(), this, list);
        listSach.setAdapter(sachAdapter);
    }

    public void Delete(final String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có chắc muốn xóa sách này không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sachDAO.delete(id);
                // cập nhật ListView
                UpdateListSach();
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

    protected void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.sach_dialog);
        edtMaSach = dialog.findViewById(R.id.edtMaSach);
        edtTenSach = dialog.findViewById(R.id.edtTenSach);
        edtGiaThue = dialog.findViewById(R.id.edtGiaThue);
        spinnerLoaiSach = dialog.findViewById(R.id.spinnerLoaiSach);
        btnSaveS = dialog.findViewById(R.id.btnSaveS);
        btnCancelS = dialog.findViewById(R.id.btnCancelS);

        listLoaiSach = new ArrayList<>();
        loaiSachDAO = new LoaiSachDAO(context);
        listLoaiSach = (ArrayList<LoaiSach>) loaiSachDAO.getAll();

        loaiSachSpinnerAdapter = new LoaiSachSpinnerAdapter(context, listLoaiSach);
        spinnerLoaiSach.setAdapter(loaiSachSpinnerAdapter);

        spinnerLoaiSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiSach = listLoaiSach.get(position).getMaLoai();
                Toast.makeText(context, "Chọn"+ listLoaiSach.get(position).getTenLoai(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        edtMaSach.setEnabled(false);
        if(type != 0) {
            edtMaSach.setText(String.valueOf(sach.getMaSach()));
            edtTenSach.setText(sach.getTenSach());
            edtGiaThue.setText(String.valueOf(sach.getGiaThue()));

            for (int i = 0; i < listLoaiSach.size(); i++) {
                if(sach.getMaLoai() == (listLoaiSach.get(i).getMaLoai())) {
                    position = i;
                }
                Log.i("sach", "posSach"+position);
                spinnerLoaiSach.setSelection(position);
            }
        }

        btnSaveS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sach = new Sach();
                sach.setTenSach(edtTenSach.getText().toString());
                sach.setGiaThue(Integer.parseInt(edtGiaThue.getText().toString()));
                sach.setMaLoai(maLoaiSach);

                if(validate() > 0) {
                    if(type == 0) {
                        if(sachDAO.insert(sach) > 0) {
                            Toast.makeText(context, "Thêm sách thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm sách thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        sach.setMaSach(Integer.parseInt(edtMaSach.getText().toString()));
                        if(sachDAO.updateSach(sach) > 0) {
                            Toast.makeText(context, "Update sách thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Update sách thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    UpdateListSach();
                    dialog.dismiss();
                }
            }
        });

        btnCancelS.setOnClickListener(new View.OnClickListener() {
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
        if(edtTenSach.getText().length() == 0 || edtGiaThue.getText().length() == 0) {
            Toast.makeText(getContext(), "Thông tin phải nhập đầy đủ", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}