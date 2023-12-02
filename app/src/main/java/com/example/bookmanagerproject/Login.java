package com.example.bookmanagerproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmanagerproject.DAO.ThuThuDAO;
import com.example.bookmanagerproject.SendMail.SendMail;
import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {
    private EditText edtUserName, edtPassword;
    private CheckBox chkMiss;
    private Button btnLogin;

    private TextView txtForgotPass;
    private SharedPreferences sharedPreferences;
    private SendMail sendMail;
    ThuThuDAO thuThuDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Mapping();

        // Khởi tạo SharedPreferences
        sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        // Kiểm tra xem đã lưu user và password hay chưa
        if (sharedPreferences.contains("user") && sharedPreferences.contains("password")) {
            String savedUser = sharedPreferences.getString("user", "");
            String savedPassword = sharedPreferences.getString("password", "");

            edtUserName.setText(savedUser);
            edtPassword.setText(savedPassword);
            chkMiss.setChecked(true); // Đánh dấu checkbox "Remember Me"
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUserName.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                // Kiểm tra nếu checkbox "Remember Me" được chọn
                if (chkMiss.isChecked()) {
                    Toast.makeText(Login.this, "Tài khoản đã được nhớ.", Toast.LENGTH_SHORT).show();
                    // Lưu email và password vào SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("user", user);
                    editor.putString("password", password);
                    editor.apply();
                }
                if(user.equals("") || password.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setTitle("Cảnh Báo");
                    builder.setIcon(R.drawable.warning_icon);
                    builder.setMessage("Email và mật khẩu không được trống");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    if (thuThuDAO.checkLogin(user, password) > 0) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        intent.putExtra("user", user);
                        startActivity(intent);
                        finishAffinity(); // Đóng tất cả các tab trước Main_Activity
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(Login.this, "Đăng nhập thất bại.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        txtForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogForgot();
            }
        });
    }

    private void Mapping() {
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        chkMiss = findViewById(R.id.chkMiss);
        txtForgotPass = findViewById(R.id.txtForgotPass);
        btnLogin = findViewById(R.id.btnLogin);
        thuThuDAO = new ThuThuDAO(this);
        sendMail = new SendMail();
    }

    private void showDialogForgot() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.forgot_password, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);
        alertDialog.show();

        TextInputEditText edtSendEmail = view.findViewById(R.id.edtSendEmail);
        Button btnSendEmail = view.findViewById(R.id.btnSendEmail);
        Button btnBackToLogin = view.findViewById(R.id.btnBackToLogin);

        btnBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                alertDialog.dismiss();
            }
        });

        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtSendEmail.getText().toString();
                String matKhau = thuThuDAO.ForgotPassword(email);

                if(matKhau.equals("")) {
                    Toast.makeText(Login.this, "Không tìm thấy tài khoản.", Toast.LENGTH_SHORT).show();
                } else {
                    sendMail.Send(Login.this, email, "MẬT KHẨU ỨNG DỤNG BỊ QUÊN", "Mật khẩu của bạn là: "+matKhau);
                    alertDialog.dismiss();
                }
            }
        });
    }

    boolean doubleBack = false;

    @Override
    public void onBackPressed() {
        if (doubleBack) {
            super.onBackPressed();
            return;
        }

        this.doubleBack = true;
        Toast.makeText(this, "Bấm thêm 1 lần nữa để thoát", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBack=false;
            }
        }, 2000);
    }
}