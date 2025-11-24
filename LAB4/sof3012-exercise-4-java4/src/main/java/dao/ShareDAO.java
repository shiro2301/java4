package dao;

import entity.Share;
import dto.ReportShareDTO;
import java.util.List;

public interface ShareDAO {
    
    // Phương thức nghiệp vụ (Bài 4)
    /**
     * Lấy báo cáo tổng hợp về số lượt chia sẻ, ngày chia sẻ đầu và cuối của từng video.
     * @return Danh sách DTO chứa thông tin báo cáo.
     */
    List<ReportShareDTO> getShareReport();
    
    // CRUD Cơ bản
    void insert(Share share);
    Share update(Share share);
    void delete(Long id);
    Share findById(Long id);
    List<Share> findAll();
}