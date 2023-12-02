package com.example.bookmanagerproject.Database;

public class Data_SQLite {
    public static final String INSERT_THU_THU = "Insert into ThuThu(maTT, hoTen, matKhau) values " +
            "('phuoctn', 'Nguyen Tien Phuoc', '123456')," +
            "('thaoxl', 'Le Xuan Thao', 'thao123')," +
            "('nghibn', 'Nguyen Ba Nghi', 'nghi123')," +
            "('trungkn', 'Nguyen Khac Trung', 'trung456')";

    public static final String INSERT_THANH_VIEN = "Insert into ThanhVien(hoTen, namSinh) values " +
            "('Cu Duy Duc', '2001')," +
            "('Le Huu Nghia', '2001')," +
            "('Nguyen Van Toan', '2000')," +
            "('Tran Quang Huy', '2002')," +
            "('Nguyen Tra My', '2003')," +
            "('Nguyen Yen Huong', '2003')," +
            "('Vo Huu Hoang Vu', '1999')," +
            "('Nguyen Tuong Vy', '2001')," +
            "('Tran Van Cuong', '2004')," +
            "('Le Nguyen Thong', '2001')," +
            "('Mac Van Khoa', '2000')," +
            "('Nguyen Chi Cuong', '1999')," +
            "('Tran Phu Duy', '2001')," +
            "('Tran Le Trung Kien', '2000')";

    public static final String INSERT_LOAI_SACH = "Insert into LoaiSach(tenLoai) values" +
            "('Thuật Toán Lập Trình')," +
            "('Tiếng Trung N5')," +
            "('Luyện Thi Tiếng Anh Ielts')," +
            "('Design & PhotoShop')," +
            "('Cơ sở dữ liệu với SQL Server')," +
            "('Những Câu Chuyện Khỏi Nghiệp')," +
            "('Lập Trình Hướng Đối Tượng OOP')," +
            "('Những Câu Hỏi Khi Phỏng Vấn Java')," +
            "('Flutter Và Những Điều Bạn Chưa Biết')";

    public static final String INSERT_SACH = "Insert into Sach(tenSach, giaThue, maLoai) values" +
            "('Lập Trình Flutter cơ bản', '3000', '10')," +
            "('Lập Trình Flutter nâng cao', '3000', '10')," +
            "('Câu Hỏi Phỏng Vấn Flutter', '3000', '10')," +
            "('Lập Trình Java cơ bản', '5000', '9')," +
            "('Lập Trình Java nâng cao', '5000', '9')," +
            "('Java SpringBoot', '3000', '9')," +
            "('Dự Án với Swing & jDBC', '3000', '9')," +
            "('Cấu Trúc Giải Thuật', '3000', '2')," +
            "('Tư Duy Lập Trình', '3000', '2')," +
            "('Tiếng Trung Cho Người Mới Bắt Đầu', '4000', '3')," +
            "('Luyện Thi Tiếng Trung HSK5', '4000', '3')," +
            "('Tiếng Anh Cho Người Mới Bắt Đầu', '4000', '4')," +
            "('Cấu Trúc Câu Hỏi Đề Thi Ielts', '4000', '4')," +
            "('Thiết Kế Hình Ảnh Với PhoToShop', '3000', '5')," +
            "('Thiết Kế Nội Thất Và Ngoại Thất', '3000', '5')," +
            "('Cơ Sở Dữ Liệu SQL Server', '4000', '6')," +
            "('Giáo Trình Cơ Sở Dữ Liệu Với SQL Server', '4000', '6')," +
            "('Bí Quyết Thành Công Của Người Giàu', '3000', '7')," +
            "('Những Bài Học Đắc Giá Trong Kinh Doanh', '3000', '7')," +
            "('Lập Trình Hướng Đối Tượng Java Core', '3000', '8')," +
            "('Lập Trình Hướng Đối Tượng Với C++', '3000', '8')," +
            "('Lý Thuyết Và Bài Tập Lập Trình Hướng Đối Tượng', '3000', '8')," +
            "('Lập Trình MS.NET MVC', '2000', '1')," +
            "('Dự Án Với Công Nghệ Spring MVC', '2000', '1')";

    public static final String INSERT_PHIEU_MUON = "Insert into PhieuMuon(maTT, maTV, maSach, tienThue, ngay, traSach) values" +
            "('phuoctn', '1', '1', '2000', '2023/09/26', '0')," +
            "('abc', '2', '1', '3000', '2023/09/24', '1')," +
            "('phuoctn', '3', '2', '4000', '2023/09/25', '1')," +
            "('thaoxl', '4', '3', '5000', '2023/09/23', '0')," +
            "('nghibn', '5', '4','3000', '2023/09/22', '0')," +
            "('trungkn', '6', '5','5000', '2023/09/21', '0')," +
            "('abc', '7', '6', '3000', '2023/09/20', '1')," +
            "('phuoctn', '8', '7', '4000', '2023/09/19', '1')," +
            "('thaoxl', '9', '8', '5000', '2023/09/18', '0')," +
            "('nghibn', '10', '9', '3500','2023/09/17', '0')," +
            "('trungkn', '11', '10','3000', '2023/09/16', '0')," +
            "('abc', '12', '11', '3000', '2023/09/15', '1')," +
            "('phuoctn', '13', '12', '4000', '2023/09/14', '1')," +
            "('thaoxl', '14', '13', '5000', '2023/09/13', '0')," +
            "('nghibn', '15', '14','4000', '2023/09/12', '0')";
}
