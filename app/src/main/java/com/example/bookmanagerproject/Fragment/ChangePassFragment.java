package com.example.bookmanagerproject.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

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

public class ChangePassFragment extends Fragment {
    View view;
    private TextInputEditText edtPassOld, edtNewPass, edtConfirmPass;
    SharedPreferences sharedPreferences;
    private Button btnSaveChangePass, btnCancelChangePass;
    ThuThuDAO thuThuDAO;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_change_pass, container, false);
        edtPassOld = view.findViewById(R.id.edtPassOld);
        edtNewPass = view.findViewById(R.id.edtNewPass);
        edtConfirmPass = view.findViewById(R.id.edtConfirmPass);
        btnSaveChangePass = view.findViewById(R.id.btnSaveChangePass);
        btnCancelChangePass = view.findViewById(R.id.btnCancelChangePass);
        thuThuDAO = new ThuThuDAO(getActivity());
        
        
        btnSaveChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getActivity().getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
                String user = sharedPreferences.getString("user", "");
                if(validate() > 0) {
                    ThuThu thuThu = thuThuDAO.getID(user);
                    thuThu.setMatKhau(edtNewPass.getText().toString());

                    if(thuThuDAO.updatePass(thuThu) > 0) {
                        Toast.makeText(getActivity(), "Mật khẩu đã được đổi.", Toast.LENGTH_SHORT).show();
                        reset();
                    } else {
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu không thành công.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnCancelChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        
        return view;
    }

    public int validate() {
        int check = 1;
        if(edtPassOld.getText().length() == 0 || edtNewPass.getText().length() == 0 || edtConfirmPass.getText().length() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Cảnh Báo");
            builder.setIcon(R.drawable.warning_icon);
            builder.setMessage("Dữ liệu nhập vào không được trống.");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();

            check = -1;
        } else {
            sharedPreferences = getActivity().getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
            String passOld = sharedPreferences.getString("password", "");
            String newPass = edtNewPass.getText().toString().trim();
            String confirmNewPass = edtConfirmPass.getText().toString().trim();
            if(!passOld.equals(edtPassOld.getText().toString())) {
                Toast.makeText(getActivity(), "Mật khẩu cũ nhập sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if(!newPass.equals(confirmNewPass)) {
                Toast.makeText(getActivity(), "Mật khẩu mới và xác nhận mật khẩu mới không trùng khớp.", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
    
    public void reset() {
        edtPassOld.setText("");
        edtNewPass.setText("");
        edtConfirmPass.setText("");
    }
}