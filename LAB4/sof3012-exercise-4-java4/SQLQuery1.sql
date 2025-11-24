-- 1. TẠO VÀ SỬ DỤNG CƠ SỞ DỮ LIỆU
--------------------------------------------------------------------------------

-- Đảm bảo tên CSDL trùng khớp với persistence.xml (nếu cần thiết)
IF DB_ID(N'PolyOE') IS NULL
BEGIN
    CREATE DATABASE PolyOEE;
GO

USE PolyOEE;
GO

-- 2. DỌN DẸP (XÓA CÁC BẢNG CŨ NẾU CÓ)
--------------------------------------------------------------------------------

IF OBJECT_ID('Shares') IS NOT NULL DROP TABLE Shares;
IF OBJECT_ID('Favorites') IS NOT NULL DROP TABLE Favorites;
IF OBJECT_ID('Videos') IS NOT NULL DROP TABLE Videos;
IF OBJECT_ID('Users') IS NOT NULL DROP TABLE Users;
GO

-- 3. TẠO CẤU TRÚC BẢNG (DDL)
--------------------------------------------------------------------------------

-- Bảng Users (Người dùng)
CREATE TABLE Users (
    Id          VARCHAR(50) PRIMARY KEY,
    Password    VARCHAR(50) NOT NULL,
    Email       VARCHAR(100) UNIQUE NOT NULL, -- Email phải là UNIQUE (Yêu cầu Bài 1)
    Fullname    NVARCHAR(100) NOT NULL,
    IsAdmin     BIT DEFAULT 0
);
GO

-- Bảng Videos (Video)
CREATE TABLE Videos (
    Id          VARCHAR(50) PRIMARY KEY,
    Title       NVARCHAR(255) NOT NULL, -- Dùng cho tìm kiếm theo keyword
    Poster      VARCHAR(255),
    Views       BIGINT DEFAULT 0,
    Description NVARCHAR(MAX),
    IsActive    BIT DEFAULT 1 -- Còn hiệu lực
);
GO

-- Bảng Favorites (Lượt Thích)
CREATE TABLE Favorites (
    Id          BIGINT IDENTITY(1,1) PRIMARY KEY,
    UserId      VARCHAR(50) NOT NULL,
    VideoId     VARCHAR(50) NOT NULL,
    LikedDate   DATE DEFAULT GETDATE(),
    
    -- Khóa ngoại
    CONSTRAINT FK_Favorites_Users FOREIGN KEY (UserId) REFERENCES Users(Id),
    CONSTRAINT FK_Favorites_Videos FOREIGN KEY (VideoId) REFERENCES Videos(Id),
    
    -- Mỗi User chỉ thích một Video một lần (Composite Unique Key)
    CONSTRAINT UQ_Favorite UNIQUE (UserId, VideoId) 
);
GO

-- Bảng Shares (Lượt Chia sẻ)
CREATE TABLE Shares (
    Id          BIGINT IDENTITY(1,1) PRIMARY KEY,
    UserId      VARCHAR(50) NOT NULL,
    VideoId     VARCHAR(50) NOT NULL,
    Email       VARCHAR(100) NOT NULL, -- Email người nhận
    ShareDate   DATE DEFAULT GETDATE(), -- Dùng cho truy vấn năm 2024 (Bài 1)
    
    -- Khóa ngoại
    CONSTRAINT FK_Shares_Users FOREIGN KEY (UserId) REFERENCES Users(Id),
    CONSTRAINT FK_Shares_Videos FOREIGN KEY (VideoId) REFERENCES Videos(Id)
);
GO

-- 4. CHÈN DỮ LIỆU MẪU (DML)
--------------------------------------------------------------------------------

-- Dữ liệu Users (Hỗ trợ Bài 2: Đăng nhập bằng ID hoặc Email)
INSERT INTO Users (Id, Password, Email, Fullname, IsAdmin) VALUES
('admin', '123', 'admin@polyo.edu.vn', N'Quản Trị Viên', 1),
('user1', '123', 'user1@gmail.com', N'Nguyễn Văn A', 0), -- User để test ID/Email login
('user2', '456', 'user2@gmail.com', N'Lê Thị B', 0),
('user_share', '123', 'share@test.com', N'Người Chia Sẻ', 0);
GO

-- Dữ liệu Videos (Hỗ trợ Bài 3: Tìm kiếm keyword, Bài 1: Top liked, unliked)
INSERT INTO Videos (Id, Title, Description, IsActive) VALUES
('V001', N'Lập Trình JAVA Cơ Bản', N'Bài giảng về cú pháp Java', 1), -- Top Liked
('V002', N'Hướng dẫn SQL Server', N'Tìm hiểu về CSDL quan hệ', 1), -- Video không được ai thích (Unliked)
('V003', N'Spring Boot và REST API', N'Xây dựng API với Spring', 1), -- Chứa keyword 'API'
('V004', N'Web Dev Fundamentals', N'HTML, CSS, JavaScript', 0), -- Video không còn hiệu lực
('V005', N'JPQL nâng cao cho Hibernate', N'Các hàm tổng hợp trong JPQL', 1); 
GO

-- Dữ liệu Favorites (Hỗ trợ Bài 1: Top 10 video được thích & video không được thích)
-- V001: 2 lượt thích (User1, User2)
INSERT INTO Favorites (UserId, VideoId) VALUES
('user1', 'V001'),
('user2', 'V001');

-- V003: 1 lượt thích
INSERT INTO Favorites (UserId, VideoId) VALUES
('user2', 'V003');

-- V002 không có lượt thích nào -> kiểm tra JPQL "Tìm các video không được ai thích"
GO

-- Dữ liệu Shares (Hỗ trợ Bài 4: Báo cáo tổng hợp & Bài 1: Tìm video chia sẻ năm 2024)
INSERT INTO Shares (UserId, VideoId, Email, ShareDate) VALUES
-- Dữ liệu 2024 (Hỗ trợ JPQL tìm kiếm)
('user_share', 'V005', 'nguoinhan1@mail.com', '2024-01-10'),
('user1', 'V005', 'nguoinhan2@mail.com', '2024-05-20'), -- V005 được chia sẻ 2 lần trong 2024

-- Dữ liệu năm khác (Hỗ trợ JPQL tìm kiếm)
('user2', 'V003', 'nguoinhan3@mail.com', '2023-11-15'),

-- Dữ liệu cho Báo cáo (V005 có 2 lượt share: 10/01/2024 và 20/05/2024)
('user_share', 'V001', 'nguoinhan4@mail.com', '2024-10-01'); 
GO

-- 5. KIỂM TRA DỮ LIỆU
--------------------------------------------------------------------------------
SELECT * FROM Users;
SELECT * FROM Videos;
SELECT * FROM Favorites;
SELECT * FROM Shares;