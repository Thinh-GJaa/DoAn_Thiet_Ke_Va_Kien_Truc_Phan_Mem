package management.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import management.dao.IDonHangDao;
import management.entity.Ctpd;
import management.entity.Mathang;
import management.entity.Phieudat;
import management.entity.Size;

@Repository
@Transactional
public class DonHangDaoImpl implements IDonHangDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Mathang layMatHangTheoID(int id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			String hql = "FROM Mathang where mamh = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			Mathang mh = (Mathang) query.uniqueResult();
			transaction.commit();
			Hibernate.initialize(mh.getDanhgias());
			Hibernate.initialize(mh.getHinhanhmhs());
			return mh;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public int LayGiaSP(int id1) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			// Sử dụng HQL để truy vấn giá sản phẩm
			String hql = "SELECT bg.giamoi FROM Banggia bg WHERE bg.id.ngayapdung <= :currentDate AND bg.id.mamh = :id ORDER BY bg.id.ngayapdung DESC";
			Query query = session.createQuery(hql);
			Date currentDate = new Date(); // Ngày hiện tại
			query.setParameter("currentDate", currentDate);
			query.setParameter("id", id1);
			query.setMaxResults(1); // Lấy bản ghi đầu tiên (ngày gần nhất)
			Integer price = (Integer) query.uniqueResult();
			transaction.commit();
			return (price != null) ? price : 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			session.close();
		}
	}

	@Override
	public Size laySize(int maSize) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			String hql = "FROM Size where masize = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", maSize);
			Size mh = (Size) query.uniqueResult();
			transaction.commit();
			return mh;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			session.close();
		}
	}

	@Override
	public List<Ctpd> layDSSPCuaPD(int mapd) {
		
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			String hql = "FROM Ctpd where id.mapd = :mapd";
			Query query = session.createQuery(hql);
			query.setParameter("mapd", mapd);
			List<Ctpd> dssp = (List<Ctpd>) query.list();
			transaction.commit();
			return dssp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			session.close();
		}
		
	}

	@Override
	public List<Phieudat> layDSPDTheoTrangThai(int status) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			String hql = "FROM Phieudat where trangthai = :status";
			Query query = session.createQuery(hql);
			query.setParameter("status", status);
			List<Phieudat> dspd = (List<Phieudat>) query.list();
			transaction.commit();
			return dspd;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			session.close();
		}

	}

	@Override
	public boolean thayDoiTrangThaiPD(Phieudat pd) {
	    Session session = sessionFactory.openSession();
	    Transaction transaction = null;
	    try {
	        transaction = session.beginTransaction();
	        session.update(pd); // Lưu phiếu đặt đã được cập nhật
	        transaction.commit();
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Xử lý lỗi nếu cần
	        return false;
	    } finally {
	        session.close();
	    }
	}
	
	@Override
	public Phieudat layPDTheoID(int mapd) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			String hql = "FROM Phieudat where mapd = :mapd";
			Query query = session.createQuery(hql);
			query.setParameter("mapd", mapd);
			Phieudat pd = (Phieudat) query.uniqueResult();
			transaction.commit();
			return pd;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			session.close();
		}

	}

	@Override
	public String layAnhSP(int masp) {
	    Session session = sessionFactory.openSession();
	    Transaction transaction = null;
	    try {
	        transaction = session.beginTransaction();
	        String hql = "SELECT h.duongdan FROM Hinhanhmh h WHERE h.mathang.mamh = :masp";
	        Query query = session.createQuery(hql);
	        query.setParameter("masp", masp);
	        String link = (String) query.uniqueResult();
	        transaction.commit();
	        return link;
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        return null;
	    } finally {
	        session.close();
	    }
	}

	
}
