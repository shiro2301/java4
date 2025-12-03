-- Kiểm tra và Tạo Database (thay thế LAB5_JAVA4 bằng tên Database của bạn)

CREATE DATABASE LAB5_JAVA4;
GO

USE LAB5_JAVA4;
GO

-- 1. Bảng Users (Bài 1: Đăng nhập)
-- Username là khóa chính (PRIMARY KEY)
IF OBJECT_ID('Users', 'U') IS NOT NULL 
    DROP TABLE Users;
CREATE TABLE Users (
    Username VARCHAR(50) PRIMARY KEY,
    Password VARCHAR(50) NOT NULL,
    HoVaTen NVARCHAR(100) NOT NULL
);
GO

-- 2. Bảng Logs (Bài 3: Ghi nhận thông tin truy cập)
-- Id là cột tự tăng (IDENTITY)
IF OBJECT_ID('Logs', 'U') IS NOT NULL 
    DROP TABLE Logs;
CREATE TABLE Logs (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Url VARCHAR(255) NOT NULL,
    Time DATETIME NOT NULL,
    Username VARCHAR(50) NULL -- Có thể NULL nếu chưa đăng nhập
);
GO

-- 3. Chèn Dữ liệu Mẫu vào Bảng Users
INSERT INTO Users (Username, Password, HoVaTen) VALUES 
('gvphuc', '123456', N'Nguyễn Phước'),
('sinhvienA', '111', N'Lê Văn An'),
('useradmin', 'java4', N'Trần Bảo Bình');
GO

-- 4. Chèn Dữ liệu Mẫu vào Bảng Logs (Dùng để kiểm tra truy vấn)
INSERT INTO Logs (Url, Time, Username) VALUES 
(N'/lab5/index.jsp', GETDATE(), 'useradmin'),
(N'/lab5/login.jsp', DATEADD(minute, -1, GETDATE()), 'gvphuc'),
(N'/lab5/about.jsp', DATEADD(minute, -5, GETDATE()), NULL);
GO

SELECT * FROM Logs WHERE Username = 'gvphuc' ORDER BY Time DESC;