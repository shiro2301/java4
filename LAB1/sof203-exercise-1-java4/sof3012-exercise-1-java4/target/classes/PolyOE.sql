CREATE DATABASE PolyOE;
USE PolyOE;
CREATE TABLE Users (
    Id INT PRIMARY KEY IDENTITY(1,1),
    Fullname NVARCHAR(100),
    Email NVARCHAR(100),
    Password NVARCHAR(100),
    Admin BIT
);
INSERT INTO Users VALUES
(N'Nguyễn Thanh Phong', 'phong@fpt.edu.vn', '123', 1),
(N'Võ Minh Nhật', 'nhat@fpt.edu.vn', '456', 0),
(N'Võ Lâm Gia Huy', 'huy@gmail.com', '789', 0);

USE PolyOE;
INSERT INTO Users (Fullname, Email, Password, Admin) VALUES
(N'Trần Văn An', 'an.tran@poly.edu', 'poly123', 0),
(N'Lê Thị Bình', 'binh.le@poly.edu', 'poly456', 0),
(N'Phạm Quang Cường', 'cuong.pham@poly.edu', 'poly789', 0),
(N'Đỗ Mai Duyên', 'duyen.do@poly.edu', 'pass123', 0),
(N'Hoàng Minh Đức', 'duc.hoang@poly.edu', 'pass456', 1),
(N'Nguyễn Thị Em', 'em.nguyen@poly.edu', 'pass789', 0),
(N'Vũ Văn Giang', 'giang.vu@poly.edu', 'user123', 0),
(N'Bùi Thị Hạnh', 'hanh.bui@poly.edu', 'user456', 0),
(N'Đinh Công Khải', 'khai.dinh@poly.edu', 'user789', 0),
(N'Trần Thị Lan', 'lan.tran@poly.edu', 'data123', 0),
(N'Lý Văn Mạnh', 'manh.ly@poly.edu', 'data456', 1),
(N'Phạm Thị Nga', 'nga.pham@poly.edu', 'data789', 0);

USE PolyOE;
DELETE FROM Users
WHERE Fullname = N'Văn Trọng';