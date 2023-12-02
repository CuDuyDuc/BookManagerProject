package com.example.bookmanagerproject.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bookmanagerproject.Database.DbHelper;
import com.example.bookmanagerproject.Model.LoaiSach;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDAO {
    private SQLiteDatabase db;

    public LoaiSachDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(LoaiSach ls) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenLoai", ls.getTenLoai());
        return db.insert("LoaiSach", null, contentValues);
    }

    public int updateLS(LoaiSach ls) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenLoai", ls.getTenLoai());

        return db.update("LoaiSach", contentValues, "maLoai=?", new String[]{String.valueOf(ls.getMaLoai())});
    }

    public int Delete(String id) {
        return db.delete("LoaiSach", "maLoai=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<LoaiSach> getData(String sql, String...selectionArgs) {
        // Phương thức này nhận một câu lệnh SQL và một danh sách tham số (selectionArgs) để thay thế vào câu lệnh SQL.
        // Thông qua câu lệnh SQL và tham số, phương thức thực hiện truy vấn dữ liệu từ cơ sở dữ liệu.
        List<LoaiSach> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            LoaiSach ls = new LoaiSach();
            ls.setMaLoai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLoai"))));
            ls.setTenLoai(cursor.getString(cursor.getColumnIndex("tenLoai")));

            list.add(ls);
        }
        return list;
    }

    public List<LoaiSach> getAll() {
        String sql = "SELECT * FROM LoaiSach";
        return getData(sql);
    }

    public LoaiSach getID(String id) {
        String sql = "SELECT * FROM LoaiSach WHERE maLoai=?";
        List<LoaiSach> list = getData(sql, id);
        // Kiểm tra nếu danh sách rỗng
        if (list.isEmpty()) {
            // Trả về null hoặc giá trị mặc định phù hợp
            return null; // Hoặc bạn có thể trả về một giá trị mặc định
        } else {
            return list.get(0);
        }
    }
}
