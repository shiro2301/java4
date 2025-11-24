package entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "Users") // Đặt tên bảng là Users
public class User {
    
    @Id
    @Column(name = "Id") // Đây là username/ID
    private String id;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Email", unique = true, nullable = false) // Email là duy nhất (Bài 1 (1))
    private String email; 

    @Column(name = "Fullname")
    private String fullname;
    
    @Column(name = "IsAdmin")
    private Boolean isAdmin = false;

    // Quan hệ 1-nhiều với Favorite: User thích nhiều Video
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Favorite> favorites;
    
    // Quan hệ 1-nhiều với Share: User chia sẻ nhiều Video
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Share> shares;

    // Getters và Setters (Bắt buộc cho JPA)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    // ... (Các getters/setters khác)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
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
    
}