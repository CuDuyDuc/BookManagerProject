package com.example.bookmanagerproject.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bookmanagerproject.Database.DbHelper;
import com.example.bookmanagerproject.Model.LoaiSach;
import com.example.bookmanagerproject.Model.Sach;
import com.example.bookmanagerproject.Model.ThanhVien;
import com.example.bookmanagerproject.Model.ThuThu;

public class CreateDB {
    private SQLiteDatabase db;

    ThanhVienDAO thanhVienDAO;
    ThuThuDAO thuThuDAO;

    LoaiSachDAO loaiSachDAO;
    SachDAO sachDAO;

    PhieuMuonDAO phieuMuonDAO;

    public CreateDB(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        thanhVienDAO = new ThanhVienDAO(context);
        thuThuDAO = new ThuThuDAO(context);
        loaiSachDAO = new LoaiSachDAO(context);
        sachDAO = new SachDAO(context);
        phieuMuonDAO = new PhieuMuonDAO(context);
    }
    public void thanhVien() {
        ThanhVien tv = new ThanhVien(1, "Đức", "2001");
        if (thanhVienDAO.insert(tv) > 0) {
            Log.i("//==========","Thêm thành viên thành công");
        } else {
            Log.i("//==========","Thêm thành viên không thành công");
        }
        Log.i("//===========", "==============================");
        Log.i("//===========", "Tổng số thành viên: "+thanhVienDAO.getAll().size());

        Log.i("//===========", "=============Sau Update=================");
        tv = new ThanhVien(1, "Phước", "2004");
        thanhVienDAO.updateTV(tv);
        Log.i("//===========", "Tổng số thành viên: "+thanhVienDAO.getAll().size());

//        thanhVienDAO.delete("1");
//        Log.i("//===========", "===============Sau Delete===============");
//        Log.i("//===========", "Tổng số thành viên: "+thanhVienDAO.getAll().size());
    }

//    public void thuThu() {
//        ThuThu thuThu = new ThuThu("abc", "Nguyễn Văn Tuấn", "tuan123");
//        if (thuThuDAO.insert(thuThu) > 0) {
//            Log.i("//==========","Thêm thành viên thành công");
//        } else {
//            Log.i("//==========","Thêm thành viên không thành công");
//        }
//
//        thuThu = new ThuThu("abc", "Nguyễn Văn Tuấn", "123456");
//        thuThuDAO.updatePass(thuThu);
//        Log.i("//===========", "==============Sau Update================");
//        Log.i("//===========", "Tổng số thành viên: "+thuThuDAO.getAll().size());
//
////        thuThuDAO.delete("abc");
////        Log.i("//===========", "===============Sau Delete===============");
////        Log.i("//===========", "Tổng số thành viên: "+thuThuDAO.getAll().size());
//
//        if(thuThuDAO.checkLogin("abc", "123456") > 0) {
//            Log.i("//===========", "Đăng nhập thành công");
//        } else {
//            Log.i("//===========", "Đăng nhập thất bại");
//        }
//    }

    public void LoaiSach() {
        LoaiSach loaiSach = new LoaiSach(1, "Sách Tiếng Anh");
        if (loaiSachDAO.insert(loaiSach) > 0) {
            Log.i("//==========","Thêm loại sách thành công");
        } else {
            Log.i("//==========","Thêm loại sách không thành công");
        }

        loaiSach = new LoaiSach(1, "Sách Công Nghệ Thông Tin");
        loaiSachDAO.updateLS(loaiSach);
        Log.i("//===========", "==============Sau Update================");
        Log.i("//===========", "Tổng số loại sách: "+loaiSachDAO.getAll().size());

//        loaiSachDAO.delete("1");
//        Log.i("//===========", "===============Sau Delete===============");
//        Log.i("//===========", "Tổng số loại sách: "+loaiSachDAO.getAll().size());
    }

    public void Sach() {
        Sach sach = new Sach(001, "Sách Tiếng Anh", 12000, 1);
        if (sachDAO.insert(sach) > 0) {
            Log.i("//==========","Thêm sách thành công");
        } else {
            Log.i("//==========","Thêm sách không thành công");
        }

        sach = new Sach(001, "Sách Lập Trình Java", 20000, 1);
        sachDAO.updateSach(sach);
        Log.i("//===========", "==============Sau Update================");
        Log.i("//===========", "Tổng số sách: "+sachDAO.getAll().size());

//        sachDAO.delete("001");
//        Log.i("//===========", "===============Sau Delete===============");
//        Log.i("//===========", "Tổng số sách: "+sachDAO.getAll().size());
    }
}
