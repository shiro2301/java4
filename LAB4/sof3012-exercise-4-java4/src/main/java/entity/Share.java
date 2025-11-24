package entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Shares")
public class Share {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "UserId")
    private User user; // Người chia sẻ
    
    @ManyToOne @JoinColumn(name = "VideoId")
    private Video video; // Video được chia sẻ
    
    @Column(name = "Email", nullable = false)
    private String email; // Email người nhận

    @Column(name = "ShareDate")
    @Temporal(TemporalType.DATE)
    private Date shareDate = new Date(); // Dùng cho truy vấn năm 2024 (Bài 1 (5))

    // Getters và Setters
    public Date getShareDate() { return shareDate; }
    // ...
}