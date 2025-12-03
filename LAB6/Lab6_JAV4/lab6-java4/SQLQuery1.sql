-- Sử dụng Database LAB6_JAVA4
Create database LAB6_JAVA4;
GO

USE LAB6_JAVA4;
GO

-- 1. Bảng Users (Với cột Admin để phân quyền)
IF OBJECT_ID('Users', 'U') IS NOT NULL 
    DROP TABLE Users;
CREATE TABLE Users (
    Username VARCHAR(50) PRIMARY KEY,
    Password VARCHAR(50) NOT NULL,
    HoVaTen NVARCHAR(100) NOT NULL,
    Admin BIT NOT NULL DEFAULT 0 -- 1: Admin, 0: User thường
);
GO

-- 2. Chèn Dữ liệu Mẫu
INSERT INTO Users (Username, Password, HoVaTen, Admin) VALUES 
('admin', '123', N'Quản Trị Viên', 1),    -- Admin
('user', '123', N'Người Dùng Thường', 0), -- User thường
('giao_vien', '456', N'Giảng Viên', 0);
GO