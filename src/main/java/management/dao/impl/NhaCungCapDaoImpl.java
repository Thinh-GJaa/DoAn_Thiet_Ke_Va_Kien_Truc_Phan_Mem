package management.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import management.dao.INhaCungCapDao;
import management.entity.Nhacungcap;


@Repository
@Transactional
public class NhaCungCapDaoImpl implements INhaCungCapDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Nhacungcap> get_ListSupplier() {
		List<Nhacungcap> list = new ArrayList<>();
		try {
			Session session = sessionFactory.openSession();
			String hqlString="FROM Nhacungcap";
			Query query = session.createQuery(hqlString);
			 list = query.list();
			 session.close();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return list;
		
	}
	
	
	// Thêm NCC vào CSDL
		@Override
		public boolean addSupplier(Nhacungcap supplier) {
			Session session = sessionFactory.openSession();
			try {
				session.beginTransaction();
				session.save(supplier);
				session.getTransaction().commit();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(57);
				session.getTransaction().rollback();
				
			}finally {
				session.close();
			}
			return false;
		}
}
