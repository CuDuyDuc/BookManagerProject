package com.example.bookmanagerproject.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bookmanagerproject.Database.DbHelper;
import com.example.bookmanagerproject.Model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class ThuThuDAO {
    private SQLiteDatabase db;
    private DbHelper dbHelper;

    public ThuThuDAO(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean insert(ThuThu tt) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maTT", tt.getMaTT());
        contentValues.put("hoTen", tt.getHoTen());
        contentValues.put("matKhau", tt.getMatKhau());

        long check = db.insert("ThuThu", null, contentValues);
        return check != -1;
    }

    public int updatePass(ThuThu tt) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoTen", tt.getHoTen());
        contentValues.put("matKhau", tt.getMatKhau());

        return db.update("ThuThu", contentValues, "maTT=?", new String[]{tt.getMaTT()});
    }

    public int delete(String id) {
        return db.delete("ThuThu", "maTT=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<ThuThu> getData(String sql, String... selectionArgs) {
        // Phương thức này nhận một câu lệnh SQL và một danh sách tham số (selectionArgs) để thay thế vào câu lệnh SQL.
        // Thông qua câu lệnh SQL và tham số, phương thức thực hiện truy vấn dữ liệu từ cơ sở dữ liệu.
        List<ThuThu> list = new ArrayList<>();
        Cursor cursor = null;

        if (selectionArgs != null && selectionArgs.length > 0) {
            cursor = db.rawQuery(sql, selectionArgs);
        } else {
            cursor = db.rawQuery(sql, null);
        }

        while (cursor.moveToNext()) {
            ThuThu tt = new ThuThu();
            tt.setHoTen(cursor.getString(cursor.getColumnIndex("hoTen")));
            tt.setMaTT(cursor.getString(cursor.getColumnIndex("maTT")));
            tt.setMatKhau(cursor.getString(cursor.getColumnIndex("matKhau")));
            Log.i("//==========", tt.toString());
            list.add(tt);
        }
        cursor.close(); // Đảm bảo đóng Cursor sau khi sử dụng
        return list;
    }

    public List<ThuThu> getAll() {
        String sql = "SELECT * FROM ThuThu";
        return getData(sql);
    }

    public ThuThu getID(String id) {
        if (id != null) {
            String sql = "SELECT * FROM ThuThu WHERE maTT=?";
            List<ThuThu> list = getData(sql, id);
            if (!list.isEmpty()) {
                return list.get(0);
            }
        }
        return null; // hoặc một giá trị mặc định khác tùy vào yêu cầu của bạn
    }


    // check login
    public int checkLogin(String id, String password) {
        String sql = "SELECT * FROM ThuThu WHERE maTT=? AND matKhau=?";
        List<ThuThu> list = getData(sql, id, password);
        if(list.size() == 0) {
            return -1;
        }
        return 1;
    }

    public String ForgotPassword(String email) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT matKhau FROM ThuThu WHERE maTT=?", new String[]{email});
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getString(0);
        } else {
            return "";
        }
    }
}
