CREATE DATABASE PolyOE_Lab3;
GO
USE PolyOE_Lab3;

-- Bảng User
CREATE TABLE Users (
    Id VARCHAR(50) PRIMARY KEY,
    Password VARCHAR(50) NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    Fullname NVARCHAR(100) NOT NULL,
    Admin BIT NOT NULL
);

-- Bảng Video
CREATE TABLE Videos (
    Id VARCHAR(50) PRIMARY KEY,
    Title NVARCHAR(255) NOT NULL,
    Poster VARCHAR(255),
    Views INT,
    Description NVARCHAR(MAX),
    Active BIT
);

-- Bảng Favorite
CREATE TABLE Favorites (
    Id BIGINT IDENTITY(1,1) PRIMARY KEY,
    UserId VARCHAR(50) NOT NULL,
    VideoId VARCHAR(50) NOT NULL,
    LikeDate DATE,

    CONSTRAINT FK_Fav_User FOREIGN KEY (UserId) REFERENCES Users(Id),
    CONSTRAINT FK_Fav_Video FOREIGN KEY (VideoId) REFERENCES Videos(Id),

    CONSTRAINT UQ_User_Video UNIQUE(UserId, VideoId)
);

-- Bảng Share
CREATE TABLE Shares (
    Id BIGINT IDENTITY(1,1) PRIMARY KEY,
    UserId VARCHAR(50) NOT NULL,
    VideoId VARCHAR(50) NOT NULL,
    Emails VARCHAR(255),
    ShareDate DATE,

    CONSTRAINT FK_Share_User FOREIGN KEY(UserId) REFERENCES Users(Id),
    CONSTRAINT FK_Share_Video FOREIGN KEY(VideoId) REFERENCES Videos(Id)
);

-- Dùng CSDL
USE PolyOE_Lab3;
GO

-- Thêm Users
INSERT INTO Users (Id, Password, Email, Fullname, Admin) VALUES
('teo', 'teo123', 'teo@gmail.com', N'Nguyễn Văn Tèo', 1),
('no', 'no123', 'no@gmail.com', N'Trần Thị Nở', 0),
('pheo', 'pheo123', 'pheo@gmail.com', N'Chí Phèo', 0);
GO

-- Thêm Videos
INSERT INTO Videos (Id, Title, Poster, Views, Description, Active) VALUES
('v01', N'Lập trình Java 4 - Hướng dẫn Lab 3', 'poster1.jpg', 1500, N'Mô tả cho video lab 3...', 1),
('v02', N'Spring Boot cho người mới bắt đầu', 'poster2.jpg', 8500, N'Series học Spring Boot từ A-Z', 1),
('v03', N'Học Hibernate trong 1 giờ', 'poster3.jpg', 12000, N'Tutorial về Hibernate/JPA cấp tốc', 1),
('v04', N'Tomcat 10 có gì mới?', 'poster4.jpg', 300, N'Tìm hiểu về Jakarta EE 10', 0);
GO

-- Thêm Favorites (Quan trọng)
INSERT INTO Favorites (UserId, VideoId, LikeDate) VALUES
('teo', 'v02', '2024-01-10'),  -- Tèo thích video Spring Boot
('teo', 'v03', '2024-01-11'),  -- Tèo thích video Hibernate
('no', 'v02', '2024-02-05'),   -- Nở cũng thích video Spring Boot
('pheo', 'v01', '2024-03-15'); -- Phèo thích video Lab 3
GO

-- Thêm Shares
INSERT INTO Shares (UserId, VideoId, Emails, ShareDate) VALUES
('teo', 'v01', 'admin@example.com,boss@example.com', '2024-03-16');
GO