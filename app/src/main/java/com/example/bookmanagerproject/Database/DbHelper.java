package com.example.bookmanagerproject.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_TABLE_NAME = "BOOK_MANAGER_PROJECT";

    static final String CREATE_TABLE_THU_THU = "CREATE TABLE ThuThu (\n" +
            "    maTT    TEXT PRIMARY KEY,\n" +
            "    hoTen   TEXT NOT NULL,\n" +
            "    matKhau TEXT NOT NULL\n" +
            ");\n";

    static final String CREATE_TABLE_THANH_VIEN = "CREATE TABLE ThanhVien (\n" +
            "    maTV    INTEGER PRIMARY KEY AUTOINCREMENT ,\n" +
            "    hoTen   TEXT NOT NULL,\n" +
            "    namSinh TEXT NOT NULL\n" +
            ");";

    static final String CREATE_TABLE_LOAI_SACH = "CREATE TABLE LoaiSach (\n" +
            "    maLoai  INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    tenLoai TEXT    NOT NULL\n" +
            ");\n";

    static final String CREATE_TABLE_SACH = "CREATE TABLE Sach (\n" +
            "    maSach  INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    tenSach TEXT    NOT NULL,\n" +
            "    giaThue INTEGER NOT NULL,\n" +
            "    maLoai  INTEGER REFERENCES LoaiSach (maLoai) \n" +
            ");";

    static final String CREATE_TABLE_PHIEU_MUON = "CREATE TABLE PhieuMuon (\n" +
            "    maPM     INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    maTT     TEXT    REFERENCES ThuThu (maTT),\n" +
            "    maTV     INTEGER    REFERENCES ThanhVien (maTV),\n" +
            "    maSach   INTEGER REFERENCES Sach (maSach),\n" +
            "    tienThue INTEGER NOT NULL,\n" +
            "    ngay     DATE    NOT NULL,\n" +
            "    traSach  INTEGER NOT NULL\n" +
            ");\n";

    public DbHelper(@Nullable Context context) {
        super(context, DB_TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo Bảng Thủ Thư
        db.execSQL(CREATE_TABLE_THU_THU);
        // Tạo Bảng Thành Viên
        db.execSQL(CREATE_TABLE_THANH_VIEN);
        // Tạo Bảng Loại Sách
        db.execSQL(CREATE_TABLE_LOAI_SACH);
        // Tạo Bảng Sách
        db.execSQL(CREATE_TABLE_SACH);
        // Tạo Bảng Phiếu Mượn
        db.execSQL(CREATE_TABLE_PHIEU_MUON);
        // Insert data
        db.execSQL(Data_SQLite.INSERT_THU_THU);
        db.execSQL(Data_SQLite.INSERT_THANH_VIEN);
        db.execSQL(Data_SQLite.INSERT_LOAI_SACH);
        db.execSQL(Data_SQLite.INSERT_SACH);
        db.execSQL(Data_SQLite.INSERT_PHIEU_MUON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //  xóa các bảng cũ bằng cách thực thi các truy vấn DROP TABLE
        String dropTableThuThu = "drop table if exists ThuThu";
        db.execSQL(dropTableThuThu);
        String dropTableThanhVien = "drop table if exists ThanhVien";
        db.execSQL(dropTableThanhVien);
        String dropTableLoaiSach = "drop table if exists LoaiSach";
        db.execSQL(dropTableLoaiSach);
        String dropTableSach = "drop table if exists Sach";
        db.execSQL(dropTableSach);
        String dropTablePhieuMuon = "drop table if exists PhieuMuon";
        db.execSQL(dropTablePhieuMuon);

        onCreate(db); // gọi lại phương thức onCreate để tạo lại cơ sở dữ liệu với cấu trúc mới.
    }
}
