package dao;

import dao.ShareDAO;
import dto.ReportShareDTO;
import entity.Share;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import utils.JpaUtil;


import java.util.List;

public class ShareDAOImpl implements ShareDAO { 

    // Phương thức tạo báo cáo (Bài 4)
    public List<ReportShareDTO> getShareReport() {
        EntityManager em = JpaUtil.getEntityManager();
        
        // JPQL sử dụng CONSTRUCTOR EXPRESSION để ánh xạ vào DTO
        // Cú pháp: new <Tên package.Tên DTO>(trường 1, trường 2, ...)
        String jpql = "SELECT new dto.ReportShareDTO(s.video.title, COUNT(s), MIN(s.shareDate), MAX(s.shareDate)) " +
                      "FROM Share s " +
                      "GROUP BY s.video.id, s.video.title"; // Group by ID và Title để đảm bảo tính duy nhất
        
        TypedQuery<ReportShareDTO> query = em.createQuery(jpql, ReportShareDTO.class);
        List<ReportShareDTO> list = query.getResultList();
        em.close();
        return list;
    }

	@Override
	public void insert(Share share) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Share update(Share share) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Share findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Share> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

    // ... CÁC PHƯƠNG THỨC CRUD KHÁC
}