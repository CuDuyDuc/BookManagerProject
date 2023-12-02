package com.example.bookmanagerproject.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.bookmanagerproject.DAO.ThuThuDAO;
import com.example.bookmanagerproject.MainActivity;
import com.example.bookmanagerproject.Model.ThuThu;
import com.example.bookmanagerproject.R;
import com.google.android.material.textfield.TextInputEditText;

public class AddAccountFragment extends Fragment {
    View view;
    TextInputEditText edtAddMaTT, edtAddUserName, edtAddPassword, edtAddRePassword;
    Button btnAddAccount, btnCancelAddAccount;

    ThuThuDAO thuThuDAO;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_account, container, false);
        edtAddMaTT = view.findViewById(R.id.edtAddMaTT);
        edtAddUserName = view.findViewById(R.id.edtAddUserName);
        edtAddPassword = view.findViewById(R.id.edtAddPassword);
        edtAddRePassword = view.findViewById(R.id.edtAddRePassword);
        btnAddAccount = view.findViewById(R.id.btnAddAccount);
        btnCancelAddAccount = view.findViewById(R.id.btnCancelAddAccount);
        thuThuDAO = new ThuThuDAO(getContext());

        btnAddAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maThuThu = edtAddMaTT.getText().toString();
                String hoTen = edtAddUserName.getText().toString();
                String matKhau = edtAddPassword.getText().toString();
                String nhapLaiMatKhau = edtAddRePassword.getText().toString();

                if(maThuThu.equals("") || hoTen.equals("") || matKhau.equals("") || nhapLaiMatKhau.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Cảnh Báo");
                    builder.setMessage("Thông tin không được trống");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

                if(!matKhau.equals(nhapLaiMatKhau)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setIcon(R.drawable.warning_icon);
                    builder.setTitle("Cảnh Báo");
                    builder.setMessage("Mật khẩu và mật khẩu nhập lại không trùng khớp.\n Vui lòng kiểm tra lại!");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    ThuThu thuThu = new ThuThu(maThuThu, hoTen, matKhau);
                    boolean check = thuThuDAO.insert(thuThu);
                    if(check) {
                        Toast.makeText(getActivity(), "Thêm Tài Khoản Thành Công", Toast.LENGTH_SHORT).show();
                        reSet();
                    } else {
                        Toast.makeText(getActivity(), "Thêm Tài Khoản Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnCancelAddAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    public void reSet() {
        edtAddMaTT.setText("");
        edtAddUserName.setText("");
        edtAddPassword.setText("");
        edtAddRePassword.setText("");
    }
}