package entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Videos")
public class Video {
    
    @Id
    @Column(name = "Id")
    private String id;
    
    @Column(name = "Title", nullable = false)
    private String title; // Dùng cho tìm kiếm theo từ khóa (Bài 1 (2), Bài 3)
    
    @Column(name = "Poster")
    private String poster;
    
    @Column(name = "Views")
    private Long views = 0L;
    
    @Column(name = "Description")
    private String description;
    
    @Column(name = "IsActive")
    private Boolean isActive = true;

    // Quan hệ 1-nhiều với Favorite: Video được nhiều User thích
    @OneToMany(mappedBy = "video", fetch = FetchType.LAZY)
    private List<Favorite> favorites;

    // Quan hệ 1-nhiều với Share: Video được nhiều User chia sẻ
    @OneToMany(mappedBy = "video", fetch = FetchType.LAZY)
    private List<Share> shares;

    // Getters và Setters
    public String getTitle() { return title; }
    // ... (Các getters/setters khác)

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public Long getViews() {
		return views;
	}

	public void setViews(Long views) {
		this.views = views;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public List<Favorite> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}

	public List<Share> getShares() {
		return shares;
	}

	public void setShares(List<Share> shares) {
		this.shares = shares;
	}

	public void setTitle(String title) {
		this.title = title;
	}
    
    
}