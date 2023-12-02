package com.example.bookmanagerproject.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bookmanagerproject.Database.DbHelper;
import com.example.bookmanagerproject.Model.PhieuMuon;
import com.example.bookmanagerproject.Model.Sach;
import com.example.bookmanagerproject.Model.Top;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDAO {
    private SQLiteDatabase db;

    private Context context;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public PhieuMuonDAO(Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(PhieuMuon pm) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maTT", pm.getMaTT());
        contentValues.put("maTV", pm.getMaTV());
        contentValues.put("maSach", pm.getMaSach());
        contentValues.put("ngay", simpleDateFormat.format(pm.getNgay()));
        contentValues.put("tienThue", pm.getTienThue());
        contentValues.put("traSach", pm.getTraSach());

        return db.insert("PhieuMuon", null, contentValues);
    }

    public int updatePhieuMuon(PhieuMuon pm) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maTT", pm.getMaTT());
        contentValues.put("maTV", pm.getMaTV());
        contentValues.put("maSach", pm.getMaSach());
        contentValues.put("ngay", simpleDateFormat.format(pm.getNgay()));
        contentValues.put("tienThue", pm.getTienThue());
        contentValues.put("traSach", pm.getTraSach());

        return db.update("PhieuMuon", contentValues, "maPM=?", new String[]{String.valueOf(pm.getMaPM())});
    }

    public int delete(String id) {
        return db.delete("PhieuMuon", "maPM=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<PhieuMuon> getData(String sql, String...selectionArgs) {
        // Phương thức này nhận một câu lệnh SQL và một danh sách tham số (selectionArgs) để thay thế vào câu lệnh SQL.
        // Thông qua câu lệnh SQL và tham số, phương thức thực hiện truy vấn dữ liệu từ cơ sở dữ liệu.
        List<PhieuMuon> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) { // moveToNext trả về true nếu còn dòng kết quả để duyệt, và false nếu đã duyệt hết
            PhieuMuon pm = new PhieuMuon();
            pm.setMaPM(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maPM"))));
            pm.setMaTT(cursor.getString(cursor.getColumnIndex("maTT")));
            pm.setMaTV(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maTV"))));
            pm.setMaSach(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSach"))));
            pm.setTienThue(Integer.parseInt(cursor.getString(cursor.getColumnIndex("tienThue"))));
            try {
                pm.setNgay(simpleDateFormat.parse(cursor.getString(cursor.getColumnIndex("ngay"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            pm.setTraSach(Integer.parseInt(cursor.getString(cursor.getColumnIndex("traSach"))));

            list.add(pm);
        }
        return list;
    }

    public List<PhieuMuon> getAll() {
        String sql = "SELECT * FROM PhieuMuon";
        return getData(sql);
    }

    public PhieuMuon getID(String id) {
        String sql = "SELECT * FROM PhieuMuon WHERE maPM=?";
        List<PhieuMuon> list = getData(sql, id);
        return list.get(0);
    }

    // Thống kê top 10
    @SuppressLint("Range")
    public List<Top> getTop() {
        String sqlTop = "SELECT maSach, count(maSach) as soLuong FROM PhieuMuon GROUP BY maSach ORDER BY soLuong DESC LIMIT 10";
        List<Top> list = new ArrayList<>();
        SachDAO sachDAO = new SachDAO(context);
        // thực hiện truy vấn SQL bằng cách sử dụng db.rawQuery() và lấy kết quả dưới dạng Cursor
        Cursor cursor = db.rawQuery(sqlTop, null);
        while (cursor.moveToNext()) {
            Top top = new Top();
            Sach s = sachDAO.getID(cursor.getString(cursor.getColumnIndex("maSach")));
            top.setTenSach(s.getTenSach());
            top.setSoLuong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soLuong"))));
            list.add(top);
        }
        return list;
    }

    // Thống kê Doanh Thu
    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay) {
        String sqlDoanhThu = "SELECT SUM(tienThue) as doanhThu FROM PhieuMuon WHERE ngay BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<>();
        //  thực hiện truy vấn SQL bằng cách sử dụng db.rawQuery() và lấy kết quả dưới dạng Cursor
        Cursor cursor = db.rawQuery(sqlDoanhThu, new String[]{tuNgay, denNgay});
        while (cursor.moveToNext()) {
            try{
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhThu"))));
            } catch (Exception e) {
                list.add(0);
            }
        }
        return list.get(0);
    }
}
