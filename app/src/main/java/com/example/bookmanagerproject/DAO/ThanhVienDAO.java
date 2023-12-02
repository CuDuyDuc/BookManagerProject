package com.example.bookmanagerproject.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bookmanagerproject.Database.DbHelper;
import com.example.bookmanagerproject.Model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDAO {
    private SQLiteDatabase db;

    public ThanhVienDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(ThanhVien tv) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoTen", tv.getHoTen());
        contentValues.put("namSinh", tv.getNamSinh());

        return db.insert("ThanhVien", null, contentValues);
    }

    public int updateTV(ThanhVien tv) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoTen", tv.getHoTen());
        contentValues.put("namSinh", tv.getNamSinh());

        return db.update("ThanhVien", contentValues, "maTV=?", new String[]{String.valueOf(tv.getMaTV())});
    }

    public int delete(String id) {
        return db.delete("ThanhVien", "maTV=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<ThanhVien> getData(String sql, String...selectionArgs) {
        // Phương thức này nhận một câu lệnh SQL và một danh sách tham số (selectionArgs) để thay thế vào câu lệnh SQL.
        // Thông qua câu lệnh SQL và tham số, phương thức thực hiện truy vấn dữ liệu từ cơ sở dữ liệu.
        List<ThanhVien> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            ThanhVien tv = new ThanhVien();
            tv.setMaTV(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maTV"))));
            tv.setHoTen(cursor.getString(cursor.getColumnIndex("hoTen")));
            tv.setNamSinh(cursor.getString(cursor.getColumnIndex("namSinh")));
            Log.i("//==========", tv.toString());
            list.add(tv);
        }
        return list;
    }

    public List<ThanhVien> getAll() {
        String sql = "SELECT * FROM ThanhVien";
        return getData(sql);
    }

    public ThanhVien getID(String id) {
        String sql = "SELECT * FROM ThanhVien WHERE maTV=?";
        List<ThanhVien> list = getData(sql, id);
        // Kiểm tra nếu danh sách rỗng
        if (list.isEmpty()) {
            // Trả về null hoặc giá trị mặc định phù hợp
            return null; // Hoặc bạn có thể trả về một giá trị mặc định
        } else {
            return list.get(0);
        }
    }
}
