package com.example.bookmanagerproject.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bookmanagerproject.Database.DbHelper;
import com.example.bookmanagerproject.Model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    private SQLiteDatabase db;

    public SachDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(Sach s) {
        ContentValues contentValues = new ContentValues(); // để chứa dữ liệu của sách trước khi thêm vào cơ sở dữ liệu
        contentValues.put("tenSach", s.getTenSach());
        contentValues.put("giaThue", s.getGiaThue());
        contentValues.put("maLoai", s.getMaLoai());
        // trả về một giá trị long, là số thứ tự (masach) của sách vừa được thêm vào cơ sở dữ liệu.
        return db.insert("Sach", null, contentValues);
    }

    public int updateSach(Sach s) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenSach", s.getTenSach());
        contentValues.put("giaThue", s.getGiaThue());
        contentValues.put("maLoai", s.getMaLoai());

        return db.update("Sach", contentValues, "maSach=?", new String[]{String.valueOf(s.getMaSach())});
    }

    public int delete(String id) {
        return db.delete("Sach", "maSach=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<Sach> getData(String sql, String...selectionArgs) {
        // Phương thức này nhận một câu lệnh SQL và một danh sách tham số (selectionArgs) để thay thế vào câu lệnh SQL.
        // Thông qua câu lệnh SQL và tham số, phương thức thực hiện truy vấn dữ liệu từ cơ sở dữ liệu.
        List<Sach> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            Sach s = new Sach();
            s.setMaSach(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSach"))));
            s.setTenSach(cursor.getString(cursor.getColumnIndex("tenSach")));
            s.setGiaThue(Integer.parseInt(cursor.getString(cursor.getColumnIndex("giaThue"))));
            s.setMaLoai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLoai"))));

            list.add(s);
        }
        return list;
    }

    public List<Sach> getAll() {
        String sql = "SELECT * FROM Sach";
        return getData(sql);
    }

    public Sach getID(String id) {
        String sql = "SELECT * FROM Sach WHERE maSach=?";
        List<Sach> list = getData(sql, id);

        return list.get(0);
    }
}
