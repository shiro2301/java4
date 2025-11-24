package dto;

import java.util.Date;

// DTO phải là public để JPQL có thể truy cập Constructor Expression
public class ReportShareDTO {
    private String videoTitle;
    private Long shareCount;
    private Date firstShareDate;
    private Date lastShareDate;

    // Constructor phải chứa đủ các tham số theo thứ tự của JPQL
    public ReportShareDTO(String videoTitle, Long shareCount, Date firstShareDate, Date lastShareDate) {
        this.videoTitle = videoTitle;
        this.shareCount = shareCount;
        this.firstShareDate = firstShareDate;
        this.lastShareDate = lastShareDate;
    }
    
    // Getters
    public String getVideoTitle() { return videoTitle; }
    public Long getShareCount() { return shareCount; }
    public Date getFirstShareDate() { return firstShareDate; }
    public Date getLastShareDate() { return lastShareDate; }
    
    // Setters (Nếu cần)
    // ...
}